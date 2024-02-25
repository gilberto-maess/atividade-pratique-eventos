package com.pratique.infra.repositories.usuarios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratique.domain.usuarios.*;

public class UsuarioRepository implements IUsuarioRepository {
	private final File arquivo = new File("usuarios.data");
	private final ObjectMapper mapper = new ObjectMapper();

	private void save(List<Usuario> usuarios) {
        try {
            mapper.writeValue(arquivo, usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public List<Usuario> get() throws IOException {
		if (arquivo.exists()) {
			return mapper.readValue(arquivo, new TypeReference<List<Usuario>>(){});
		} else {
			return new ArrayList<>();
		}
	}

	public void add(Usuario usuario) throws IOException {
        List<Usuario> usuarios = get();
        usuarios.add(usuario);
        save(usuarios);
    }

	public void del(String id) throws IOException {
        List<Usuario> usuarios = get();
        usuarios.removeIf(u -> u.getId() == id);
        save(usuarios);
    }
}
