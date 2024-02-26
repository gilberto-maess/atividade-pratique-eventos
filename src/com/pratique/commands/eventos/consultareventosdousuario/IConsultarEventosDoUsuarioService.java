package com.pratique.commands.eventos.consultareventosdousuario;

import java.io.IOException;
import java.util.List;

import com.pratique.domain.eventos.EventoData;

public interface IConsultarEventosDoUsuarioService {
    List<EventoData> executar(String idUsuario) throws IOException;
}
