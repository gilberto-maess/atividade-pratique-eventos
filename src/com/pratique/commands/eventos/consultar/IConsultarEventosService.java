package com.pratique.commands.eventos.consultar;

import java.io.IOException;
import java.util.List;

import com.pratique.domain.eventos.Evento;
import com.pratique.domain.eventos.EventoData;

public interface IConsultarEventosService {
	List<EventoData> executar() throws IOException;
}
