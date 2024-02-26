package com.pratique.infra.repositories.eventos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pratique.domain.eventos.Evento;

public class EventoRepository implements IEventoRepository {
	private final File arquivo = new File("events.data");
	private final ObjectMapper mapper = new ObjectMapper();
	
	public EventoRepository() {
		mapper.registerModule(new JavaTimeModule());
	}

	private void save(List<Evento> eventos) {
        try {
            mapper.writeValue(arquivo, eventos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public List<Evento> get() throws IOException {
		if (arquivo.exists()) {
			return mapper.readValue(arquivo, new TypeReference<List<Evento>>(){});
		} else {
			return new ArrayList<>();
		}
	}
	
	public Evento getId(String id) throws IOException {
		if (arquivo.exists()) {
			Evento eventoEncontrado = null;
			List<Evento> eventos = mapper.readValue(arquivo, new TypeReference<List<Evento>>(){});
			for(Evento evento: eventos) {
				if (evento.getId().equals(id)) {
					eventoEncontrado = evento;
					break;
				}
			}
			return eventoEncontrado;
		} else {
			return null;
		}
	}

	public void add(Evento evento) throws IOException {
        List<Evento> eventos = get();
        eventos.add(evento);
        save(eventos);
    }
	
	public void update(Evento evento) throws IOException {
        List<Evento> eventos = get();
        for(Evento eventoReferencia: eventos) {
        	if (eventoReferencia.getId().equals(evento.getId())) {
        		evento.atualizar(evento);
        		break;
        	}
        }
        save(eventos);
    }

	public void del(String id) throws IOException {
        List<Evento> eventos = get();
        eventos.removeIf(u -> u.getId() == id);
        save(eventos);
    }
}
