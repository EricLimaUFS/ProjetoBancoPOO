/**
 * 
 */
package projeto.banco.poo.app;

import projeto.banco.poo.appaux.AppAlterarBanco;
import projeto.banco.poo.appaux.AppExcluirBanco;
import projeto.banco.poo.appaux.AppInserirBanco;
import projeto.banco.poo.appaux.AppInserirPrimeiroBanco;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbBanco;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbPesquisarCliente;
import projeto.banco.poo.db.DbPrimeiraConexao;
import projeto.banco.poo.db.DbSetDadosBancos;

import java.sql.*;
import java.util.Scanner;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 */
public class AppBancoCentral {

	/**
	 * @param args
	 */
	public static boolean main() {

		Scanner ler = new Scanner(System.in);
		byte menu = 0;
		boolean retorno = false;
		boolean volta = true;

		// Limpar tela
		for (int i = 0; i < 18; i++) {
			System.out.println();
		}

		if (DbPrimeiraConexao.main() == true) {

			do {
				volta = false;
				System.out.println("ATENÇÃO - Não há banco cadastrado! Deseja cadastrar o banco?");
				System.out.println();
				System.out.println("1 - Sim");
				System.out.println("2 - Não");
				menu = ler.nextByte();
				if (menu == 1) {
					AppInserirPrimeiroBanco.main();
					break;
				} else if (menu == 2) {
					retorno = true;
					break;
				} else {
					System.out.println("Opção inválida! Tente novamente usando apenas os números 1 ou 2.");
					volta = true;
				}

			} while (volta = true);
		}

		if (DbPrimeiraConexao.main() == false) {

			do {
				volta = false;
				System.out.println();
				System.out.println("Projeto App Banco				-			App Banco Central");
				System.out.println();
				System.out.println("Escolha uma opção:");
				System.out.println();
				System.out.println("1 - Cadastros de Bancos");
				System.out.println("2 - Consulta de Dados");
				System.out.println("3 - Sair");
				menu = ler.nextByte();
				switch (menu) {
				case 1: {
					if (menuCadastroDeBancos() == true)
						;
					volta = true;
				}
					break;
				case 2: {
					int codBanco;
					System.out.println("Digite o número do banco:");
					codBanco = ler.nextInt();
					menuConsultaDeDados(codBanco);
				}
					break;
				case 3: {
					retorno = true;
				}
					break;
				default: {
					System.out.println("Opção inválida! Tente novamente usando apenas os números 1 ou 2.");
					volta = true;
				}
				}
			} while (volta == true);
		}
		return retorno;

	}

	public static boolean menuCadastroDeBancos() {

		Scanner ler = new Scanner(System.in);
		System.out.println("1 - Inserir cadastro de banco");
		System.out.println("2 - Alterar cadastro de banco");
		// System.out.println("3 - Excluir cadastro de banco"); -- não
		// implementado
		System.out.println("3 - Voltar");
		byte menu = ler.nextByte();
		switch (menu) {
		case 1: {
			AppInserirBanco.main();
		}
			break;
		case 2: {
			AppAlterarBanco.main();
		}
			break;
		/*
		 * case 3: { AppExcluirBanco.main(); -- não implementado
		 * 
		 * }
		 */
		case 3: {
			// Volta
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
		}
		}
		return true;
	}

	public static boolean menuConsultaDeDados(int codBanco) {

		Scanner ler = new Scanner(System.in);
		byte menu = 0;
		boolean volta = true;

		System.out.println("\nConsulta de Dados\n");
		System.out.println("1 - Exibir o montante em dinheiro aplicado no banco");
		System.out.println("2 - Exibir o montante em dinheiro aplicado numa determinada agência");
		System.out.println("3 - Exibir saldo do cliente");
		System.out.println("4 - Exibir extrato detalhado do cliente no banco");
		System.out.println("5 - Exibir extrato detalhado do cliente em todos os bancos");
		System.out.println("6 - Voltar");
		menu = ler.nextByte();
		ler.nextLine();

		switch (menu) {
		case 1: {
			System.out.println("Montante aplicado no banco: R$" + MetodosAuxiliares.getMontanteBanco(codBanco));
			menuConsultaDeDados(codBanco);
		}
			break;
		case 2: {
			System.out.println("Digite o código da agência:");
			int codAgencia = ler.nextInt();
			System.out.println(
					"Montante aplicado na agência: R$" + MetodosAuxiliares.getMontanteAgencia(codBanco, codAgencia));
			menuConsultaDeDados(codBanco);
		}
			break;
		case 3: {

			Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
			cliente.setCodigo(DbPesquisarCliente.main(codBanco));
			if (cliente.getCodigo() != 0) {
				cliente = DbGetDadosClientes.main(cliente.getCodigo(), codBanco);

				while (volta == true) {
					volta = false;
					System.out.println("1 - Por agência");
					System.out.println("2 - Total");
					System.out.println("3 - Voltar");

					menu = ler.nextByte();
					if (menu == 1) {
						System.out.println("Digite o código da agência:");
						int codAgencia = ler.nextInt();
						if (DbGetDadosAgencias.main(codAgencia, codBanco).getCodigo() != 0) {
							System.out.println("Saldo total do cliente na agência '" + codAgencia + "': " + "R$"
									+ MetodosAuxiliares.getSaldoTotalClienteAg(codBanco, codAgencia,
											cliente.getCodigo()));
							menuConsultaDeDados(codBanco);
						} else {
							System.out.println("Agência não encontrada! Tente novamente.");
							menuConsultaDeDados(codBanco);
						}
					} else if (menu == 2) {
						System.out.println("Saldo total do cliente no banco: " + "R$"
								+ MetodosAuxiliares.getSaldoTotalCliente(codBanco, cliente.getCodigo()));
						menuConsultaDeDados(codBanco);
					} else if (menu == 3) {
						menuConsultaDeDados(codBanco);
					} else {
						System.out.println("Opção inválida!");
						volta = true;
					}
				}
			} else {
				menuConsultaDeDados(codBanco);
			}
		}
			break;
		case 4: {

		}
			break;
		case 5: {

		}
			break;
		case 6: {
			AppBancoCentral.main();
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 6.");
			menuConsultaDeDados(codBanco);
		}
		}

		return true;
	}
}
