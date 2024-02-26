package com.pratique.commands.eventos.removerUsuario;

import java.io.IOException;

import com.pratique.commands.eventos.inscreverusuario.InscreverUsuarioException;
import com.pratique.domain.eventos.Evento;
import com.pratique.domain.eventos.EventoException;
import com.pratique.domain.usuarios.Usuario;
import com.pratique.infra.repositories.eventos.IEventoRepository;
import com.pratique.infra.repositories.usuarios.IUsuarioRepository;
import com.pratique.shared.StringHelper;

public class RemoverUsuarioService implements IRemoverUsuarioService {

	private IUsuarioRepository usuarioRepository;
	private IEventoRepository eventoRepository;
	
	public RemoverUsuarioService(
		IUsuarioRepository usuarioRepository, 
		IEventoRepository eventoRepository
	) {
		this.usuarioRepository = usuarioRepository;
		this.eventoRepository = eventoRepository;
	}
	
	public void executar(String idUsuario, String idEvento) throws InscreverUsuarioException, IOException, EventoException {
		validar(idUsuario, idEvento);
		
		Usuario usuario = usuarioRepository.getById(idUsuario);
		if (usuario == null) {
			throw new InscreverUsuarioException("O usuário informado não existe");
		}
		
		Evento evento = eventoRepository.getId(idUsuario);
		if (evento == null) {
			throw new InscreverUsuarioException("O evento informado não existe");
		}
		
		evento.removerUsuario(usuario);
		
		eventoRepository.update(evento);
	}
	
	private void validar(String idUsuario, String idEvento) throws InscreverUsuarioException {
		if (StringHelper.isNullOrEmpty(idUsuario)) {
			throw new InscreverUsuarioException("O campo idUsuario é obrigatório");
		}
		
		if (StringHelper.isNullOrEmpty(idEvento)) {
			throw new InscreverUsuarioException("O campo idEvento é obrigatório");
		}
	}
	
}
