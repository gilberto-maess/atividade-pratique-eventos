package com.pratique.presentation;

import com.pratique.commands.usuarios.Adicionar.AdicionarUsuarioService;
import com.pratique.commands.usuarios.Adicionar.IAdicionarUsuarioService;
import com.pratique.commands.usuarios.Consultar.IConsultarUsuariosService;
import com.pratique.commands.usuarios.Consultar.ConsultarUsuariosService;
import com.pratique.domain.usuarios.UsuarioData;
import com.pratique.domain.usuarios.UsuarioException;
import com.pratique.infra.repositories.usuarios.IUsuarioRepository;
import com.pratique.infra.repositories.usuarios.UsuarioRepository;
import com.pratique.shared.StringHelper;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Program {
	
	private static IUsuarioRepository usuarioRepository = new UsuarioRepository(); 
	private static IAdicionarUsuarioService adicionarUsuarioService = new AdicionarUsuarioService(usuarioRepository);
	private static IConsultarUsuariosService consultaUsuariosService = new ConsultarUsuariosService(usuarioRepository);
	
	public static void main(String args[]) throws UsuarioException, IOException {
		String opcao = "";
		while(!opcao.equals("3")) {
			System.out.println("--------------------------------");
			System.out.println("Menu");
			System.out.println("--------------------------------");
			System.out.println("1 - Consultar Usuários");
			System.out.println("2 - Cadastrar Usuário");
			System.out.println("3 - Sair");
			System.out.println();
			
			opcao = getVar("Opcao Escolhida");
			
			if (opcao.equals("1")) {
				ConsultarUsuarios();
			} else if (opcao.equals("2")) {
				AdicionarUsuario();
			} else if (opcao.equals("3")) {
				System.out.println();
				System.out.println("Obrigado por usar o programa");
			} else {
				System.out.println("Opcao Inválida");
			}
		}
	}
	
	private static void ConsultarUsuarios() throws IOException, UsuarioException {
		System.out.println("--------------------------------");
		System.out.println("Usuários Cadastrados");
		System.out.println("--------------------------------");
		
		List<UsuarioData> usuarios = consultaUsuariosService.Executar();
		System.out.printf("%-36s %-30s %-30s%n", "ID", "Nome", "E-mail");
        System.out.println("-------------------------------------------------------------------------------");
		for(UsuarioData usuario: usuarios) {
            System.out.printf("%-36s | %-30s | %-30s%n", usuario.getId(), usuario.getNome(), usuario.getEmail());
		}
	}
	
	private static void AdicionarUsuario() throws UsuarioException, IOException {
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
