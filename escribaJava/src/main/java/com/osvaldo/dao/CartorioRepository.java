package com.osvaldo.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.osvaldo.dto.CartorioDTO;
import com.osvaldo.entity.Cartorio;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Long> {
	@Query("SELECT new com.osvaldo.dto.CartorioDTO(obj.id, obj.nome) FROM Cartorio obj WHERE LOWER(obj.nome) LIKE LOWER('%'||:filtro||'%')")
	Page<CartorioDTO> listar(String filtro, Pageable pageable);

	Optional<Cartorio> findByNome(String nome);

	Optional<Cartorio> findByNomeAndIdNot(String nome, Long id);
}
