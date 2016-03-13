/**
 * 
 */
package projeto.banco.poo.app;

import java.util.Scanner;

import projeto.banco.poo.appaux.AppInserirCliente;
import projeto.banco.poo.appaux.AppInserirConta;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetCodigoNovoCliente;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbGetDadosContas;
import projeto.banco.poo.db.DbPesquisarCliente;
import projeto.banco.poo.db.DbPrimeiraConexao;
import projeto.banco.poo.db.DbSetDadosContas;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 */
public class AppCaixa {

	/**
	 * @param args
	 */
	public static boolean main() {

		Contas conta = new Contas(0, 0, 0, 0, 0, 0, 0, null, null);
		Contas conta2 = new Contas(0, 0, 0, 0, 0, 0, 0, null, null);
		boolean retorno = false;
		Scanner ler = new Scanner(System.in);
		byte menu = 0;
		int i = 0;

		for (i = 0; i < 10; i++) {
			if (DbGetCodigoBanco.main(i) == true)
				break;
		}
		if (DbPrimeiraConexao.main() == true) {
			System.out.println("ERRO: Não há banco cadastrado!");
			retorno = true;
		} else {
			System.out.println("Digite o número do banco que deseja se conectar:");
			conta.setBanco(ler.nextInt());
			conta2.setBanco(conta.getBanco());

			if (DbGetCodigoBanco.main(conta.getCodigo()) == false) {

				System.out.println();
				System.out.println("Escolha uma opção:");
				System.out.println();
				System.out.println("1 - Depósito");
				System.out.println("2 - Saque");
				System.out.println("3 - Transferência");
				System.out.println("4 - Pagamento");
				System.out.println("5 - Sair");
				menu = ler.nextByte();

				switch (menu) {

				// Depositar:
				case 1: {
					menuDepositar(conta);
				}
					break;

				// Sacar:
				case 2: {
					menuSacar(conta);
				}
					break;

				// Transferir:
				case 3: {
					menuTransferir(conta, conta2);
				}
					break;

				// Pagar
				case 4: {
					menuPagar(conta);
				}
					break;
				}
			} else {
				System.out.println("Erro: O banco digitado não existe!");
			}
		}

		return retorno;
	}

	public static void menuDepositar(Contas conta) {

		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o número da conta que deseja efetuar o depósito: ");
		conta.setCodigo(ler.nextInt());

		System.out.println("Digite o número da agência da conta:");
		conta.setAgencia(ler.nextInt());

		System.out.println("Digite o valor do depósito: ");
		double valorDeposito = ler.nextDouble();
		ler.nextLine();

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());
		Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

		System.out.println("Informações da conta:");
		if (cliente.getTipo() == 1) {
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
		} else {
			System.out.println("Razão Social: " + cliente.getRazaoSocial());
			System.out.println("CNPJ: " + cliente.getCnpj());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
		}
		System.out.println();
		System.out.println("Deseja confirmar o depósito?");
		System.out.println();
		System.out.println("1 - Sim");
		System.out.println("2 - Não");
		byte opcao = ler.nextByte();
		ler.nextLine();

