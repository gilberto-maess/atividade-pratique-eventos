package com.pratique.domain.usuarios;

import java.util.UUID;

import com.pratique.shared.StringHelper;


public class Usuario {
	private String id;
	private String nome;
	private String email;
	
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
	
	public static Usuario criar(String nome, String email) throws UsuarioException {
		if (StringHelper.isNullOrEmpty(nome)) {
			throw new UsuarioException("O campo nome é obrigatório para se criar um usuário");
		}
		
		if (StringHelper.isNullOrEmpty(email)) {
			throw new UsuarioException("O campo email é obrigatório para se criar um usuário");
		}
		
		if (StringHelper.isValidEmail(email)) {
			throw new UsuarioException("O campo email é obrigatório para se criar um usuário");
		}
		
		Usuario novoUsuario = new Usuario();
		
		novoUsuario.id = UUID.randomUUID().toString();
		novoUsuario.nome = nome;
		novoUsuario.email = email;
		
		return novoUsuario;
	}
	
}
