package com.pratique.commands.usuarios.consultar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pratique.domain.usuarios.Usuario;
import com.pratique.domain.usuarios.UsuarioData;
import com.pratique.infra.repositories.usuarios.IUsuarioRepository;

public class ConsultarUsuariosService implements IConsultarUsuariosService {
	
	private IUsuarioRepository usuarioRepository;
	
	public ConsultarUsuariosService(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<UsuarioData> executar() throws IOException {
		List<Usuario> usuarios = this.usuarioRepository.get();
		List<UsuarioData> usuariosData = new ArrayList<>();
		
		for(Usuario usuario: usuarios) {
			usuariosData.add(new UsuarioData(usuario));
		}
		
		return usuariosData;
	}
	
}
