package com.pratique.commands.eventos.consultar;

import com.pratique.domain.eventos.Evento;
import com.pratique.domain.eventos.EventoData;
import com.pratique.infra.repositories.eventos.IEventoRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultarEventosService implements IConsultarEventosService {
	
	private IEventoRepository eventoRepository;

	public ConsultarEventosService(IEventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}
	
	public List<EventoData> executar() throws IOException {
		List<Evento> eventos = this.eventoRepository.get();
		List<EventoData> eventosData = new ArrayList<>();
		
		for(Evento evento: eventos) {
			eventosData.add(new EventoData(evento));
		}
		
		eventosData.sort((e1, e2) -> e1.getInicio().compareTo(e2.getInicio()));
		
		return eventosData;
	}
	
}
