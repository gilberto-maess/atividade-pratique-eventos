package com.pratique.domain.usuarios;

import com.pratique.domain.enderecos.EnderecoData;

public class UsuarioData {
	private String id;
	private String nome;
	private String email;
	private String senha;
	private String tipo;
	private EnderecoData endereco;

	public UsuarioData() { }

	public UsuarioData(Usuario usuario) {
		id = usuario.getId();
		nome = usuario.getNome();
		email = usuario.getEmail();
		tipo = usuario.getTipo().toString();
		senha = usuario.getSenha();
		endereco = new EnderecoData(usuario.getEndereco());
	}
	
	public String getId() {
		return this.id;
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
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public EnderecoData getEndereco() {
		return this.endereco;
	}
	
	public void setEndereco(EnderecoData endereco) {
		this.endereco = endereco;
	}
}
