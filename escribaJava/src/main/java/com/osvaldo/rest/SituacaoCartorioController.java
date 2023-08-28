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

import com.osvaldo.dao.SituacaoCartorioRepository;
import com.osvaldo.dto.QueryParams;
import com.osvaldo.dto.QueryResults;
import com.osvaldo.dto.SituacaoCartorioDTO;
import com.osvaldo.exception.ServiceException;
import com.osvaldo.service.SituacaoCartorioService;

@RestController
@RequestMapping("/api/situacaocartorio")
public class SituacaoCartorioController {

	@Autowired
	private SituacaoCartorioService service;
	@Autowired
	private SituacaoCartorioRepository repository;

	@GetMapping(value = "/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping(value = "/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable String id) {
		return ResponseEntity.ok().body(service.getOne(id));
	}

	@PostMapping(value = "/paged")
	public ResponseEntity<?> paged(@RequestBody QueryParams queryParams) {
		return new ResponseEntity<QueryResults>(service.listar(queryParams), HttpStatus.OK);
	}

	@PostMapping(value = "/inserir")
	public ResponseEntity<SituacaoCartorioDTO> save(@RequestBody SituacaoCartorioDTO dto) {
		return new ResponseEntity<SituacaoCartorioDTO>(service.save(dto), HttpStatus.CREATED);
	}

	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<SituacaoCartorioDTO> update(@RequestBody SituacaoCartorioDTO dto, @PathVariable String id) {
		return new ResponseEntity<SituacaoCartorioDTO>(service.update(dto, id), HttpStatus.CREATED);
	}

	@PostMapping(value = "/excluir")
	public ResponseEntity<?> delete(@RequestBody List<String> ids) throws ServiceException {
		service.delete(ids);
		return ResponseEntity.ok().build();
	}

}
