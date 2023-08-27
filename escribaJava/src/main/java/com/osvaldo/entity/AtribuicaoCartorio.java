package com.osvaldo.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.osvaldo.dto.AtribuicaoCartorioDTO;
import com.osvaldo.dto.SituacaoCartorioDTO;

@Entity
@Table(name = "ATRIBUICAO_CARTORIO ")
public class AtribuicaoCartorio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String nome;
	private Boolean situacao;

	public AtribuicaoCartorio() {
		super();
	}

	public AtribuicaoCartorio(String id, String nome, Boolean situacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.situacao = situacao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, situacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtribuicaoCartorio other = (AtribuicaoCartorio) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(situacao, other.situacao);
	}
	

	public AtribuicaoCartorioDTO toDto(AtribuicaoCartorio entity) {
		AtribuicaoCartorioDTO dto = new AtribuicaoCartorioDTO();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSituacao(entity.getSituacao());

		return dto;
	}

}
