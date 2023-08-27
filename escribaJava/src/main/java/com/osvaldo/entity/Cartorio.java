package com.osvaldo.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CARTORIOS")
public class Cartorio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String observacao;
	private SituacaoCartorio situacaoCartorio;
	@OneToMany
	@JoinColumn(name="id")
	private List<AtribuicaoCartorio> atribuicoesCartorio;

	public Cartorio() {
		super();
	}

	public Cartorio(Long id, String nome, String observacao, SituacaoCartorio situacaoCartorio,
			List<AtribuicaoCartorio> atribuicoesCartorio) {
		super();
		this.id = id;
		this.nome = nome;
		this.observacao = observacao;
		this.situacaoCartorio = situacaoCartorio;
		this.atribuicoesCartorio = atribuicoesCartorio;
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

	public List<AtribuicaoCartorio> getAtribuicoesCartorio() {
		return atribuicoesCartorio;
	}

	public void setAtribuicoesCartorio(List<AtribuicaoCartorio> atribuicoesCartorio) {
		this.atribuicoesCartorio = atribuicoesCartorio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atribuicoesCartorio, id, nome, observacao, situacaoCartorio);
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
		return Objects.equals(atribuicoesCartorio, other.atribuicoesCartorio) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(observacao, other.observacao)
				&& Objects.equals(situacaoCartorio, other.situacaoCartorio);
	}

}
