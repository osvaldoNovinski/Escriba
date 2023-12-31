package com.osvaldo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osvaldo.dao.AtribuicaoCartorioRepository;
import com.osvaldo.dto.AtribuicaoCartorioDTO;
import com.osvaldo.dto.QueryParams;
import com.osvaldo.dto.QueryResults;
import com.osvaldo.entity.AtribuicaoCartorio;
import com.osvaldo.exception.ServiceException;

@Service
public class AtribuicaoCartorioService {

	@Autowired
	private AtribuicaoCartorioRepository repository;

	@Transactional(readOnly = true)
	public QueryResults listar(QueryParams queryParams) {
		PageRequest pageRequest = PageRequest.of(queryParams.getPageNumber(), queryParams.getPageSize(),
				Sort.Direction.valueOf(queryParams.getSortOrder().toUpperCase()),
				queryParams.getSortField().isEmpty() ? "nome" : queryParams.getSortField());
		Page<AtribuicaoCartorioDTO> atribuicaoCartorioPage = repository.listar(queryParams.getFilter(), pageRequest);
		List<AtribuicaoCartorioDTO> atribuicaoCartorioList = atribuicaoCartorioPage.getContent().stream()
				.collect(Collectors.toList());
		return new QueryResults(atribuicaoCartorioList, atribuicaoCartorioPage.getTotalElements());
	}

	@Transactional(readOnly = true)
	public AtribuicaoCartorio getOne(String id) {
		if (repository.findById(id).isEmpty()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Registro não encontrado: " + id);
		}
		return repository.findById(id).get();
	}

	public AtribuicaoCartorioDTO save(AtribuicaoCartorioDTO dto) {
		if (repository.findByNome(dto.getNome()).isPresent()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST,
					"Nome já informado no registro com código: " + repository.findByNome(dto.getNome()).get().getId());
		}
		if (repository.findById(dto.getId()).isPresent()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Registro já cadastrado");
		}

		if (dto.getSituacao() == null) {
			dto.setSituacao(true);
		}
		AtribuicaoCartorio entity = dto.toEntity(dto);
		repository.save(entity);
		return entity.toDto(entity);
	}

	@Transactional
	public AtribuicaoCartorioDTO update(AtribuicaoCartorioDTO dto, String idAtribuicao) throws ServiceException {
		try {

			AtribuicaoCartorio entity = repository.findById(idAtribuicao).get();

			if (repository.findByNomeAndIdNot(dto.getNome(), idAtribuicao).isPresent()) {
				throw new ServiceException(HttpStatus.BAD_REQUEST, "Nome já informado no registro com código: "
						+ repository.findByNome(dto.getNome()).get().getId());
			}
			if (dto.getSituacao() == null) {
				dto.setSituacao(true);
			}

			entity.setNome(dto.getNome());
			entity.setSituacao(dto.getSituacao());

			return repository.save(entity).toDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ServiceException(HttpStatus.NOT_FOUND, "Registro não encontrada: " + idAtribuicao);
		}
	}

	@Transactional
	public void delete(List<String> ids) throws ServiceException {

		try {
			ids.stream().forEach(id -> {

				if (findAtribuicaoUsada(id)) {
					throw new ServiceException(HttpStatus.BAD_REQUEST, "Registro utilizado em outro cadastro.");
				}
				;
				repository.deleteById(id);
			});
		} catch (EntityNotFoundException e) {
			throw new ServiceException(HttpStatus.NOT_FOUND, "Registro não encontrada: ");
		}
	}

	@Transactional(readOnly = true)
	public Boolean findAtribuicaoUsada(String idAtribuicao) {

		List<Optional<AtribuicaoCartorio>> entity = repository.findAtribuicaoUsada(idAtribuicao);

		if (entity.size() > 0) {
			return true;
		}

		return false;
	}

}
