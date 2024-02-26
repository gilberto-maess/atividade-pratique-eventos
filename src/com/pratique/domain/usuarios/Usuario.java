package com.pratique.domain.usuarios;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.pratique.domain.enderecos.Endereco;
import com.pratique.domain.enderecos.EnderecoException;
import com.pratique.domain.eventos.Categoria;
import com.pratique.domain.eventos.EventoException;
import com.pratique.shared.StringHelper;

public class Usuario {
	private String id;
	private String nome;
	private String email;
	private String senha;
	private Tipo tipo;
	private Endereco endereco;

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
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public static Usuario criar(UsuarioData usuarioData)
			throws UsuarioException, EventoException, EnderecoException, NoSuchAlgorithmException {
		validarCriacao(usuarioData);

		Usuario novoUsuario = new Usuario();

		novoUsuario.id = UUID.randomUUID().toString();
		novoUsuario.nome = usuarioData.getNome();
		novoUsuario.email = usuarioData.getEmail();
		novoUsuario.tipo = Tipo.fromString(usuarioData.getTipo());
		novoUsuario.senha = StringHelper
				.toSha256(String.format("%s.pratique.%s", usuarioData.getEmail(), usuarioData.getSenha()));
		novoUsuario.endereco = Endereco.criar(usuarioData.getEndereco());

		return novoUsuario;
	}

	private static void validarCriacao(UsuarioData usuarioData) throws UsuarioException {
		if (usuarioData == null) {
			throw new UsuarioException("Nenhum dado informado para que o usuário seja criado");
		}

		if (StringHelper.isNullOrEmpty(usuarioData.getNome())) {
			throw new UsuarioException("O campo nome é obrigatório para se criar um usuário");
		}

		if (StringHelper.isNullOrEmpty(usuarioData.getEmail())) {
			throw new UsuarioException("O campo email é obrigatório para se criar um usuário");
		}

		if (StringHelper.isNullOrEmpty(usuarioData.getTipo())) {
			throw new UsuarioException("O campo tipo é obrigatório");
		}

		if (!Tipo.isValid(usuarioData.getTipo())) {
			throw new UsuarioException("O campo tipo está inválido");
		}

		if (usuarioData.getEndereco() == null) {
			throw new UsuarioException("O campo endereço é obrigatório");
		}
	}

	public boolean validarSenha(String senhaInformada) throws NoSuchAlgorithmException {
		return senha.equals(
			StringHelper
				.toSha256(String.format("%s.pratique.%s", email, senhaInformada))
		);
	}

}
