package com.pratique.commands.usuarios.consultar;

import java.io.IOException;
import java.util.List;

import com.pratique.domain.usuarios.UsuarioData;

public interface IConsultarUsuariosService {
	List<UsuarioData> executar() throws IOException;
}
