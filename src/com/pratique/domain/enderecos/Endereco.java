package com.pratique.domain.enderecos;

import java.util.UUID;

import com.pratique.shared.StringHelper;

public class Endereco {
	private String id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLogradouro() {
		return this.logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return this.numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return this.complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return this.bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return this.cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCep() {
		return this.cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public static Endereco criar(EnderecoData enderecoData) throws EnderecoException {
		if (enderecoData == null) {
			throw new EnderecoException("Dados do enderećo não informado");
		}
		
		if (StringHelper.isNullOrEmpty(enderecoData.getLogradouro())) {
			throw new EnderecoException("O campo logradouro é obrigatório");
		}
		
		if (StringHelper.isNullOrEmpty(enderecoData.getNumero())) {
			throw new EnderecoException("O campo numero é obrigatório");
		}
		
		if (StringHelper.isNullOrEmpty(enderecoData.getCidade())) {
			throw new EnderecoException("O campo cidade é obrigatório");
		}
		
		if (StringHelper.isNullOrEmpty(enderecoData.getEstado())) {
			throw new EnderecoException("O campo estado é obrigatório");
		}
		
		if (StringHelper.isNullOrEmpty(enderecoData.getCep()c)) {
			throw new EnderecoException("O campo cep é obrigatório");
		}
		
		Endereco novoEndereco = new Endereco();
		
		novoEndereco.id = UUID.randomUUID().toString();
		novoEndereco.logradouro = enderecoData.getLogradouro();
		novoEndereco.numero = enderecoData.getNumero();
		novoEndereco.complemento = enderecoData.getComplemento();
		novoEndereco.cidade = enderecoData.getCidade();
		novoEndereco.estado = enderecoData.getEstado();
		novoEndereco.cep = enderecoData.getCep();
		
		return novoEndereco;
	}
}
