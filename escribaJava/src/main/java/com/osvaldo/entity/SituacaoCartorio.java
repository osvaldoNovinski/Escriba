package com.osvaldo.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.osvaldo.dto.SituacaoCartorioDTO;

@Entity
@Table(name = "SITUACAO_CARTORIO")
public class SituacaoCartorio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String nome;

	public SituacaoCartorio() {
		super();
	}

	public SituacaoCartorio(String id, String nome) {
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
		SituacaoCartorio other = (SituacaoCartorio) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	public SituacaoCartorioDTO toDto(SituacaoCartorio entity) {
		SituacaoCartorioDTO dto = new SituacaoCartorioDTO();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());

		return dto;
	}

}
