package com.osvaldo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osvaldo.dao.CartorioRepository;
import com.osvaldo.dto.CartorioDTO;
import com.osvaldo.dto.QueryParams;
import com.osvaldo.dto.QueryResults;
import com.osvaldo.entity.Cartorio;
import com.osvaldo.exception.ServiceException;

@Service
public class CartorioService {
	@Autowired
	private CartorioRepository repository;

	@Transactional(readOnly = true)
	public QueryResults listar(QueryParams queryParams) {
		PageRequest pageRequest = PageRequest.of(queryParams.getPageNumber(), queryParams.getPageSize(),
				Sort.Direction.valueOf(queryParams.getSortOrder().toUpperCase()),
				queryParams.getSortField().isEmpty() ? "nome" : queryParams.getSortField());
		Page<CartorioDTO> atribuicaoCartorioPage = repository.listar(queryParams.getFilter(), pageRequest);
		List<CartorioDTO> atribuicaoCartorioList = atribuicaoCartorioPage.getContent().stream()
				.collect(Collectors.toList());
		return new QueryResults(atribuicaoCartorioList, atribuicaoCartorioPage.getTotalElements());
	}

	@Transactional(readOnly = true)
	public Cartorio getOne(Long id) {
		if (repository.findById(id).isEmpty()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Registro não encontrado: " + id);
		}
		return repository.findById(id).get();
	}

	public CartorioDTO save(CartorioDTO dto) {

		System.out.println("CARTORIOZINHO: " + dto);
		if (repository.findByNome(dto.getNome()).isPresent()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST,
					"Nome já informado no registro com código: " + repository.findByNome(dto.getNome()).get().getId());
		}
		if (repository.findById(dto.getId()).isPresent()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Registro já cadastrado");
		}
		if (dto.getAtribuicoes().size() < 1) {
			throw new ServiceException(HttpStatus.BAD_REQUEST,
					"Para cadastrar um cartório é necessário informar ao menos uma atribuição");
		}
		if (dto.getSituacaoCartorio() == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST,
					"Para cadastrar um cartório é necessário informar situação do cartório");
		}
		Cartorio entity = dto.toEntity(dto);
		repository.save(entity);
		return entity.toDto(entity);
	}

	@Transactional
	public CartorioDTO update(CartorioDTO dto, Long id) throws ServiceException {
		try {

			Cartorio entity = repository.findById(id).get();

			if (repository.findByNomeAndIdNot(dto.getNome(), id).isPresent()) {
				throw new ServiceException(HttpStatus.BAD_REQUEST, "Nome já informado no registro com código: "
						+ repository.findByNome(dto.getNome()).get().getId());
			}

			if (dto.getAtribuicoes().size() < 1) {
				throw new ServiceException(HttpStatus.BAD_REQUEST,
						"Para cadastrar um cartório é necessário informar ao menos uma atribuição");
			}
			if (dto.getSituacaoCartorio() == null) {
				throw new ServiceException(HttpStatus.BAD_REQUEST,
						"Para cadastrar um cartório é necessário informar situação do cartório");
			}

			entity.setNome(dto.getNome());
			entity.setObservacao(dto.getObservacao());
			entity.setSituacaoCartorio(dto.getSituacaoCartorio());
			entity.setAtribuicoes(dto.getAtribuicoes());

			return repository.save(entity).toDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ServiceException(HttpStatus.NOT_FOUND, "Cartório não encontrado: " + id);
		}
	}

	@Transactional
	public void delete(List<Long> ids) throws ServiceException {
		try {
			ids.stream().forEach(id -> {
				repository.deleteById(id);
			});
		} catch (IllegalArgumentException e) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Atribuição não encontrada: ");
		}
	}

}
