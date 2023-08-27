package com.osvaldo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


import com.osvaldo.entity.AtribuicaoCartorio;

@Repository
public interface AtribuicaoCartorioRepository extends JpaRepository<AtribuicaoCartorio, String> {
	
	@Query("SELECT DISTINCT atribuicaoCartorio FROM AtribuicaoCartorio atribuicaoCartorio WHERE LOWER(atribuicaoCartorio.nome) LIKE LOWER('%'||:filtro||'%')")
	Page<AtribuicaoCartorio> listar(String filtro, Pageable pageable);
	
	Optional<AtribuicaoCartorio> findByNome(String nome);
	
	Optional<AtribuicaoCartorio> findByNomeAndIdNot(String nome, String id);
	
/*	@Query(value="SELECT atribuicaoCartorio.id FROM AtribuicaoCartorio atribuicaoCartorio "
			+ "LEFT JOIN Cartorio cartorio ON cartorio.id = atribuicaoCartorio.id "
			+ "", nativeQuery=true)
	Optional AtribuicaoCartorio findAtribuicaoUsada(String id);*/
}
