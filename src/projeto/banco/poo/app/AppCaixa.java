/**
 * 
 */
package projeto.banco.poo.app;

import java.util.Scanner;


import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbGetDadosContas;
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
				System.out.println("4 - Pagamento de fatura");
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

		System.out.println("OPERAÇÃO DEPÓSITO\n\n");
		
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o número da agência: ");
		int codAgencia = ler.nextInt();

		System.out.println("Digite o número da conta que deseja efetuar o depósito:");
		conta.setCodigo(ler.nextInt());

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());
		Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

		if (conta.getCliente() == 0) {
			System.out.println("Tente Novamente!");
			AppCaixa.main();
		} else if (codAgencia != conta.getAgencia()) {
			System.out.println("Agência inexistente.\nTente Novamente!");
			AppCaixa.main();
		}

		System.out.println("Digite o valor do depósito: ");
		double valorDeposito = ler.nextDouble();
		ler.nextLine();

		System.out.println("Informações da conta:");
		if (cliente.getTipo() == 1) {
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			System.out.println("Valor do depósito: " + valorDeposito);
		} else {
			System.out.println("Razão Social: " + cliente.getRazaoSocial());
			System.out.println("CNPJ: " + cliente.getCnpj());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			System.out.println("Valor do depósito: R$" + valorDeposito);
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
			System.out.println("Operação cancelada pelo usuário!");
			AppCaixa.main();
		} else if (opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida!\nTente Novamente");
			AppCaixa.main();
		}
	}

	public static void menuSacar(Contas conta) {
		Scanner ler = new Scanner(System.in);

		System.out.println("OPERAÇÃO SAQUE\n\n");
		
		System.out.println("Digite o número da agência da conta:");
		int codAgencia = ler.nextInt();
		
		System.out.println("Digite o número da conta que deseja efetuar o saque: ");
		conta.setCodigo(ler.nextInt());

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());
		
		if (conta.getCliente() == 0) {
			System.out.println("Tente Novamente!");
			AppCaixa.main();
		} else if (codAgencia != conta.getAgencia()) {
			System.out.println("Aência inexistente.\nTente Novamente!");
			AppCaixa.main();
		}

		System.out.println("Selecione o tipo de saque: ");
		System.out.println("1 - Sacar a partir do saldo da conta");
		System.out.println("2 - Sacar a partir do crédito disponível da conta");
		System.out.println("3 - Sair");
		byte opcao = ler.nextByte();

		if (opcao == 1) {

			Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
			cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

			System.out.println("Digite o valor do saque: ");
			double valorSaque = ler.nextDouble();
			ler.nextLine();

			System.out.println("Informações da conta:");
			if (cliente.getTipo() == 1) {
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("CPF: " + cliente.getCpf());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
				System.out.println("Saldo da conta: R$" + conta.getSaldo());
				System.out.println("Valor a ser sacado: R$" + valorSaque);
			} else {
				System.out.println("Razão Social: " + cliente.getRazaoSocial());
				System.out.println("CNPJ: " + cliente.getCnpj());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
				System.out.println("Saldo da conta: R$" + conta.getSaldo());
				System.out.println("Valor a ser sacado: R$" + valorSaque);
			}
			System.out.println();
			System.out.println("Deseja confirmar o saque?");
			System.out.println();
			System.out.println("1 - Sim");
			System.out.println("2 - Não");
			opcao = ler.nextByte();
			ler.nextLine();

			if (opcao == 1) {

				System.out.println("Digite a senha da conta: ");
				String senha = ler.next();
				ler.nextLine();

				if (conta.getSenha().equals(senha)) {
					if (conta.getSaldo() >= valorSaque) {
						conta.setSaldo(conta.getSaldo() - valorSaque);
						DbSetDadosContas.main(conta, conta.getBanco());
					} else {
						System.out.println("Erro: O valor solicitado é maior que o disponível!");
						menuSacar(conta);
					}
				} else {
					System.out.println("Erro: Senha incorreta!");
					menuSacar(conta);
				}
			}
			if (opcao == 2) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			} else if (opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida!");
				menuSacar(conta);
			}
		}

		if (opcao == 2) {

			Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
			cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

			System.out.println("Digite o valor do saque: ");
			double valorSaque = ler.nextDouble();
			ler.nextLine();

			System.out.println("Informações da conta:");
			if (cliente.getTipo() == 1) {
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("CPF: " + cliente.getCpf());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
				System.out.println("Saldo da conta: R$" + conta.getSaldo());
				System.out.println("Valor a ser sacado: R$" + valorSaque);
			} else if (opcao != 1 || opcao != 2) {
				System.out.println("Razão Social: " + cliente.getRazaoSocial());
				System.out.println("CNPJ: " + cliente.getCnpj());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
				System.out.println("Saldo da conta: R$" + conta.getSaldo());
				System.out.println("Valor a ser sacado: R$" + valorSaque);
			}
			System.out.println();
			System.out.println("Deseja confirmar o saque?");
			System.out.println();
			System.out.println("1 - Sim");
			System.out.println("2 - Não");

			opcao = ler.nextByte();

			if (opcao == 1) {

				System.out.println("Digite a senha da conta: ");
				String senha = ler.next();
				ler.nextLine();

				if (conta.getSenha().equals(senha)) {

					if (conta.getCredito() >= valorSaque) {
						conta.setCredito(conta.getCredito() - valorSaque);
						conta.setDivida(conta.getDivida() + valorSaque);
						DbSetDadosContas.main(conta, conta.getBanco());
					} else {
						System.out.println("Erro: O valor solicitado é maior que o disponível!");
						menuSacar(conta);
					}

				} else {
					System.out.println("Erro: Senha incorreta!");
					menuSacar(conta);
				}
			}
			if (opcao == 2) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			} else if (opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida!");
				menuSacar(conta);
			}
		}
		if (opcao == 3) {
			AppCaixa.main();
		} else if (opcao != 1 && opcao != 2 && opcao != 3) {
			System.out.println("Opção inválida!");
			menuSacar(conta);
		}
	}

	public static void menuTransferir(Contas conta, Contas conta2) {
		
		System.out.println("OPERAÇÃO TRANSFERIR\n\n");
		
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o número da sua agência:");
		int codAgencia = ler.nextInt();

		System.out.println("Digite o número da sua conta: ");
		conta.setCodigo(ler.nextInt());

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());

		if (conta.getCliente() == 0) {
			System.out.println("Tente Novamente!");
			AppCaixa.main();
		} else if (codAgencia != conta.getAgencia()) {
			System.out.println("Agência inexistente.\nTente Novamente!");
			AppCaixa.main();
		}

		System.out.println("Digite o número da agência da conta favorecida:");
		int codAgencia2 = ler.nextInt();

		System.out.println("Digite o número da conta do favorecido: ");
		conta2.setCodigo(ler.nextInt());

		conta2 = DbGetDadosContas.main(conta2.getCodigo(), conta2.getBanco());

		if (conta2.getCliente() == 0) {
			System.out.println("Tente Novamente!");
			AppCaixa.main();
		} else if (codAgencia2 != conta2.getAgencia()) {
			System.out.println("Aência inexistente.\nTente Novamente!");
			AppCaixa.main();
		}

		Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		Clientes cliente2 = new Clientes(conta2.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());
		cliente2 = DbGetDadosClientes.main(cliente2.getCodigo(), conta2.getBanco());

		System.out.println("Digite o valor que deseja transferir: ");
		double valorTransferencia = ler.nextDouble();
		ler.nextLine();

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
			System.out.println("Nome: " + cliente2.getNome());
			System.out.println("CPF: " + cliente2.getCpf());
			System.out.println("Conta: " + conta2.getCodigo() + " | Agencia: " + conta2.getAgencia());
		} else {
			System.out.println("Favorecido:");
			System.out.println("Razão Social: " + cliente2.getRazaoSocial());
			System.out.println("CNPJ: " + cliente2.getCnpj());
			System.out.println("Conta: " + conta2.getCodigo() + " | Agencia: " + conta2.getAgencia());
		}

		System.out.println();
		System.out.println("Valor da transferência: R$" + valorTransferencia);
		System.out.println();
		System.out.println("Deseja confirmar a operação?");
		System.out.println();
		System.out.println("1 - Sim");
		System.out.println("2 - Não");
		byte opcao = ler.nextByte();
		ler.nextLine();

		if (opcao == 1) {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.next();
			ler.nextLine();

			if (conta.getSenha().equals(senha)) {
				if (conta.getSaldo() >= valorTransferencia) {
					conta.setSaldo(conta.getSaldo() - valorTransferencia);
					conta2.setSaldo(conta2.getSaldo() + valorTransferencia);
					DbSetDadosContas.main(conta, conta.getBanco());
					DbSetDadosContas.main(conta2, conta2.getBanco());
				} else {
					System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente");
					AppCaixa.main();
				}
			} else {
				System.out.println("Erro: Senha incorreta!\nTente Novamente");
				AppCaixa.main();
			}

		}
		if (opcao == 2) {
			System.out.println("Operação cancelada pelo usuário!");
			AppCaixa.main();
		} else if (opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida!");
			menuTransferir(conta, conta2);
		}
	}

	public static void menuPagar(Contas conta) {
		
		System.out.println("OPERAÇÃO PAGAMENTO\n\n");
		
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o número da agência da conta:");
		int codAgencia = ler.nextInt();
		
		System.out.println("Digite o número da conta que deseja utilizar para efetuar o pagamento: ");
		conta.setCodigo(ler.nextInt());

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());
		
		if (conta.getCliente() == 0) {
			System.out.println("Tente Novamente!");
			AppCaixa.main();
		} else if (codAgencia != conta.getAgencia()) {
			System.out.println("Agência inexistente.\nTente Novamente!");
			AppCaixa.main();
		}

		System.out.println("O valor da dívida dessa conta é: R$" + conta.getDivida());

		System.out.println("Digite o valor que deseja pagar:\n[Para sair, digite um valor superior ao da dívida]");

		double valorPagamento = ler.nextDouble();
		ler.nextLine();

		if (valorPagamento > conta.getDivida()) {
			System.out.println("Erro: Valor maior que a dívida existente!");
			AppCaixa.main();
		}

		Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

		System.out.println("Informações da conta:");
		if (cliente.getTipo() == 1) {
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("O valor da dívida: " + conta.getDivida());
			System.out.println("Valor a pagar: R$" + valorPagamento);
		} else {
			System.out.println("Razão Social: " + cliente.getRazaoSocial());
			System.out.println("CNPJ: " + cliente.getCnpj());
			System.out.println("O valor da dívida: " + conta.getDivida());
			System.out.println("Valor a pagar: R$" + valorPagamento);
		}
		System.out.println();
		System.out.println("Deseja confirmar o pagamento?");
		System.out.println();
		System.out.println("1 - Sim");
		System.out.println("2 - Não");
		byte opcao = ler.nextByte();
		ler.nextLine();

		if (opcao == 1) {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.next();
			ler.nextLine();

			if (conta.getSenha().equals(senha)) {
				conta.setSaldo(conta.getSaldo() - valorPagamento);
				conta.setDivida(conta.getDivida() - valorPagamento);
				conta.setCredito(conta.getCredito() + valorPagamento);
				DbSetDadosContas.main(conta, conta.getBanco());
			} else {
				System.out.println("Erro: Senha incorreta!\nTente Novamente!");
				AppCaixa.main();
			}

		}
		if (opcao == 2) {
			System.out.println("Operação cancelada pelo usuário!");
			AppCaixa.main();
		} else if (opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida!\nTente Novamente");
			AppCaixa.main();
		}
	}

}
