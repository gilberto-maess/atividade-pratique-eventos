package com.pratique.commands.usuarios.adicionar;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.pratique.domain.enderecos.EnderecoException;
import com.pratique.domain.eventos.EventoException;
import com.pratique.domain.usuarios.UsuarioData;
import com.pratique.domain.usuarios.UsuarioException;

public interface IAdicionarUsuarioService {
	void executar(UsuarioData usuario) throws UsuarioException, IOException, NoSuchAlgorithmException, EventoException, EnderecoException;
}
