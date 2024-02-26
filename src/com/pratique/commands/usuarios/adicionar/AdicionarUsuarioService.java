package com.pratique.commands.usuarios.adicionar;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.pratique.domain.enderecos.EnderecoException;
import com.pratique.domain.eventos.EventoException;
import com.pratique.domain.usuarios.Usuario;
import com.pratique.domain.usuarios.UsuarioData;
import com.pratique.domain.usuarios.UsuarioException;
import com.pratique.infra.repositories.usuarios.IUsuarioRepository;

public class AdicionarUsuarioService implements IAdicionarUsuarioService {
	
	private IUsuarioRepository usuarioRepository;

	public AdicionarUsuarioService(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public void executar(UsuarioData usuario) throws UsuarioException, IOException, NoSuchAlgorithmException, EventoException, EnderecoException {
		Usuario usario = Usuario.criar(usuario);
		this.usuarioRepository.add(usario);
	}

}
