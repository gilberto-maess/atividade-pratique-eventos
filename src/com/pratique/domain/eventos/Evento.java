package com.pratique.domain.eventos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pratique.domain.enderecos.Endereco;
import com.pratique.domain.enderecos.EnderecoException;
import com.pratique.domain.usuarios.Usuario;
import com.pratique.shared.StringHelper;

public class Evento {
	private String id;
	private String nome;
	private String descricao;
	private Categoria categoria;
	private LocalDateTime data;
	private Endereco endereco;
	private List<Usuario> usuarios;
	
	
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
	
	public Categoria getCategoria() {
		return this.categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public static Evento criar(EventoData eventoData) throws EventoException, EnderecoException {
		if (StringHelper.isNullOrEmpty(eventoData.getNome())) {
			throw new EventoException("O campo nome do evento é obrigatório");
		}
		
		if (eventoData.getData() == null) {
			throw new EventoException("O campo data do evento é obrigatório");
		}
		
		if (StringHelper.isNullOrEmpty(eventoData.getCategoria())) {
			throw new EventoException("O campo categoria é obrigatório");
		}
		
		if (!Categoria.isValid(eventoData.getCategoria())) {
			throw new EventoException("O campo categoria está inválido");
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
		novoEvento.categoria = Categoria.fromString(eventoData.getCategoria());
		novoEvento.data = eventoData.getData();
		novoEvento.endereco = Endereco.criar(eventoData.getEndereco());
		
		return novoEvento;
	}
	
	private boolean usuarioJaEstaCadastrado(Usuario usuario) {
		boolean encontrado = false;
		
		for (Usuario usuarioReferencia : this.usuarios) {
            if (usuarioReferencia.getId().equals(usuario.getId())) {
                encontrado = true;
                break;
            }
        }
		
		return encontrado;
	}
	
	public void adicionarUsuario(Usuario usuario) throws EventoException {
		if (usuarios == null) {
			this.usuarios = new ArrayList<>();
			this.usuarios.add(usuario);
		} else {
			if (this.usuarioJaEstaCadastrado(usuario)) {
				throw new EventoException("O usuário já estava cadastrado para este evento");
			}
			
			this.usuarios.add(usuario);
		}
	}
	
	public void removerUsuario(Usuario usuario) throws EventoException {
		if (usuarios == null) {
			throw new EventoException("Nenhum usuário está registrado para este evento");
		}
		
		this.usuarios.remove(usuario);
	}
	
}
