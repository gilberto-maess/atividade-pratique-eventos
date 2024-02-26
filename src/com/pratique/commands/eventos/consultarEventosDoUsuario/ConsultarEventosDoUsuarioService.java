package com.pratique.commands.eventos.consultarEventosDoUsuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pratique.domain.eventos.Evento;
import com.pratique.domain.eventos.EventoData;
import com.pratique.domain.usuarios.Usuario;
import com.pratique.infra.repositories.eventos.IEventoRepository;

public class ConsultarEventosDoUsuarioService implements IConsultarEventosDoUsuarioService {

	private IEventoRepository eventoRepository;

	public ConsultarEventosDoUsuarioService(IEventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	public List<EventoData> executar(String idUsuario) throws IOException {
		List<Evento> eventos = this.eventoRepository.get();
		List<EventoData> eventosData = new ArrayList<>();

		for (Evento evento : eventos) {
			List<Usuario> usuarios = evento.getUsuarios();
			if (usuarios != null && usuarios.size() > 0 && usuarioEstaInscrito(usuarios, idUsuario))
				eventosData.add(new EventoData(evento));
		}

		return eventosData;
	}

	private boolean usuarioEstaInscrito(List<Usuario> usuarios, String idUsuario) {
		boolean achou = false;

		for (Usuario usuario : usuarios)
			if (usuario.getId().equals(idUsuario)) {
				achou = true;
				break;
			}

		return achou;
	}

}
