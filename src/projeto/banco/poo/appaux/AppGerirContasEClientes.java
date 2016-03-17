/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;

import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetContasCliente;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbGetDadosContas;
import projeto.banco.poo.db.DbPesquisarCliente;
import projeto.banco.poo.db.DbSetDadosContas;

/**
 * Classe responsável por gerenciar os dados de contas e clientes.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 14 de mar de 2016
 * @version 1.0
 */
public class AppGerirContasEClientes {

	/**Método principal, responsável por chamar os métodos de realizar o gerenciamento de contas, agencias e clientes.
	 * @param codAgencia int - código da agencia que a conta pertence ou o cleinte pertencem
	 * @param codBanco int - código do banco que a conta, o cliente ou a agencia pertencem */
	public static void main(int codBanco, int codAgencia) {

		Banco objBanco = new Banco(codBanco, null, null, null, null, null);
		objBanco = DbGetDadosBancos.main(codBanco);
		Scanner ler = new Scanner(System.in);
		String menu = null;
		System.out.println("1 - Gerir Contas\n[Realizar consultas, alterações e operações financeiras numa conta]\n");
		System.out.println("2 - Gerir Clientes\n[Cadastros, consultas e alterações num cliente]\n");
		System.out.println("3 - Voltar");
		menu = ler.next();
		ler.nextLine();

		switch (menu) {
		case "1": {
			menuGerirContas(codBanco, codAgencia);
		}
			break;
		case "2": {
			menuGerirClientes(codBanco, codAgencia);
		}
			break;
		case "3": {
			AppGerirAgencia.main(objBanco);
		}
		default: {
			System.out.println("Opção inválida! Digite apenas o número 1, 2 ou 3.");
		}
		}
	}

