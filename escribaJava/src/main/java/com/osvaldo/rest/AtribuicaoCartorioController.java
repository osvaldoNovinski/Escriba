package com.osvaldo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osvaldo.dao.AtribuicaoCartorioRepository;
import com.osvaldo.dto.AtribuicaoCartorioDTO;
import com.osvaldo.dto.QueryParams;
import com.osvaldo.dto.QueryResults;
import com.osvaldo.exception.ServiceException;
import com.osvaldo.service.AtribuicaoCartorioService;

@RestController
@RequestMapping("/api/atribuicaocartorio")
public class AtribuicaoCartorioController {

	@Autowired
	private AtribuicaoCartorioService service;
	@Autowired
	private AtribuicaoCartorioRepository repository;

	@GetMapping(value = "/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping(value = "/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable String id) {
		return ResponseEntity.ok().body(service.getOne(id));
	}

	@PostMapping(value = "/paged")
	public ResponseEntity<?> listar(@RequestBody QueryParams queryParams) {
		return new ResponseEntity<QueryResults>(service.listar(queryParams), HttpStatus.OK);
	}

	@PostMapping(value = "/inserir")
	public ResponseEntity<AtribuicaoCartorioDTO> save(@RequestBody AtribuicaoCartorioDTO dto) {
		return new ResponseEntity<AtribuicaoCartorioDTO>(service.save(dto), HttpStatus.CREATED);
	}

	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<AtribuicaoCartorioDTO> update(@RequestBody AtribuicaoCartorioDTO dto,
			@PathVariable String id) {
		return new ResponseEntity<AtribuicaoCartorioDTO>(service.update(dto, id), HttpStatus.CREATED);
	}

	@PostMapping(value = "/excluir")
	public ResponseEntity<?> delete(@RequestBody List<String> ids) throws ServiceException {
		service.delete(ids);
		return ResponseEntity.ok().build();
	}

}
