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
			
			opcao = consoleRead("Opcao Escolhida");
			
			if (opcao.equals("1")) {
				System.out.println();
				ConsultarUsuarios();
			} else if (opcao.equals("2")) {
				System.out.println();
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
		if (usuarios.size() == 0) {
			System.out.println();
			System.out.println("Nenhum usuário foi cadastrado");
			System.out.println();
		} else {
			System.out.printf("%-36s %-30s %-30s%n", "ID", "Nome", "E-mail");
	        System.out.println("-------------------------------------------------------------------------------");
			for(UsuarioData usuario: usuarios) {
	            System.out.printf("%-36s | %-30s | %-30s%n", usuario.getId(), usuario.getNome(), usuario.getEmail());
			}
			System.out.println();
		}
	}
	
	private static void AdicionarUsuario() throws UsuarioException, IOException {
		boolean usuarioAdicionado = false;
		
		while(!usuarioAdicionado) {
			try {
				System.out.println("--------------------------------");
				System.out.println("Cadastro de Usuário");
				System.out.println("--------------------------------");
				
				UsuarioData usuarioData = new UsuarioData();
				usuarioData.setNome(consoleRead("Nome"));
				usuarioData.setEmail(consoleRead("E-mail"));
				adicionarUsuarioService.executar(usuarioData);
				
				System.out.println();
				System.out.println("Usuário cadastrado com sucesso!");
				System.out.println();
				usuarioAdicionado = true;
			} catch(Exception ex) {
				System.out.println();
				System.out.println("Erro ao adicionar o usuário. Tente novamente!");
				System.out.println(ex.toString());
				System.out.println();
			}
		}
	}
	
	private static String consoleRead(String label) {
		Scanner scanner = new Scanner(System.in);
		String var = "";
		
		do {
			System.out.print(String.format("%s: ", label));
			var = scanner.nextLine();
		} while(StringHelper.isNullOrEmpty(var));
		
		scanner.close();
		return var;
	}
	
}
