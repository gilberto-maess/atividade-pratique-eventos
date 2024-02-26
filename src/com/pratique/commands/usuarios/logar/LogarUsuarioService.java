package com.pratique.commands.usuarios.logar;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.pratique.domain.usuarios.Usuario;
import com.pratique.domain.usuarios.UsuarioData;
import com.pratique.infra.repositories.usuarios.IUsuarioRepository;
import com.pratique.shared.StringHelper;

public class LogarUsuarioService implements ILogarUsuarioService {
    private IUsuarioRepository usuarioRepository;

    public LogarUsuarioService(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

    public UsuarioData executar(String email, String senha) throws LogarUsuarioException, IOException, NoSuchAlgorithmException {
        validar(email, senha);

        Usuario usuario = usuarioRepository.getByEmail(email);
        if (usuario == null) {
            throw new LogarUsuarioException("O usuário informado não foi encontrado");
        }

        if (!usuario.validarSenha(senha)) {
            throw new LogarUsuarioException("Credenciais inválidas");
        }

        return new UsuarioData(usuario);
    }

    private void validar(String email, String senha) throws LogarUsuarioException {
        if (StringHelper.isNullOrEmpty(email)) {
            throw new LogarUsuarioException("O campo e-mail é obrigatório para o login");
        }

        if (StringHelper.isNullOrEmpty(senha)) {
            throw new LogarUsuarioException("O campo senha é obrigatório para o login");
        }
    }
}
