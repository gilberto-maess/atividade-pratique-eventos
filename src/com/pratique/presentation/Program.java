package com.pratique.presentation;

import com.pratique.commands.usuarios.AdicionarUsuarioService;
import com.pratique.commands.usuarios.IAdicionarUsuarioService;
import com.pratique.domain.usuarios.UsuarioData;
import com.pratique.domain.usuarios.UsuarioException;
import com.pratique.infra.repositories.usuarios.IUsuarioRepository;
import com.pratique.infra.repositories.usuarios.UsuarioRepository;
import com.pratique.shared.StringHelper;

import java.io.IOException;
import java.util.Scanner;

public class Program {
	
	public static void main(String args[]) throws UsuarioException, IOException {
		IUsuarioRepository usuarioRepository = new UsuarioRepository();
		IAdicionarUsuarioService adicionarUsuarioService = new AdicionarUsuarioService(usuarioRepository);
		
		AdicionarUsuario(adicionarUsuarioService);
	}
	
	private static void ConsultarUsuarios() {
		
	}
	
	private static void AdicionarUsuario(IAdicionarUsuarioService adicionarUsuarioService) throws UsuarioException, IOException {
		System.out.println("--------------------------------");
		System.out.println("Cadastro de Usuário");
		System.out.println("--------------------------------");
		
		UsuarioData usuarioData = new UsuarioData();
		usuarioData.setNome(getVar("Nome"));
		usuarioData.setEmail(getVar("E-mail"));
		adicionarUsuarioService.executar(usuarioData);
		
		System.out.println("--------------------------------");
		System.out.println("Usuário cadastrado com sucesso!");
		System.out.println("--------------------------------");
	}
	
	private static String getVar(String label) {
		Scanner scanner = new Scanner(System.in);
		String var = "";
		
		do {
			System.out.print(String.format("%s: ", label));
			var = scanner.nextLine();
		} while(StringHelper.isNullOrEmpty(var));
		
		return var;
	}
	
}
