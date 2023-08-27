package com.osvaldo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osvaldo.entity.Cartorio;
@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Long> {

}
