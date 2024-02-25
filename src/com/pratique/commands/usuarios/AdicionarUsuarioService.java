package com.pratique.commands.usuarios;

import java.io.IOException;

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
	public void executar(UsuarioData usuario) throws UsuarioException, IOException {
		Usuario usario = Usuario.criar(usuario);
		this.usuarioRepository.add(usario);
	}

}
