package com.pratique.commands.usuarios.logar;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.pratique.domain.usuarios.UsuarioData;

public interface ILogarUsuarioService {
    UsuarioData executar(String email, String senha) throws LogarUsuarioException, IOException, NoSuchAlgorithmException;
}
