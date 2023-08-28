package com.osvaldo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.osvaldo.entity.AtribuicaoCartorio;
import com.osvaldo.entity.Cartorio;

public class AtribuicaoCartorioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private Boolean situacao;
	private Set<Cartorio> cartorios = new HashSet<>();

	public AtribuicaoCartorioDTO() {
		super();
	}

	public AtribuicaoCartorioDTO(String id, String nome, Boolean situacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.situacao = situacao;
	}

	public AtribuicaoCartorioDTO(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Set<Cartorio> getCartorios() {
		return cartorios;
	}

	public void setCartorios(Set<Cartorio> cartorios) {
		this.cartorios = cartorios;
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
		AtribuicaoCartorioDTO other = (AtribuicaoCartorioDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(situacao, other.situacao);
	}

	public AtribuicaoCartorio toEntity(AtribuicaoCartorioDTO dto) {
		AtribuicaoCartorio entity = new AtribuicaoCartorio();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSituacao(dto.getSituacao());

		return entity;
	}

}
