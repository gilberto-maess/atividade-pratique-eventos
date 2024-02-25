package com.pratique.commands.eventos.inscreverUsuario;

import java.io.IOException;

import com.pratique.domain.eventos.EventoException;

public interface IInscreverUsuarioService {
	void Executar(String idUsuario, String idEvento) throws InscreverUsuarioException, IOException, EventoException;
}
