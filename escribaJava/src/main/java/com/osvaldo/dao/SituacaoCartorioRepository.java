package com.osvaldo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osvaldo.entity.SituacaoCartorio;
@Repository
public interface SituacaoCartorioRepository extends JpaRepository<SituacaoCartorio, String> {

}
