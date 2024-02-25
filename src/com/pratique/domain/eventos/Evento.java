package com.pratique.domain.eventos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.pratique.domain.enderecos.Endereco;
import com.pratique.domain.enderecos.EnderecoException;
import com.pratique.shared.StringHelper;

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
	
	public Evento criar(EventoData eventoData) throws EventoException, EnderecoException {
		if (StringHelper.isNullOrEmpty(eventoData.getNome())) {
			throw new EventoException("O campo nome do evento é obrigatório");
		}
		
		if (eventoData.getData() == null) {
			throw new EventoException("O campo data do evento é obrigatório");
		}
		
		if (eventoData.getData() == null) {
			throw new EventoException("O campo data do evento é obrigatório");
		}
		
		if (eventoData.getEndereco() == null) {
			throw new EventoException("O campo enderećo do evento é obrigatório");
		}
		
		Evento novoEvento = new Evento();
		
		novoEvento.id = UUID.randomUUID().toString();
		novoEvento.nome = eventoData.getNome();
		novoEvento.descricao = eventoData.getDescricao();
		novoEvento.data = eventoData.getData();
		novoEvento.endereco = Endereco.criar(eventoData.getEndereco());
		
		return novoEvento;
	}
	
}
