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

import com.osvaldo.dao.SituacaoCartorioRepository;
import com.osvaldo.dto.QueryParams;
import com.osvaldo.dto.QueryResults;
import com.osvaldo.dto.SituacaoCartorioDTO;
import com.osvaldo.entity.AtribuicaoCartorio;
import com.osvaldo.entity.SituacaoCartorio;
import com.osvaldo.exception.ServiceException;

@Service
public class SituacaoCartorioService {

	@Autowired
	private SituacaoCartorioRepository repository;

	@Transactional(readOnly = true)
	public QueryResults listar(QueryParams queryParams) {
		PageRequest pageRequest = PageRequest.of(queryParams.getPageNumber(), queryParams.getPageSize(),
				Sort.Direction.valueOf(queryParams.getSortOrder().toUpperCase()),
				queryParams.getSortField().isEmpty() ? "nome" : queryParams.getSortField());
		Page<SituacaoCartorio> situacaoCartorioPage = repository.listar(queryParams.getFilter(), pageRequest);
		List<SituacaoCartorioDTO> situacaoCartorioList = situacaoCartorioPage.getContent().stream().map(c -> c.toDto(c))
				.collect(Collectors.toList());
		return new QueryResults(situacaoCartorioList, situacaoCartorioPage.getTotalElements());
	}

	@Transactional(readOnly = true)
	public SituacaoCartorio getOne(String id) {
		if (repository.findById(id).isEmpty()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Registro não encontrado: " + id);
		}
		return repository.findById(id).get();
	}

	public SituacaoCartorioDTO save(SituacaoCartorioDTO dto) {

		if (repository.findByNome(dto.getNome()).isPresent()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST,
					"Nome já informado no registro com código: " + repository.findByNome(dto.getNome()).get().getId());
		}
		if (repository.findById(dto.getId()).isPresent()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Registro já cadastrado");
		}

		SituacaoCartorio entity = dto.toEntity(dto);

		repository.save(entity);

		return entity.toDto(entity);
	}

	@Transactional
	public SituacaoCartorioDTO update(SituacaoCartorioDTO dto, String idSituacao) throws ServiceException {
		try {
			SituacaoCartorio entity = repository.findById(idSituacao).get();

			if (repository.findByNomeAndIdNot(dto.getNome(), idSituacao).isPresent()) {
				throw new ServiceException(HttpStatus.BAD_REQUEST, "Nome já informado no registro com código: "
						+ repository.findByNome(dto.getNome()).get().getId());
			}
			entity.setNome(dto.getNome());

			return repository.save(entity).toDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ServiceException(HttpStatus.NOT_FOUND, "Situação não encontrada: " + idSituacao);
		}
	}

	@Transactional
	public void delete(List<String> ids) throws ServiceException {
		try {
			ids.stream().forEach(id -> {

				if (findSituacaoUsada(id)) {
					throw new ServiceException(HttpStatus.BAD_REQUEST, "Registro utilizado em outro cadastro.");
				}
				;
				repository.deleteById(id);
			});
		} catch (IllegalArgumentException e) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Situação não encontrada: ");
		}
	}

	@Transactional(readOnly = true)
	public Boolean findSituacaoUsada(String idSituacao) {
		List<Optional<SituacaoCartorio>> entity = repository.findSituacaoUsada(idSituacao);

		if (entity.size() > 0) {
			return true;
		}

		return false;
	}

}
