/**
 * 
 */
package projeto.banco.poo.app;

import java.util.ArrayList;
import java.util.Scanner;

import projeto.banco.poo.appaux.AppAlterarCliente;
import projeto.banco.poo.appaux.AppExcluirCliente;
import projeto.banco.poo.appaux.AppExibirExtrato;
import projeto.banco.poo.appaux.AppGerirAgencia;
import projeto.banco.poo.appaux.AppInserirCliente;
import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetContasCliente;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbGetDadosContas;
import projeto.banco.poo.db.DbPesquisarCliente;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 15 de mar de 2016
 */
public class AppPrincipal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;

		while (volta = true) {
			volta = false;
			System.out.println("Projeto App Banco - Universidade Federal de Sergipe - DCOMP\n");
			System.out.println("Programação Orientada a Objetos	  -   Turma 02   -   2015.2\n");
			System.out.println("Docente: Kalil Araujo Bispo\n");
			System.out.println("Discentes: Bruno Rodrigues dos Santos  -  Eric Fonseca Lima\n\n");
			System.out.println("1 - Cadastros");
			System.out.println("2 - Operações");
			System.out.println("3 - Menu Interativo (opcional)");
			System.out.println("4 - Sair");
			menu = ler.nextLine();

			switch (menu) {
			case "1": {
				menuCadastros();
			}
				break;
			case "2": {
				menuOperacoes();
			}
				break;
			case "3": {
				AppInicio.main(args);
			}
				break;
			case "4": {
				System.exit(1);
			}
			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}
			}
		}

	}

	private static void menuCadastros() {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;
		int codCliente = 0;

		System.out.println("1 - Cadastro de bancos");
		System.out.println("2 - Cadastro de agências");
		System.out.println("3 - Cadastro de clientes");
		System.out.println("4 - Cadastro de contas");
		System.out.println("5 - Voltar");
		menu = ler.nextLine();

		switch (menu) {
		case "1": {
			AppBancoCentral.menuCadastroDeBancos();
		}
			break;
		case "2": {
			Banco banco = new Banco(0, null, null, null, null, null);

			System.out.println("Digite o código do banco que deseja conectar-se: ");
			banco.setCodigo(ler.nextInt());
			ler.nextLine();

			if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
				banco = DbGetDadosBancos.main(banco.getCodigo());
				AppBanco.menuCadastroDeAgencias(banco.getCodigo());
			}
		}
			break;
		case "3": {
			Banco banco = new Banco(0, null, null, null, null, null);

			System.out.println("Digite o código do banco que deseja conectar-se: ");
			banco.setCodigo(ler.nextInt());
			ler.nextLine();

			if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
				banco = DbGetDadosBancos.main(banco.getCodigo());

				while (volta = true) {
					volta = false;
					System.out.println("1 - Inserir cliente");
					System.out.println("2 - Alterar cliente");
					System.out.println("3 - Excluir cliente");
					System.out.println("4 - Voltar");
					menu = ler.nextLine();

					if (menu == "1") {
						AppInserirCliente.main(banco.getCodigo());
						volta = true;
					} else if (menu == "2") {
						codCliente = DbPesquisarCliente.main(banco.getCodigo());
						if (codCliente != 0) {
							AppAlterarCliente.main(codCliente, banco.getCodigo());
						}
						volta = true;
					} else if (menu == "3") {
						codCliente = DbPesquisarCliente.main(banco.getCodigo());
						if (codCliente != 0) {
							AppExcluirCliente.main(codCliente, banco.getCodigo());
						}
						volta = true;
					}
				}
			}
		}
			break;
		case "4": {
			Banco banco = new Banco(0, null, null, null, null, null);
			System.out.println("Digite o código do banco que deseja conectar-se: ");
			banco.setCodigo(ler.nextInt());
			ler.nextLine();

			if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
				banco = DbGetDadosBancos.main(banco.getCodigo());

				Agencia agencia = new Agencia(0, banco.getCodigo(), null, null);
				System.out.println("Digite o código da agência que deseja conectar-se:");
				agencia.setCodigo(ler.nextInt());
				ler.nextLine();

				if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
					agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());
					AppGerirAgencia.menuCadastroContas(agencia, banco);
				} else {
					System.out.println("Agência não encontrada");
				}
			} else {
				System.out.println("Banco não encontrado.");
			}
		}
			break;
		case "5": {
			// voltar
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 5.");
		}
		}
	}

	private static void menuOperacoes() {
		Scanner ler = new Scanner(System.in);
		String menu = null;

		System.out.println("1 - Consulta de dados");
		System.out.println("2 - Realizar operações financeiras");
		System.out.println("3 - Voltar");
		menu = ler.nextLine();

		switch (menu) {
		case "1": {
			
			System.out.println("1 - Exibir o montante de dinheiro aplicado no banco");
			System.out.println("2 - Exibir o montante de dinheiro aplicado numa agência");
			System.out.println("3 - Exibir o saldo de um cliente numa agência");
			System.out.println("4 - Exibir o saldo de um cliente em mais de uma agência ao mesmo tempo");
			System.out.println("5 - Exibir extrato detalhado de uma conta de um cliente");
			System.out.println("6 - Exibir o extrato detalhado de todas as contas de um cliente");
			System.out.println("7 - Voltar");
			menu = ler.nextLine();

			if (menu.equals("1")) {
				Banco banco = new Banco(0, null, null, null, null, null);

				System.out.println("Digite o código do banco que deseja conectar-se: ");
				banco.setCodigo(ler.nextInt());
				ler.nextLine();

				if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
					banco = DbGetDadosBancos.main(banco.getCodigo());
					System.out.println(
							"Montante aplicado no banco: R$" + MetodosAuxiliares.formatarDinheiro(MetodosAuxiliares.getMontanteBanco(banco.getCodigo())));
				}

			} else if (menu.equals("2")) {
				Banco banco = new Banco(0, null, null, null, null, null);
				System.out.println("Digite o código do banco que deseja conectar-se: ");
				banco.setCodigo(ler.nextInt());
				ler.nextLine();

				if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
					banco = DbGetDadosBancos.main(banco.getCodigo());

					Agencia agencia = new Agencia(0, banco.getCodigo(), null, null);
					System.out.println("Digite o código da agência que deseja conectar-se:");
					agencia.setCodigo(ler.nextInt());
					ler.nextLine();

					if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
						agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());
						System.out.println("Montante aplicado na agência: R$"
								+ MetodosAuxiliares.formatarDinheiro(MetodosAuxiliares.getMontanteAgencia(banco.getCodigo(), agencia.getCodigo())));
					} else {
						System.out.println("Agência não encontrada");
					}
				} else {
					System.out.println("Banco não encontrado.");
				}

			} else if (menu.equals("3")) {
				Banco banco = new Banco(0, null, null, null, null, null);
				System.out.println("Digite o código do banco que deseja conectar-se: ");
				banco.setCodigo(ler.nextInt());
				ler.nextLine();

				if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
					banco = DbGetDadosBancos.main(banco.getCodigo());

					Agencia agencia = new Agencia(0, banco.getCodigo(), null, null);
					System.out.println("Digite o código da agência que deseja conectar-se:");
					agencia.setCodigo(ler.nextInt());
					ler.nextLine();

					if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
						agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());

						Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
						cliente = DbGetDadosClientes.main(DbPesquisarCliente.main(banco.getCodigo()), banco.getCodigo());

						if (cliente.getDataCadastro() != null) {
							System.out.println("Saldo total do cliente na agência '" + agencia.getCodigo() + "': "
									+ "R$" + MetodosAuxiliares.formatarDinheiro(MetodosAuxiliares.getSaldoTotalClienteAg(banco.getCodigo(),
											agencia.getCodigo(), cliente.getCodigo())));
						} else {
							System.out.println("Cliente não encontrado");
						}

					} else {
						System.out.println("Agência não encontrada");
					}
				} else {
					System.out.println("Banco não encontrado.");
				}
			} else if (menu.equals("4")) {
				Banco banco = new Banco(0, null, null, null, null, null);
				System.out.println("Digite o código do banco que deseja conectar-se: ");
				banco.setCodigo(ler.nextInt());
				ler.nextLine();

				if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
					banco = DbGetDadosBancos.main(banco.getCodigo());

						Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
						cliente = DbGetDadosClientes.main(DbPesquisarCliente.main(banco.getCodigo()), banco.getCodigo());

						if (cliente.getDataCadastro() != null) {
							System.out.println("Saldo total do cliente no banco: " + "R$"
									+ MetodosAuxiliares.formatarDinheiro(MetodosAuxiliares.getSaldoTotalCliente(banco.getCodigo(), cliente.getCodigo())));
						} else {
							System.out.println("Cliente não encontrado");
						}

					} else {
					System.out.println("Banco não encontrado.");
				}
			} else if (menu.equals("5")) {

				Banco banco = new Banco(0, null, null, null, null, null);
				System.out.println("Digite o código do banco que deseja conectar-se: ");
				banco.setCodigo(ler.nextInt());
				ler.nextLine();

				if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
					banco = DbGetDadosBancos.main(banco.getCodigo());

					Agencia agencia = new Agencia(0, banco.getCodigo(), null, null);
					System.out.println("Digite o código da agência:");
					agencia.setCodigo(ler.nextInt());
					ler.nextLine();

					if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
						agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());

						Contas conta = new Contas(0, banco.getCodigo(), agencia.getCodigo(), 0, 0, 0, 0, null, null);
						System.out.println("Digite o número da conta:");
						conta.setCodigo(ler.nextInt());
						conta = DbGetDadosContas.main(conta.getCodigo(), banco.getCodigo());

						if (conta.getDataCadastro() != null) {
							AppExibirExtrato.main(conta.getCodigo(), banco.getCodigo());
						} else {
							System.out.println("Conta não encontrada.");
						}

					} else {
						System.out.println("Agência não encontrada");
					}
				} else {
					System.out.println("Banco não encontrado.");
				}
			} else if (menu.equals("6")) {
				Banco banco = new Banco(0, null, null, null, null, null);
				System.out.println("Digite o código do banco que deseja conectar-se: ");
				banco.setCodigo(ler.nextInt());
				ler.nextLine();

				if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
					banco = DbGetDadosBancos.main(banco.getCodigo());

					Agencia agencia = new Agencia(0, banco.getCodigo(), null, null);
					System.out.println("Digite o código da agência que deseja conectar-se:");
					agencia.setCodigo(ler.nextInt());
					ler.nextLine();

					if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
						agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());

						Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
						cliente.setCodigo(DbPesquisarCliente.main(banco.getCodigo()));

						if (cliente.getDataCadastro() != null) {
							cliente = DbGetDadosClientes.main(cliente.getCodigo(), banco.getCodigo());
							ArrayList<Contas> contas = new ArrayList<Contas>();
							contas = DbGetContasCliente.main(banco.getCodigo(), cliente.getCodigo());

							for (int i = 0; i < contas.size(); i++) {
								AppExibirExtrato.main(contas.get(i).getCodigo(), banco.getCodigo());
							}
						} else {
							System.out.println("Cliente não encontrado");
						}

					} else {
						System.out.println("Agência não encontrada");
					}
				} else {
					System.out.println("Banco não encontrado.");
				}

			} else if (menu.equals("7")) {
				// voltar
			} else {
				System.out.println("Opção inválida! Digite apenas números de 1 a 7.");
			}
		}
			break;
		case "2": {
			AppCaixa.main();
		}
			break;
		case "3": {

		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
		}
		}

	}
}
