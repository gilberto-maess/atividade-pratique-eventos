package com.pratique.domain.eventos;

import java.time.LocalDateTime;

import com.pratique.domain.enderecos.EnderecoData;

public class EventoData {
	private String id;
	private String nome;
	private String descricao;
	private LocalDateTime data;
	private EnderecoData endereco;
	
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
	
	public EnderecoData getEndereco() {
		return this.endereco;
	}
	
	public void setEndereco(EnderecoData endereco) {
		this.endereco = endereco;
	}
}
