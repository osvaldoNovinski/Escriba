package com.osvaldo.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import com.osvaldo.entity.AtribuicaoCartorio;
import com.osvaldo.entity.Cartorio;
import com.osvaldo.entity.SituacaoCartorio;

public class CartorioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String observacao;
	private SituacaoCartorio situacaoCartorio;
	private Set<AtribuicaoCartorio> atribuicoes;

	public CartorioDTO() {
		super();
	}

	public CartorioDTO(Long id, String nome, String observacao, SituacaoCartorio situacaoCartorio,
			Set<AtribuicaoCartorio> atribuicoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.observacao = observacao;
		this.situacaoCartorio = situacaoCartorio;
		this.atribuicoes = atribuicoes;
	}

	public CartorioDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public SituacaoCartorio getSituacaoCartorio() {
		return situacaoCartorio;
	}

	public void setSituacaoCartorio(SituacaoCartorio situacaoCartorio) {
		this.situacaoCartorio = situacaoCartorio;
	}

	public Set<AtribuicaoCartorio> getAtribuicoes() {
		return atribuicoes;
	}

	public void setAtribuicoes(Set<AtribuicaoCartorio> atribuicoes) {
		this.atribuicoes = atribuicoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atribuicoes, id, nome, observacao, situacaoCartorio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartorioDTO other = (CartorioDTO) obj;
		return Objects.equals(atribuicoes, other.atribuicoes) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(observacao, other.observacao)
				&& Objects.equals(situacaoCartorio, other.situacaoCartorio);
	}

	public Cartorio toEntity(CartorioDTO dto) {
		Cartorio entity = new Cartorio();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setObservacao(dto.getObservacao());
		entity.setSituacaoCartorio(dto.getSituacaoCartorio());
		entity.setAtribuicoes(dto.getAtribuicoes());

		return entity;
	}

	@Override
	public String toString() {
		return "CartorioDTO [id=" + id + ", nome=" + nome + ", observacao=" + observacao + ", situacaoCartorio="
				+ situacaoCartorio + ", atribuicoes=" + atribuicoes + "]";
	}

}
