package com.pratique.presentation;

import com.pratique.commands.usuarios.Adicionar.AdicionarUsuarioService;
import com.pratique.commands.usuarios.Adicionar.IAdicionarUsuarioService;
import com.pratique.commands.usuarios.Consultar.IConsultarUsuariosService;
import com.pratique.commands.usuarios.Consultar.ConsultarUsuariosService;
import com.pratique.domain.enderecos.EnderecoData;
import com.pratique.domain.enderecos.EnderecoException;
import com.pratique.domain.eventos.EventoData;
import com.pratique.domain.eventos.EventoException;
import com.pratique.domain.usuarios.UsuarioData;
import com.pratique.domain.usuarios.UsuarioException;
import com.pratique.infra.repositories.usuarios.IUsuarioRepository;
import com.pratique.infra.repositories.usuarios.UsuarioRepository;
import com.pratique.shared.StringHelper;
import com.pratique.infra.repositories.eventos.*;
import com.pratique.commands.eventos.consultar.*;
import com.pratique.commands.eventos.adicionar.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Program {
	
	private static IUsuarioRepository usuarioRepository = new UsuarioRepository();
	private static IEventoRepository eventoRepository = new EventoRepository(); 
	private static IAdicionarUsuarioService adicionarUsuarioService = new AdicionarUsuarioService(usuarioRepository);
	private static IConsultarUsuariosService consultaUsuariosService = new ConsultarUsuariosService(usuarioRepository);
	private static IConsultarEventosService consultarEventosService = new ConsultarEventosService(eventoRepository);
	private static IAdicionarEventoService adicionarEventoService = new AdicionarEventoService(eventoRepository);
	
	public static void main(String args[]) throws UsuarioException, IOException, EventoException, EnderecoException {
		Scanner scanner = new Scanner(System.in);
		String opcao = "";
		
		while(true) {
			System.out.println("--------------------------------");
			System.out.println("Menu");
			System.out.println("--------------------------------");
			System.out.println("1 - Consultar Eventos");
			System.out.println("2 - Cadastrar Evento");
			System.out.println("3 - Consultar Usuários");
			System.out.println("4 - Adicionar Usuário");
			System.out.println("5 - Inscrever Usuário em Evento");
			System.out.println("6 - Remover Usuario de Evento");
			System.out.println("7 - Sair");
			System.out.println();
			
			opcao = lerConsole("Opcao Escolhida", scanner);
			
			if (opcao.equals("1")) {
				System.out.println();
				consultarEventos();
			} else if (opcao.equals("2")) {
				System.out.println();
				adicionarEvento(scanner);
			} else if (opcao.equals("3")) {
				System.out.println();
				consultarUsuarios();
			} else if (opcao.equals("4")) {
				System.out.println();
				adicionarUsuario(scanner);
			} else if (opcao.equals("7")) {
				System.out.println();
				System.out.println("Obrigado por usar o programa");
				break;
			} else {
				System.out.println("Opcao Inválida");
			}
		}
		
		scanner.close();
	}
	
	private static void consultarEventos() throws IOException {
		System.out.println("--------------------------------");
		System.out.println("Eventos Cadastrados");
		System.out.println("--------------------------------");
		
		List<EventoData> eventos = consultarEventosService.Executar();
		if (eventos.size() == 0) {
			System.out.println();
			System.out.println("Nenhum evento foi cadastrado");
			System.out.println();
		} else {
			System.out.printf("%-36s %-30s %-30s%n", "ID", "Nome", "E-mail");
	        System.out.println("-------------------------------------------------------------------------------");
			for(EventoData evento: eventos) {
	            System.out.printf("%-36s | %-30s | %-30s%n", evento.getId(), evento.getNome(), evento.getDescricao());
			}
			System.out.println();
		}
	}
	
	private static void adicionarEvento(Scanner scanner) throws EventoException, EnderecoException, IOException {
		DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		System.out.println("--------------------------------");
		System.out.println("Cadastro de Evento");
		System.out.println("--------------------------------");
		
		EventoData eventoData = new EventoData();
		eventoData.setNome(lerConsole("Nome", scanner));
		eventoData.setDescricao(lerConsole("Descricao", scanner));
		eventoData.setCategoria(lerConsole("Categoria\nOpćoes Válidas -> Festa, Esporte, Show, Feira, Bootcamp, Treinamento, Congresso e Outros)", scanner));
		
		eventoData.setInicio(
			LocalDateTime.parse(
				lerConsole("Data Inicial do Evento (dd/MM/yyyy HH:mm:ss)", scanner), 
				customFormatter)
		);
		eventoData.setFim(
			LocalDateTime.parse(
				lerConsole("Data Final do Evento (dd/MM/yyyy HH:mm:ss)", scanner), 
				customFormatter)
		);
		
		System.out.println();
		System.out.println("Enderećo do Evento");
		EnderecoData enderecoData = new EnderecoData();
		enderecoData.setLogradouro(lerConsole("Logradouro", scanner));
		enderecoData.setNumero(lerConsole("Número", scanner));
		enderecoData.setComplemento(lerConsole("Complemento", scanner));
		enderecoData.setBairro(lerConsole("Bairro", scanner));
		enderecoData.setCidade(lerConsole("Cidade", scanner));
		enderecoData.setEstado(lerConsole("Estado", scanner));
		enderecoData.setCep(lerConsole("Cep", scanner));
		
		eventoData.setEndereco(enderecoData);
		
		adicionarEventoService.Executar(eventoData);
	}
	
	private static void consultarUsuarios() throws IOException, UsuarioException {
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
	
	private static void adicionarUsuario(Scanner scanner) throws UsuarioException, IOException {
		boolean usuarioAdicionado = false;
		
		while(!usuarioAdicionado) {
			try {
				System.out.println("--------------------------------");
				System.out.println("Cadastro de Usuário");
				System.out.println("--------------------------------");
				
				UsuarioData usuarioData = new UsuarioData();
				usuarioData.setNome(lerConsole("Nome", scanner));
				usuarioData.setEmail(lerConsole("E-mail", scanner));
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
	
	private static String lerConsole(String label, Scanner scanner) {
		String var = "";
		
		do {
			System.out.print(String.format("%s: ", label));
			var = scanner.nextLine();
		} while(StringHelper.isNullOrEmpty(var));
		
		return var;
	}
	
}
