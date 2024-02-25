package com.pratique.commands.eventos.adicionar;

import java.io.IOException;

import com.pratique.domain.enderecos.EnderecoException;
import com.pratique.domain.eventos.EventoData;
import com.pratique.domain.eventos.EventoException;

public interface IAdicionarEventoService {
	void Executar(EventoData eventoData) throws EventoException, EnderecoException, IOException;
}
