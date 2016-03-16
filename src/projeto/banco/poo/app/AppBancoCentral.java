/**
 * 
 */
package projeto.banco.poo.app;

import java.util.ArrayList;
import java.util.Scanner;

import projeto.banco.poo.appaux.AppAlterarBanco;
import projeto.banco.poo.appaux.AppExibirExtrato;
import projeto.banco.poo.appaux.AppInserirBanco;
import projeto.banco.poo.appaux.AppInserirPrimeiroBanco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetContasCliente;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbPesquisarCliente;
import projeto.banco.poo.db.DbPrimeiraConexao;

/**
 * Classe para realizar o gerenciamento de bancos.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class AppBancoCentral {

	/**Método principal, responsável por chamar os métodos de cadastrar bancos e consultar dados relacionados ao banco.
	 * @return boolean - true */
	public static boolean main() {

		Scanner ler = new Scanner(System.in);
		String menu = null;
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
				menu = ler.next();
				ler.nextLine();
				
				if (menu == "1") {
					AppInserirPrimeiroBanco.main();
					break;
				} else if (menu == "2") {
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
				System.out.println("Projeto App Banco		-   	App Banco Central");
				System.out.println();
				System.out.println("Escolha uma opção:");
				System.out.println();
				System.out.println("1 - Cadastros de Bancos");
				System.out.println("2 - Consulta de Dados");
				System.out.println("3 - Sair");
				menu = ler.next();
				ler.nextLine();
				
				switch (menu) {
				case "1": {
					if (menuCadastroDeBancos() == true)
						;
					volta = true;
				}
					break;
				case "2": {
					int codBanco;
					System.out.println("Digite o número do banco:");
					codBanco = ler.nextInt();
					menuConsultaDeDados(codBanco);
				}
					break;
				case "3": {
					retorno = true;
				}
					break;
				default: {
					System.out.println("Opção inválida! Tente novamente usando apenas os números 1 e 3.");
					volta = true;
				}
				}
			} while (volta == true);
		}
		return retorno;

	}

	/**Método para realizar ou alterar o cadastro de bancos.
	 * @return boolean - true */
	public static boolean menuCadastroDeBancos() {

		Scanner ler = new Scanner(System.in);
		System.out.println("1 - Inserir cadastro de banco");
		System.out.println("2 - Alterar cadastro de banco");
		// System.out.println("3 - Excluir cadastro de banco"); -- não
		// implementado
		System.out.println("3 - Voltar");
		String menu = ler.next();
		ler.nextLine();
		switch (menu) {
		case "1": {
			AppInserirBanco.main();
		}
			break;
		case "2": {
			AppAlterarBanco.main();
		}
			break;
		/*
		 * case 3: { AppExcluirBanco.main(); -- não implementado
		 * 
		 * }
		 */
		case "3": {
			AppBancoCentral.main();
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
		}
		}
		return true;
	}

	/**Método para realizar a consulta de dados de saldo de um banco, de uma agencia ou de um cliente e
	 * exibir o extrato de um cliente do banco.
	 * @param codBanco int - código do banco referente as informações
	 * @return boolean - true */
	public static boolean menuConsultaDeDados(int codBanco) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;

		System.out.println("\nConsulta de Dados\n");
		System.out.println("1 - Exibir o montante em dinheiro aplicado no banco");
		System.out.println("2 - Exibir o montante em dinheiro aplicado numa determinada agência");
		System.out.println("3 - Exibir saldo do cliente");
		System.out.println("4 - Exibir extrato detalhado do cliente no banco");
		System.out.println("5 - Voltar");
		menu = ler.next();
		ler.nextLine();

		switch (menu) {
		case "1": {
			System.out.println("Montante aplicado no banco: R$" + MetodosAuxiliares.getMontanteBanco(codBanco));
			menuConsultaDeDados(codBanco);
		}
			break;
		case "2": {
			System.out.println("Digite o código da agência:");
			int codAgencia = ler.nextInt();
			System.out.println(
					"Montante aplicado na agência: R$" + MetodosAuxiliares.getMontanteAgencia(codBanco, codAgencia));
			menuConsultaDeDados(codBanco);
		}
			break;
		case "3": {

			Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
			cliente.setCodigo(DbPesquisarCliente.main(codBanco));
			if (cliente.getCodigo() != 0) {
				cliente = DbGetDadosClientes.main(cliente.getCodigo(), codBanco);

				while (volta == true) {
					volta = false;
					System.out.println("1 - Por agência");
					System.out.println("2 - Total");
					System.out.println("3 - Voltar");

					menu = ler.next();
					ler.nextLine();
					
					if (menu == "1") {
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
					} else if (menu == "2") {
						System.out.println("Saldo total do cliente no banco: " + "R$"
								+ MetodosAuxiliares.getSaldoTotalCliente(codBanco, cliente.getCodigo()));
						menuConsultaDeDados(codBanco);
					} else if (menu == "3") {
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
		case "4": {
			int codCliente = DbPesquisarCliente.main(codBanco);
			ArrayList<Contas> contas = new ArrayList<Contas>();
			contas = DbGetContasCliente.main(codBanco, codCliente);
			
			for (int i = 0; i < contas.size(); i++) {
				AppExibirExtrato.main(contas.get(i).getCodigo(), codBanco);
			}
		}
			break;
		case "5": {
			AppBancoCentral.main();
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 5.");
			menuConsultaDeDados(codBanco);
		}
		}

		return true;
	}
}
