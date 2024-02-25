package com.pratique.infra.repositories.eventos;

import java.io.IOException;
import java.util.List;

import com.pratique.domain.eventos.Evento;

public interface IEventoRepository {
	List<Evento> get() throws IOException;
	Evento getId(String id) throws IOException;
	void add(Evento evento) throws IOException;
	void update(Evento evento) throws IOException;
	void del(String id) throws IOException;
}
