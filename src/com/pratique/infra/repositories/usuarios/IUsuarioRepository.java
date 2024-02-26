package com.pratique.infra.repositories.usuarios;

import java.io.IOException;
import java.util.List;

import com.pratique.domain.usuarios.Usuario;

public interface IUsuarioRepository {
	List<Usuario> get() throws IOException;
	Usuario getById(String id) throws IOException;
	Usuario getByEmail(String email) throws IOException;
	void add(Usuario usuario) throws IOException;
	void del(String id) throws IOException;
}
