package com.osvaldo.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;

import com.osvaldo.entity.SituacaoCartorio;

public class SituacaoCartorioDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;

	public SituacaoCartorioDTO() {
		super();
	}

	public SituacaoCartorioDTO(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SituacaoCartorioDTO other = (SituacaoCartorioDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	public SituacaoCartorio toEntity(SituacaoCartorioDTO dto) {
		SituacaoCartorio entity = new SituacaoCartorio();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());

		return entity;
	}
}