		if (opcao == 1) {
			conta.setSaldo(conta.getSaldo() + valorDeposito);
			DbSetDadosContas.main(conta, conta.getBanco());
		}
		if (opcao == 2) {
			// Voltar
		} else if (opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida!");
			// Voltar
		}
	}

	public static void menuSacar(Contas conta) {
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o número da conta que deseja efetuar o saque: ");
		conta.setCodigo(ler.nextInt());

		System.out.println("Digite o número da agência da conta:");
		conta.setAgencia(ler.nextInt());

		System.out.println("Selecione o tipo de saque: ");
		System.out.println("1 - Sacar a partir do saldo da conta");
		System.out.println("2 - Sacar a partir do crédito disponível da conta");
		byte opcao = ler.nextByte();

		System.out.println("Digite o valor do saque: ");
		double valorSaque = ler.nextDouble();
		ler.nextLine();

		if (opcao == 1) {
			conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());
			Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
			cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

			System.out.println("Informações da conta:");
			if (cliente.getTipo() == 1) {
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("CPF: " + cliente.getCpf());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			} else {
				System.out.println("Razão Social: " + cliente.getRazaoSocial());
				System.out.println("CNPJ: " + cliente.getCnpj());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			}
			System.out.println();
			System.out.println("Deseja confirmar o saque?");
			System.out.println();
			System.out.println("1 - Sim");
			System.out.println("2 - Não");
			opcao = ler.nextByte();
			ler.nextLine();

			if (opcao == 1) {
				if (conta.getSaldo() >= valorSaque) {
					conta.setSaldo(conta.getSaldo() - valorSaque);
					DbSetDadosContas.main(conta, conta.getBanco());
				} else {
					System.out.println("Erro: O valor solicitado é maior que o disponível!");
					// voltar
				}
			}
			if (opcao == 2) {
				// Voltar
			} else if (opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida!");
				// Voltar
			}
		}

		if (opcao == 2) {
			conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());
			Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
			cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

			System.out.println("Infotmações da conta:");
			if (cliente.getTipo() == 1) {
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("CPF: " + cliente.getCpf());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			} else if (opcao != 1 || opcao != 2) {
				System.out.println("Razão Social: " + cliente.getRazaoSocial());
				System.out.println("CNPJ: " + cliente.getCnpj());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			}
			System.out.println();
			System.out.println("Deseja confirmar o saque?");
			System.out.println();
			System.out.println("1 - Sim");
			System.out.println("2 - Não");
			opcao = ler.nextByte();

			if (opcao == 1) {
				if (conta.getCredito() >= valorSaque) {
					conta.setCredito(conta.getCredito() - valorSaque);
					DbSetDadosContas.main(conta, conta.getBanco());
				} else {
					System.out.println("Erro: O valor solicitado é maior que o disponível!");
					// Voltar
				}
			}
			if (opcao == 2) {
				// Voltar
			} else if (opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida!");
				// Voltar
			}
		} else if (opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida!");
			// Voltar
		}
	}

	public static void menuTransferir(Contas conta, Contas conta2) {
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o número da sua conta: ");
		conta.setCodigo(ler.nextInt());

		System.out.println("Digite o número da agência da sua conta:");
		conta.setAgencia(ler.nextInt());

		System.out.println("Digite o número da conta que deseja transferir o valor: ");
		conta2.setCodigo(ler.nextInt());

		System.out.println("Digite o número da agência da conta:");
		conta2.setAgencia(ler.nextInt());

		System.out.println("Digite o valor que deseja transferir: ");
		double valorTransferencia = ler.nextDouble();
		ler.nextLine();

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());
		conta2 = DbGetDadosContas.main(conta2.getCodigo(), conta2.getBanco());
		Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		Clientes cliente2 = new Clientes(conta2.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());
		cliente2 = DbGetDadosClientes.main(cliente2.getCodigo(), conta2.getBanco());

		System.out.println("Informações das contas:");
		if (cliente.getTipo() == 1) {
			System.out.println("Debitado:");
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			System.out.println();
		} else {
			System.out.println("Debitado:");
			System.out.println("Razão Social: " + cliente.getRazaoSocial());
			System.out.println("CNPJ: " + cliente.getCnpj());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			System.out.println();
		}
		if (cliente2.getTipo() == 1) {
			System.out.println("Favorecido:");
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
		} else {
			System.out.println("Favorecido:");
			System.out.println("Razão Social: " + cliente.getRazaoSocial());
			System.out.println("CNPJ: " + cliente.getCnpj());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
		}
		System.out.println();
		System.out.println("Deseja confirmar a operação?");
		System.out.println();
		System.out.println("1 - Sim");
		System.out.println("2 - Não");
		byte opcao = ler.nextByte();
		ler.nextLine();

		if (opcao == 1) {
			if (conta.getSaldo() >= valorTransferencia) {
				conta.setSaldo(conta.getSaldo() - valorTransferencia);
				conta2.setSaldo(conta2.getSaldo() + valorTransferencia);
				DbSetDadosContas.main(conta, conta.getBanco());
				DbSetDadosContas.main(conta2, conta2.getBanco());
			} else {
				System.out.println("Erro: O valor solicitado é maior que o disponível!");
				// voltar
			}
		}
		if (opcao == 2) {
			// Voltar
		} else if(opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida!");
			// Voltar
		}
	}

	public static void menuPagar(Contas conta) {
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o número da conta que deseja utilizar para efetuar o pagamento: ");
		conta.setCodigo(ler.nextInt());

		System.out.println("Digite o número da agência da conta:");
		conta.setAgencia(ler.nextInt());

		System.out.println("Digite o valor do pagamento: ");
		double valorPagamento = ler.nextDouble();
		ler.nextLine();

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());
		Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

		System.out.println("Informações da conta:");
		if (cliente.getTipo() == 1) {
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
		} else {
			System.out.println("Razão Social: " + cliente.getRazaoSocial());
			System.out.println("CNPJ: " + cliente.getCnpj());
		}
		System.out.println();
		System.out.println("Deseja confirmar o pagamento?");
		System.out.println();
		System.out.println("1 - Sim");
		System.out.println("2 - Não");
		byte opcao = ler.nextByte();
		ler.nextLine();

		if (opcao == 1) {
			conta.setSaldo(conta.getSaldo() - valorPagamento);
			DbSetDadosContas.main(conta, conta.getBanco());
		}
		if (opcao == 2) {
			// Voltar
		} else if (opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida!");
			// Voltar
		}
	}
}
