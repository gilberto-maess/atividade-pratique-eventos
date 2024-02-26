package com.pratique.presentation;

import com.pratique.commands.usuarios.adicionar.AdicionarUsuarioService;
import com.pratique.commands.usuarios.adicionar.IAdicionarUsuarioService;
import com.pratique.commands.usuarios.consultar.ConsultarUsuariosService;
import com.pratique.commands.usuarios.consultar.IConsultarUsuariosService;
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
import com.pratique.commands.eventos.consultareventosdousuario.*;
import com.pratique.commands.eventos.adicionar.*;
import com.pratique.commands.eventos.removerUsuario.*;
import com.pratique.commands.eventos.inscreverusuario.*;
import com.pratique.commands.usuarios.logar.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Program {

	private static final String OPCAO_VER_EVENTOS = "1";
    private static final String OPCAO_PARTICIPAR_EVENTO = "2";
	private static final String OPCAO_CANCELAR_INSCRICAO_EM_EVENTO = "3";
	private static final String OPCAO_VER_DADOS_DO_USUARIO = "4";
	private static final String OPCAO_LISTAR_EVENTOS_DO_USUARIO = "5";
	private static final String OPCAO_ADICIONAR_EVENTO = "6";
	private static final String OPCAO_CONSULTAR_USUARIOS = "7";
	private static final String OPCAO_ADICIONAR_USUARIO = "8";
    private static final String OPCAO_LOGOUT = "9";
    private static final String OPCAO_SAIR = "10";

	private static IUsuarioRepository usuarioRepository = new UsuarioRepository();
	private static IEventoRepository eventoRepository = new EventoRepository();
	private static IAdicionarUsuarioService adicionarUsuarioService = new AdicionarUsuarioService(usuarioRepository);
	private static IConsultarUsuariosService consultaUsuariosService = new ConsultarUsuariosService(usuarioRepository);
	private static IConsultarEventosService consultarEventosService = new ConsultarEventosService(eventoRepository);
	private static IAdicionarEventoService adicionarEventoService = new AdicionarEventoService(eventoRepository);
	private static IInscreverUsuarioService inscreverUsuarioService = new InscreverUsuarioService(
			usuarioRepository,
			eventoRepository);
	private static IRemoverUsuarioService removerIncricaoDoUsuarioService = new RemoverUsuarioService(
			usuarioRepository,
			eventoRepository);
	private static IConsultarEventosDoUsuarioService consultarEventosDoUsuarioService = new ConsultarEventosDoUsuarioService(
			eventoRepository);
	private static ILogarUsuarioService logarUsuarioService = new LogarUsuarioService(usuarioRepository);
	private static UsuarioData usuarioSelecionado = null;

	public static void main(String args[])
			throws UsuarioException, IOException, EventoException, EnderecoException, InscreverUsuarioException,
			NoSuchAlgorithmException, LogarUsuarioException {
		Scanner scanner = new Scanner(System.in);

		System.out.println();
		System.out.println("Gerenciador de Eventos");
		System.out.println();

		List<UsuarioData> usuarios = consultaUsuariosService.executar();
		if (usuarios.size() == 0) {
			System.out.println("Nenhum usuário cadastrado");
			System.out.println();
			adicionarUsuario(scanner, "Admin");
		}

		logarUsuario(scanner);

		System.out.println();
		consultarEventos();

		menu(scanner);
	}

	private static void menu(Scanner scanner)
			throws IOException, InscreverUsuarioException, EventoException, EnderecoException, UsuarioException,
			NoSuchAlgorithmException, LogarUsuarioException {
		while (true) {
			exibirMenu(usuarioSelecionado.getTipo().equals("ADMIN"));

			String opcao = lerConsole("Opção", scanner);

			switch (opcao) {
				case OPCAO_VER_EVENTOS:
					consultarEventos();
					break;
				case OPCAO_PARTICIPAR_EVENTO:
					inscreverUsuarioEmEvento(scanner);
					break;
				case OPCAO_CANCELAR_INSCRICAO_EM_EVENTO:
					cancelarInscricaoDoUsuario(scanner);
					break;
				case OPCAO_VER_DADOS_DO_USUARIO:
					consultarUsuarios(scanner);
					break;
				case OPCAO_LISTAR_EVENTOS_DO_USUARIO:
					listarEventosDoUsuario();
					break;
				case OPCAO_ADICIONAR_EVENTO:
					adicionarEvento(scanner);
					break;
				case OPCAO_CONSULTAR_USUARIOS:
					consultarUsuarios(scanner);
					break;
				case OPCAO_ADICIONAR_USUARIO:
					adicionarUsuario(scanner, usuarioSelecionado.getTipo());
					break;
				case OPCAO_LOGOUT:
					desconectar(scanner);
					break;
				case OPCAO_SAIR:
					System.out.println("\nAté breve!\n");
					return;
				default:
					System.out.println("\nOpção Inválida.\n");
					break;
			}
		}
	}

	private static void exibirMenu(boolean ehAdmin) {
		System.out.println();
		System.out.println("Menu");
		System.out.println("1 - Ver eventos");
		System.out.println("2 - Participar de evento");
		System.out.println("3 - Cancelar participação em evento");
		System.out.println("4 - Ver meus dados");
		System.out.println("5 - Meus eventos");
		if (ehAdmin) {
			System.out.println("6 - Registrar evento");
			System.out.println("7 - Ver usuários registrados");
			System.out.println("8 - Registrar usuário");
		}
		System.out.println("9 - Logout");
		System.out.println("10 - Sair");
		System.out.println();
	}

	private static void escreverDadosDoUsuario(UsuarioData usuario) {
		System.out.println("--------------------------------------------");
		System.out.printf("ID: %s%n", usuario.getId());
		System.out.printf("Nome: %s%n", usuario.getNome());
		System.out.printf("E-mail: %s%n", usuario.getEmail());
		System.out.printf("Tipo: %s%n", usuario.getTipo());
		System.out.println("Endereço:");
		EnderecoData endereco = usuario.getEndereco();
		System.out.printf("  Logradouro: %s, Nº %s%n", endereco.getLogradouro(), endereco.getNumero());
		System.out.printf("  Complemento: %s%n", endereco.getComplemento());
		System.out.printf("  Bairro: %s%n", endereco.getBairro());
		System.out.printf("  Cidade: %s%n", endereco.getCidade());
		System.out.printf("  Estado: %s%n", endereco.getEstado());
		System.out.printf("  CEP: %s%n", endereco.getCep());
	}

	private static void consultarEventos() throws IOException {
		System.out.println("--------------------------------");
		System.out.println("Eventos Cadastrados");
		System.out.println("--------------------------------");

		List<EventoData> eventos = consultarEventosService.executar();
		if (eventos.size() == 0) {
			System.out.println("Nenhum evento foi cadastrado");
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			System.out.println("--------------------------------------------");
			for (EventoData evento : eventos) {
				System.out.printf("ID: %s%n", evento.getId());
				System.out.printf("Nome: %s%n", evento.getNome());
				System.out.printf("Descrição: %s%n", evento.getDescricao());
				System.out.printf("Categoria: %s%n", evento.getCategoria());
				System.out.printf("Início: %s%n", evento.getInicio().format(formatter));
				System.out.printf("Fim: %s%n", evento.getFim().format(formatter));
				System.out.printf("Está Ocorrendo: %s%n", evento.estaOcorrendo() ? "Sim" : "Não");
				System.out.printf("Encerrou: %s%n", evento.encerrou() ? "Sim" : "Não");
				System.out.println("--------------------------------------------");
			}
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
		eventoData.setCategoria(lerConsole(
				"Categoria\nOpćoes Válidas -> Festa, Esporte, Show, Feira, Bootcamp, Treinamento, Congresso e Outros)",
				scanner));

		eventoData.setInicio(
				LocalDateTime.parse(
						lerConsole("Data Inicial do Evento (dd/MM/yyyy HH:mm:ss)", scanner),
						customFormatter));
		eventoData.setFim(
				LocalDateTime.parse(
						lerConsole("Data Final do Evento (dd/MM/yyyy HH:mm:ss)", scanner),
						customFormatter));

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
		System.out.println();
		System.out.println("Evento cadastrado com sucesso");
		System.out.println();
		consultarEventos();
	}

	private static void consultarUsuarios(Scanner scanner) throws IOException, UsuarioException {
		System.out.println();
		System.out.println("Usuários Cadastrados");
		System.out.println();
		List<UsuarioData> usuarios = consultaUsuariosService.executar();
		if (usuarios.size() == 0) {
			System.out.println();
			System.out.println("Nenhum usuário foi cadastrado");
			System.out.println();
		} else {
			for (UsuarioData usuario : usuarios) {
				escreverDadosDoUsuario(usuario);
			}
		}
	}

	private static void adicionarUsuario(Scanner scanner, String tipo) throws UsuarioException, IOException {
		boolean usuarioAdicionado = false;

		while (!usuarioAdicionado) {
			try {
				System.out.println("--------------------------------");
				System.out.println("Cadastro de Usuário");
				System.out.println("--------------------------------");

				UsuarioData usuarioData = new UsuarioData();
				usuarioData.setNome(lerConsole("Nome", scanner));
				usuarioData.setEmail(lerConsole("E-mail", scanner));
				if (StringHelper.isNullOrEmpty(tipo)) {
					usuarioData.setTipo(
							lerConsole(
									"Tipo\nOpćoes Válidas -> Admin, Comum)",
									scanner));
				} else {
					usuarioData.setTipo(tipo);
				}
				usuarioData.setSenha(lerConsole("Senha", scanner));

				System.out.println("Enderećo do Usuário");
				EnderecoData enderecoData = new EnderecoData();
				enderecoData.setLogradouro(lerConsole("Logradouro", scanner));
				enderecoData.setNumero(lerConsole("Número", scanner));
				enderecoData.setComplemento(lerConsole("Complemento", scanner));
				enderecoData.setBairro(lerConsole("Bairro", scanner));
				enderecoData.setCidade(lerConsole("Cidade", scanner));
				enderecoData.setEstado(lerConsole("Estado", scanner));
				enderecoData.setCep(lerConsole("Cep", scanner));

				usuarioData.setEndereco(enderecoData);

				adicionarUsuarioService.executar(usuarioData);

				System.out.println();
				System.out.println("Usuário cadastrado com sucesso!");
				System.out.println();
				usuarioAdicionado = true;
			} catch (Exception ex) {
				System.out.println();
				System.out.println("Erro ao adicionar o usuário. Tente novamente!");
				System.out.println(ex.toString());
				System.out.println();
			}
		}
	}

	private static void listarEventosDoUsuario() throws IOException {
		if (usuarioSelecionado == null) {
			System.out.println();
			System.out.println("Você primeiramente precisa selecionar um usuário.");
			System.out.println("Acesse o menu na općão 3 para selecionar um usuário.");
			System.out.println();
			return;
		}

		System.out.println();
		System.out.println(String.format("Eventos do usuário %s", usuarioSelecionado.getNome()));
		System.out.println();

		List<EventoData> eventos = consultarEventosDoUsuarioService.executar(usuarioSelecionado.getId());
		if (eventos.size() == 0) {
			System.out.println(
					String.format("O usuário %s não está inscrito em nenhum evento", usuarioSelecionado.getNome()));
			System.out.println();
		} else {
			DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			for (EventoData evento : eventos) {
				System.out.printf("ID: %s%n", evento.getId());
				System.out.printf("Nome: %s%n", evento.getNome());
				System.out.printf("Descrição: %s%n", evento.getDescricao());
				System.out.printf("Categoria: %s%n", evento.getCategoria());
				System.out.printf("Início: %s%n", evento.getInicio().format(customFormatter));
				System.out.printf("Fim: %s%n", evento.getFim().format(customFormatter));
				System.out.printf("Está Ocorrendo: %s%n", evento.estaOcorrendo() ? "Sim" : "Não");
				System.out.printf("Encerrou: %s%n", evento.encerrou() ? "Sim" : "Não");
				System.out.println("--------------------------------------------");
			}
		}
	}

	private static void inscreverUsuarioEmEvento(Scanner scanner)
			throws InscreverUsuarioException, IOException, EventoException {
		if (usuarioSelecionado == null) {
			System.out.println();
			System.out.println("Você primeiramente precisa selecionar um usuário.");
			System.out.println("Acesse o menu na općão 3 para selecionar um usuário.");
			System.out.println();
			return;
		}

		String idEvento = lerConsole("idEvento", scanner);

		try {
			inscreverUsuarioService.executar(
					usuarioSelecionado.getId(),
					idEvento);

			System.out.println();
			System.out.println("Usuário inscrito com sucesso.");
			System.out.println();
		} catch (Exception ex) {
			System.out.println();
			System.out.println(String.format("Erro ao realizar a inscrição no evento. Erro: %s", ex.toString()));
			System.out.println();
		}
	}

	private static void cancelarInscricaoDoUsuario(Scanner scanner)
			throws InscreverUsuarioException, IOException, EventoException {
		if (usuarioSelecionado == null) {
			System.out.println();
			System.out.println("Você primeiramente precisa selecionar um usuário.");
			System.out.println("Acesse o menu na općão 3 para selecionar um usuário.");
			System.out.println();
			return;
		}

		String idEvento = lerConsole("idEvento", scanner);

		try {
			removerIncricaoDoUsuarioService.executar(
					usuarioSelecionado.getId(),
					idEvento);

			System.out.println();
			System.out.println("Incrição removida do evento com sucesso.");
			System.out.println();
		} catch (Exception ex) {
			System.out.println();
			System.out.println(String.format("Erro ao cancelar a inscrição no evento. Erro: %s", ex.toString()));
			System.out.println();
		}
	}

	private static void logarUsuario(Scanner scanner)
			throws NoSuchAlgorithmException, LogarUsuarioException, IOException {
		while (true) {
			System.out.println("Login");
			String email = lerConsole("Email", scanner);
			String senha = lerConsole("Senha", scanner);

			try {
				usuarioSelecionado = logarUsuarioService.executar(email, senha);
				break;
			} catch (Exception ex) {
				System.out.println();
				System.out.println(String.format("Não foi possível autenticar o usuário. Erro: %s", ex.toString()));
				System.out.println();

				System.out.println("Deseja tentar novamente?");
				System.out.println("1 - Sim");
				System.out.println("2 - Não");

				System.out.println();
				String opcao = lerConsole("Opção", scanner);
				System.out.println();

				if (opcao.equals("2")) {
					break;
				}
			}
		}
	}

	private static void desconectar(Scanner scanner)
			throws NoSuchAlgorithmException, LogarUsuarioException, IOException {
		usuarioSelecionado = null;
		System.out.println();
		System.out.println("Você precisa se autenticar novamente ou pressione ctrl+c para sair.");
		logarUsuario(scanner);
	}

	private static String lerConsole(String label, Scanner scanner) {
		String var = "";

		do {
			System.out.print(String.format("%s: ", label));
			var = scanner.nextLine();
		} while (StringHelper.isNullOrEmpty(var));

		return var;
	}

}
