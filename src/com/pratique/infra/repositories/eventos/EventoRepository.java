package com.pratique.infra.repositories.eventos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratique.domain.eventos.Evento;

public class EventoRepository implements IEventoRepository {
	private final File arquivo = new File("events.data");
	private final ObjectMapper mapper = new ObjectMapper();

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

	public void add(Evento usuario) throws IOException {
        List<Evento> eventos = get();
        eventos.add(usuario);
        save(eventos);
    }

	public void del(String id) throws IOException {
        List<Evento> eventos = get();
        eventos.removeIf(u -> u.getId() == id);
        save(eventos);
    }
}
