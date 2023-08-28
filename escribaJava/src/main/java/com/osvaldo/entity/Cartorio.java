package com.osvaldo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.osvaldo.dto.CartorioDTO;

@Entity
@Table(name = "CARTORIOS")
public class Cartorio implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String nome;
	private String observacao;
	@ManyToOne
	@JoinColumn(name = "SITUACAO_ID")
	private SituacaoCartorio situacaoCartorio;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "cartorio_atribuicoes", joinColumns = @JoinColumn(name = "cartorio_id"), inverseJoinColumns = @JoinColumn(name = "atribuicao_id"))
	private Set<AtribuicaoCartorio> atribuicoes = new HashSet<>();

	public Cartorio() {
		super();
	}

	public Cartorio(Long id) {
		super();
		this.id = id;
	}

	public Cartorio(Long id, String nome, String observacao, SituacaoCartorio situacaoCartorio,
			Set<AtribuicaoCartorio> atribuicoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.observacao = observacao;
		this.situacaoCartorio = situacaoCartorio;
		this.atribuicoes = atribuicoes;
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
		Cartorio other = (Cartorio) obj;
		return Objects.equals(atribuicoes, other.atribuicoes) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(observacao, other.observacao)
				&& Objects.equals(situacaoCartorio, other.situacaoCartorio);
	}

	public Set<AtribuicaoCartorio> getAtribuicoes() {
		return atribuicoes;
	}

	public void setAtribuicoes(Set<AtribuicaoCartorio> atribuicoes) {
		this.atribuicoes = atribuicoes;
	}

	public CartorioDTO toDto(Cartorio entity) {
		CartorioDTO dto = new CartorioDTO();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setObservacao(entity.getObservacao());
		dto.setSituacaoCartorio(entity.getSituacaoCartorio());
		dto.setAtribuicoes(entity.getAtribuicoes());

		return dto;
	}

}
