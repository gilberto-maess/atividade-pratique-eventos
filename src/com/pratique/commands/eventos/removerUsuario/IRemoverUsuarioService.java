package com.pratique.commands.eventos.removerUsuario;

import java.io.IOException;

import com.pratique.commands.eventos.inscreverUsuario.InscreverUsuarioException;
import com.pratique.domain.eventos.EventoException;

public interface IRemoverUsuarioService {
	void executar(String idUsuario, String idEvento) throws InscreverUsuarioException, IOException, EventoException;
}
