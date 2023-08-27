package com.osvaldo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osvaldo.dao.SituacaoCartorioRepository;

@RestController
@RequestMapping("/api/situacaocartorio")
public class SituacaoCartorioController {
	
	@Autowired
	private SituacaoCartorioRepository repository;

	@GetMapping(value = "/getall")
	public ResponseEntity<?> getAll(){
		
		System.out.println("VEIOOOOOOOOOO:  "+repository.findAll());
		
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	
}