	/**Método responsável por gerenciar contas de um determinado banco ou agencia.
	 * @param codAgencia int - código da agencia que a conta pertence ou o cleinte pertencem
	 * @param codBanco int - código do banco que a conta, o cliente ou a agencia pertencem */
	private static void menuGerirContas(int codBanco, int codAgencia) {
		Scanner ler = new Scanner(System.in);
		Contas conta = new Contas(0, codBanco, codAgencia, 0, 0, 0, 0, null, null);
		int codConta;
		String menu = null;
		boolean volta = true;

		System.out.println("Digite o número da conta:");
		codConta = ler.nextInt();

		conta = DbGetDadosContas.main(codConta, codBanco);
		if (conta.getDataCadastro() != null) {
			System.out.println("1 - Realizar alterações financeiras");
			System.out.println("2 - Consultar ou alterar dados da conta");
			System.out.println("3 - Voltar");
			menu = ler.next();
			ler.nextLine();

			switch (menu) {
			case "1": {
				while (volta == true) {
					volta = false;
					System.out.println("1 - Alterar saldo");
					System.out.println("2 - Alterar crédito");
					System.out.println("3 - Alterar dívida");
					System.out.println("4 - Voltar");
					menu = ler.nextLine();

					if (menu.equals("1")) {
						System.out.println("Saldo atual: R$" + MetodosAuxiliares.formatarDinheiro(conta.getSaldo()));
						System.out.println("Digite o novo valor de saldo: ");
						conta.setSaldo(ler.nextDouble());
						DbSetDadosContas.main(conta, codBanco);
						volta = true;
					} else if (menu.equals("2")) {
						System.out
								.println("Crédito atual: R$" + MetodosAuxiliares.formatarDinheiro(conta.getCredito()));
						System.out.println("Digite o novo valor de crédito: ");
						conta.setCredito(ler.nextDouble());
						DbSetDadosContas.main(conta, codBanco);
						volta = true;
					} else if (menu.equals("3")) {
						System.out.println("Dívida atual: R$" + MetodosAuxiliares.formatarDinheiro(conta.getDivida()));
						System.out.println("Digite o novo valor da dívida: ");
						conta.setDivida(ler.nextDouble());
						DbSetDadosContas.main(conta, codBanco);
						volta = true;
					} else if (menu.equals("4")) {
						menuGerirContas(codBanco, codAgencia);
					} else {
						System.out.println("Opção inválida! Digite apenas números de 1 a 4.");
						volta = true;
					}
				}
			}
				break;
			case "2": {

				while (volta == true) {
					volta = false;
					System.out.println("1 - Alterar senha");
					System.out.println("2 - Visualizar extrato");
					System.out.println("3 - Visualizar dados detalhados");
					System.out.println("4 - Voltar");
					menu = ler.nextLine();
					

					if (menu.equals("1")) {
						System.out.println("Digite a nova senha: ");
						conta.setSenha(ler.nextLine());
						DbSetDadosContas.main(conta, codBanco);
						volta = true;
					} else if (menu.equals("2")) {
						AppExibirExtrato.main(codConta, codBanco);
						volta = true;
					} else if (menu.equals("3")) {
						Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
						cliente = DbGetDadosClientes.main(conta.getCliente(), codBanco);
						System.out.println("Dados do cliente vinculado\n");
						if (cliente.getTipo() == 1) {
							System.out.println("Nome: " + cliente.getNome());
							System.out.println("CPF: " + cliente.getCpf());
							System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
						} else {
							System.out.println("Razão Social: " + cliente.getRazaoSocial());
							System.out.println("CNPJ: " + cliente.getCnpj());
							System.out.println("Nome Fantasia: " + cliente.getNomeFantasia());
						}
						System.out.println(
								"Renda Mensal: R$" + MetodosAuxiliares.formatarDinheiro(cliente.getRendaMensal()));
						System.out.println("Cliente desde: " + cliente.getDataCadastro());

						System.out.println("\n\nDados da conta\n");
						System.out.println("Banco '" + conta.getBanco() + "' | Agência: '" + conta.getAgencia() + "' | "
								+ "Conta: '" + conta.getCodigo() + "'");
						System.out.println("Saldo atual: R$" + MetodosAuxiliares.formatarDinheiro(conta.getSaldo()));
						System.out
								.println("Crédito atual: R$" + MetodosAuxiliares.formatarDinheiro(conta.getCredito()));
						System.out.println("Dívida atual: R$" + MetodosAuxiliares.formatarDinheiro(conta.getDivida()));
						System.out.println("Conta criada em: " + conta.getDataCadastro());
						System.out.println("\nPara voltar, pressione enter.");

						ler.nextLine();
						volta = true;

					} else if (menu.equals("4")) {
						menuGerirContas(codBanco, codAgencia);
					} else {
						System.out.println("Opção inválida! Digite apenas números de 1 a 4.");
						volta = true;
					}
				}

			}
				break;
			case "3": {
				AppGerirContasEClientes.main(codBanco, codAgencia);
			}
				break;
			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}
			}

		} else {
			System.out.println("Número da conta inválido ou a conta inexistente!");
			AppGerirContasEClientes.main(codBanco, codAgencia);
		}

	}

	/**Método responsável por gerenciar os clientes de um determinado banco ou agencia.
	 * @param codAgencia int - código da agencia que a conta pertence ou o cleinte pertencem
	 * @param codBanco int - código do banco que a conta, o cliente ou a agencia pertencem */
	private static void menuGerirClientes(int codBanco, int codAgencia) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		int codCliente = 0;
		boolean volta = true;
		System.out.println("1 - Cadastro de Clientes");
		System.out.println("2 - Consulta de Dados");
		System.out.println("3 - Voltar");
		menu = ler.next();
		ler.nextLine();

		switch (menu) {
		case "1": {
			while (volta = true) {
				volta = false;
				System.out.println("1 - Inserir cliente");
				System.out.println("2 - Alterar cliente");
				System.out.println("3 - Excluir cliente");
				System.out.println("4 - Voltar");
				menu = ler.nextLine();

				if (menu.equals("1")) {
					AppInserirCliente.main(codBanco);
					volta = true;
				} else if (menu.equals("2")) {
					codCliente = DbPesquisarCliente.main(codBanco);
					if (codCliente != 0) {
						AppAlterarCliente.main(codCliente, codBanco);
					}
					volta = true;
				} else if (menu.equals("3")) {
					codCliente = DbPesquisarCliente.main(codBanco);
					if (codCliente != 0) {
						AppExcluirCliente.main(codCliente, codBanco);
					}
					volta = true;
				} else if (menu.equals("4")) {
					menuGerirClientes(codBanco, codAgencia);
				} else {
					System.out.println("Opção inválida! Digite apenas números de 1 a 4.");
					volta = true;
				}
			}
		}
			break;
		case "2": {
			while (volta = true) {
				volta = false;
				System.out.println("1 - Visualizar dados detalhados de um cliente");
				System.out.println("2 - Visualizar extrato de um cliente");
				System.out.println("3 - Voltar");
				menu = ler.nextLine();

				if (menu.equals("1")) {
					codCliente = DbPesquisarCliente.main(codBanco);
					Clientes cliente = new Clientes(codCliente, 0, 0, null, null, null, 0, null, null, 0);
					cliente = DbGetDadosClientes.main(codCliente, codBanco);
					System.out.println("Dados do cliente\n");
					if (cliente.getTipo() == 1) {
						System.out.println("Nome: " + cliente.getNome());
						System.out.println("CPF: " + cliente.getCpf());
						System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
					} else {
						System.out.println("Razão Social: " + cliente.getRazaoSocial());
						System.out.println("CNPJ: " + cliente.getCnpj());
						System.out.println("Nome Fantasia: " + cliente.getNomeFantasia());
					}
					System.out
							.println("Renda Mensal: R$" + MetodosAuxiliares.formatarDinheiro(cliente.getRendaMensal()));
					System.out.println("Cliente desde: " + cliente.getDataCadastro());

					System.out.println("\nPara voltar, pressione enter.");
					ler.nextLine();
					volta = true;

				} else if (menu.equals("2")) {
					codCliente = DbPesquisarCliente.main(codBanco);
					ArrayList<Contas> contas = new ArrayList<Contas>();
					contas = DbGetContasCliente.main(codBanco, codCliente);

					for (int i = 0; i < contas.size(); i++) {
						AppExibirExtrato.main(contas.get(i).getCodigo(), codBanco);
					}

					System.out.println("\nPara voltar, pressione enter.");
					ler.nextLine();
					volta = true;

				} else if (menu.equals("3")) {
					menuGerirClientes(codBanco, codAgencia);
				} else {
					System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
					volta = true;
				}
			}
		}
			break;
		case "3": {
			AppGerirContasEClientes.main(codBanco, codAgencia);
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			menuGerirClientes(codBanco, codAgencia);
		}
		}

	}
}
