package com.pratique.domain.eventos;

import java.time.LocalDateTime;

import com.pratique.domain.enderecos.EnderecoData;

public class EventoData {
	private String id;
	private String nome;
	private String descricao;
	private String categoria;
	private LocalDateTime data;
	private EnderecoData endereco;
	
	public EventoData() { }
	
	public EventoData(Evento evento) {
		id = evento.getId();
		nome = evento.getNome();
		descricao = evento.getDescricao();
		categoria = evento.getCategoria().toString();
		data = evento.getData();
	}
	
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
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
