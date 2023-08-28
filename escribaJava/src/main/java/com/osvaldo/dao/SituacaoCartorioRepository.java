package com.osvaldo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.osvaldo.entity.SituacaoCartorio;

@Repository
public interface SituacaoCartorioRepository extends JpaRepository<SituacaoCartorio, String> {
	@Query("SELECT DISTINCT situacaoCartorio FROM SituacaoCartorio situacaoCartorio WHERE LOWER(situacaoCartorio.nome) LIKE LOWER('%'||:filtro||'%')")
	Page<SituacaoCartorio> listar(String filtro, Pageable pageable);

	Optional<SituacaoCartorio> findByNome(String nome);

	Optional<SituacaoCartorio> findByNomeAndIdNot(String nome, String id);

	@Query("SELECT obj FROM SituacaoCartorio obj "
			+ "WHERE obj.id = :id AND EXISTS (SELECT 1 FROM Cartorio c WHERE c.situacaoCartorio = obj)")
	List<Optional<SituacaoCartorio>> findSituacaoUsada(String id);

}
