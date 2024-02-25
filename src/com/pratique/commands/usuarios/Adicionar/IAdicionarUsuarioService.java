package com.pratique.commands.usuarios.Adicionar;

import java.io.IOException;

import com.pratique.domain.usuarios.UsuarioData;
import com.pratique.domain.usuarios.UsuarioException;

public interface IAdicionarUsuarioService {
	void executar(UsuarioData usuario) throws UsuarioException, IOException;
}
