package com.pratique.domain.eventos;

import java.time.LocalDateTime;

import com.pratique.domain.enderecos.Endereco;

public class Evento {
	private String id;
	private String nome;
	private String descricao;
	private LocalDateTime data;
	private Endereco endereco;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDateTime getData() {
		return this.data;
	}
	
	public void setDescricao(LocalDateTime data) {
		this.data = data;
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
