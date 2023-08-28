package com.osvaldo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.osvaldo.dto.AtribuicaoCartorioDTO;
import com.osvaldo.entity.AtribuicaoCartorio;

@Repository
public interface AtribuicaoCartorioRepository extends JpaRepository<AtribuicaoCartorio, String> {

	@Query("SELECT new com.osvaldo.dto.AtribuicaoCartorioDTO(obj.id, obj.nome) FROM AtribuicaoCartorio obj WHERE LOWER(obj.nome) LIKE LOWER('%'||:filtro||'%')")
	Page<AtribuicaoCartorioDTO> listar(String filtro, Pageable pageable);

	Optional<AtribuicaoCartorio> findByNome(String nome);

	Optional<AtribuicaoCartorio> findByNomeAndIdNot(String nome, String id);

	@Query("SELECT obj FROM AtribuicaoCartorio obj " + "JOIN obj.cartorios c " + "WHERE obj.id = :id")
	List<Optional<AtribuicaoCartorio>> findAtribuicaoUsada(String id);

}
