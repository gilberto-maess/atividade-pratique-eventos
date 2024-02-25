package com.pratique.commands.eventos.adicionar;

import java.io.IOException;

import com.pratique.domain.enderecos.EnderecoException;
import com.pratique.domain.eventos.Evento;
import com.pratique.domain.eventos.EventoData;
import com.pratique.domain.eventos.EventoException;
import com.pratique.infra.repositories.eventos.IEventoRepository;

public class AdicionarEventoService implements IAdicionarEventoService {
	
	IEventoRepository eventoRepository;
	
	public AdicionarEventoService(IEventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}
	
	public void Executar(EventoData eventoData) throws EventoException, EnderecoException, IOException {
		Evento evento = Evento.criar(eventoData);
		this.eventoRepository.add(evento);
	}
}
