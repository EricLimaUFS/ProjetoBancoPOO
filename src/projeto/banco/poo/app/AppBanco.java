/**
 * 
 */
package projeto.banco.poo.app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import projeto.banco.poo.appaux.AppAlterarAgencia;
import projeto.banco.poo.appaux.AppExibirExtrato;
import projeto.banco.poo.appaux.AppGerirAgencia;
import projeto.banco.poo.appaux.AppInserirAgencia;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetContasCliente;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbPesquisarCliente;


/**
 * Classe para realizar o gerenciamento do menu do banco, cadastrar agencias e consultar dados 
 * relacionados ao cliente e ao banco.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class AppBanco {

	/**Método principal, responsável por chamar os métodos de gerir agencias, cadastrar agencias e consultar dados.
	 * @return boolean - true */
	public static boolean main() {

		Scanner ler = new Scanner(System.in);
		Banco banco = new Banco(0, null, null, null, null, null);
		String menu = null;

		System.out.println("Digite o código do banco que deseja conectar-se: ");
		banco.setCodigo(ler.nextInt());
		ler.nextLine();

		if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
			banco = DbGetDadosBancos.main(banco.getCodigo());

			System.out.println("	  " + banco.getNomeFantasia() + "		-		App Banco");
			System.out.println();
			System.out.println();
			System.out.println("1 - Cadastros de Agências");
			System.out.println("2 - Consulta de Dados");
			System.out.println("3 - Gerir Agência");
			System.out.println("4 - Sair");
			menu = ler.next();
			ler.nextLine();

			switch (menu) {

			case "1": {
				menuCadastroDeAgencias(banco.getCodigo());
			}
				break;
			case "2": {
				menuConsultaDeDados(banco.getCodigo());
			}
				break;
			case "3": {
				AppGerirAgencia.main(banco);
			}
				break;
			case "4": {
				AppInicio.main(null);
			}
				break;
			default: {
				System.out.println("Opção inválida! Tente novamente usando apenas números de 1 a 4.");
			}
			}
		} else {
			System.out.println(
					"Não foi possível encontrar um banco cadastrado com o código '" + banco.getCodigo() + "'.");
		}
		return true;
	}

	/** Método para realizar a consulta de dados, como o montante de dinheiro, saldo e extrato do cliente à
	 *  partir do código de um banco dado.
	 *  @param codBanco int - código do banco */
	private static boolean menuConsultaDeDados(int codBanco) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;

		System.out.println("\nConsulta de Dados\n");
		System.out.println("1 - Exibir o montante em dinheiro aplicado");
		System.out.println("2 - Exibir saldo do cliente");
		System.out.println("3 - Exibir extrato detalhado do cliente no banco");
		System.out.println("4 - Voltar");
		menu = ler.next();
		ler.nextLine();

		switch (menu) {
		case "1": {
			System.out.println("1 - No banco");
			System.out.println("2 - Numa determinada agência");
			System.out.println("3 - Voltar");
			menu = ler.next();
			ler.nextLine();

			if (menu == "1") {
				System.out.println("Montante aplicado no banco: R$" + MetodosAuxiliares.getMontanteBanco(codBanco));
				menuConsultaDeDados(codBanco);
			} else if (menu == "2") {
				System.out.println("Digite o código da agência:");
				int codAgencia = ler.nextInt();
				System.out.println("Montante aplicado na agência: R$"
						+ MetodosAuxiliares.getMontanteAgencia(codBanco, codAgencia));
				menuConsultaDeDados(codBanco);
			} else if (menu == "3") {
				menuConsultaDeDados(codBanco);
			} else {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}

		}
			break;
		case "2": {

			Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
			cliente.setCodigo(DbPesquisarCliente.main(codBanco));
			if (cliente.getCodigo() != 0) {
				cliente = DbGetDadosClientes.main(cliente.getCodigo(), codBanco);

				while (volta == true) {
					volta = false;
					System.out.println("1 - Por agência");
					System.out.println("2 - Total");
					System.out.println("3 - Voltar");

					menu = ler.nextLine();
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
		case "3": {
			int codCliente = DbPesquisarCliente.main(codBanco);
			ArrayList<Contas> contas = new ArrayList<Contas>();
			contas = DbGetContasCliente.main(codBanco, codCliente);

			for (int i = 0; i < contas.size(); i++) {
				AppExibirExtrato.main(contas.get(i).getCodigo(), codBanco);
			}
		}
			break;
		case "4": {
			AppBanco.main();
		}
			break;

		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 4.");
			menuConsultaDeDados(codBanco);
		}
		}
		return true;
	}

	/** Método para realizar o cadastro e alteração de agencias dentro de um determinado banco.
	 *  @param codBanco int - código do banco */
	public static void menuCadastroDeAgencias(int codBanco) {

		Banco banco = new Banco(0, null, null, null, null, null);
		Scanner ler = new Scanner(System.in);
		boolean volta = true;
		String menu = null;

		while (volta = true) {
			volta = false;
			System.out.println("1 - Inserir cadastro de agência");
			System.out.println("2 - Alterar cadastro de agência");
			// System.out.println("3 - Excluir cadastro de agência"); -- não
			// implementado
			System.out.println("3 - Voltar");
			menu = ler.next();
			ler.nextLine();
			switch (menu) {
			case "1": {
				AppInserirAgencia.main(banco.getCodigo());
				volta = true;
			}
				break;
			case "2": {
				AppAlterarAgencia.main(banco.getCodigo());
				volta = true;
			}
				break;
			/*
			 * case 3: { AppExcluirAgencia.main(); -- não implementado
			 * 
			 * }
			 */
			case "3": {
				AppBanco.main();
			}
				break;
			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}
			}
		}

	}

}
