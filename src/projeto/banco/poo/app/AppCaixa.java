/** 
 */
package projeto.banco.poo.app;

import java.util.Scanner;
import java.text.DecimalFormat;
import projeto.banco.poo.appaux.AppExibirExtrato;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.core.Operacoes;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetCodigoNovaOperacao;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbGetDadosContas;
import projeto.banco.poo.db.DbInserirOperacao;
import projeto.banco.poo.db.DbPrimeiraConexao;
import projeto.banco.poo.db.DbSetDadosContas;

/**
 * Classe para aplicação de operações bancárias, onde estão contidos métodos
 * relacionados à esse objetivo.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class AppCaixa {

	/**Método principal, responsável por chamar os métodos de operações bancárias.
	 * @return boolean - retorno */
	public static boolean main() {

		Contas conta = new Contas(0, 0, 0, 0, 0, 0, 0, null, null);
		Contas conta2 = new Contas(0, 0, 0, 0, 0, 0, 0, null, null);
		Banco banco = new Banco(0, null, null, null, null, null);
		boolean retorno = false;
		Scanner ler = new Scanner(System.in);
		String menu = null;
		int i = 0;

		for (i = 0; i < 10; i++) {
			if (DbGetCodigoBanco.main(i) == true)
				break;
		}
		if (DbPrimeiraConexao.main() == true) {
			System.out.println("ERRO: Não há banco cadastrado!");
			retorno = true;
		} else {

			System.out.println("Cliente, digite o número do seu banco:");
			banco.setCodigo(ler.nextInt());
			ler.nextLine();

			conta.setBanco(banco.getCodigo());
			conta2.setBanco(conta.getBanco());

			if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
				banco = DbGetDadosBancos.main(banco.getCodigo());

				System.out.println();
				System.out.println("Escolha uma opção:");
				System.out.println();
				System.out.println("1 - Depósito");
				System.out.println("2 - Saque");
				System.out.println("3 - Transferência");
				System.out.println("4 - Pagamento");
				System.out.println("5 - Realizar consulta");
				System.out.println("6 - Sair");
				menu = ler.nextLine();

				switch (menu) {

				// Depositar:
				case "1": {
					menuDepositar(conta);
				}
					break;

				// Sacar:
				case "2": {
					menuSacar(conta);
				}
					break;

				// Transferir:
				case "3": {
					menuTransferir(conta, conta2);
				}
					break;

				// Pagar
				case "4": {
					menuPagar(conta);
				}
					break;

				// Consultar
				case "5": {
					menuConsultar(conta);
				}
					break;

				// Voltar
				case "6": {
					AppInicio.main(null);
				}
					break;

				default: {
					System.out.println("Erro, digite entre as opções 1 e 6!");
					AppCaixa.main();
				}
				}
			} else {
				System.out.println("Erro: O banco digitado não existe!");
			}
		}

		return retorno;
	}

	/** Método para realizar o depósito de um valor em uma conta.
	 *  @param conta Contas - número da conta favorecida */
	private static void menuDepositar(Contas conta) {

		System.out.println("\nDEPÓSITO EM CONTA\n");

		Scanner ler = new Scanner(System.in);

		System.out.println("Insira os dados do favorecido:\n");

		System.out.println("Digite o número da agência: ");
		int codAgencia = ler.nextInt();

		System.out.println("Digite o número da conta:");
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

		if (valorDeposito == 0) {
			System.out.println("O valor precisa ser diferente de R$0,00\nTente Novamente!");
			AppCaixa.main();
		}

		System.out.println("Favorecido:");
		if (cliente.getTipo() == 1) {
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			System.out.println("Valor do depósito: R$" + MetodosAuxiliares.formatarDinheiro(valorDeposito));
		} else {
			System.out.println("Razão Social: " + cliente.getRazaoSocial());
			System.out.println("CNPJ: " + cliente.getCnpj());
			System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
			System.out.println("Valor do depósito: R$" + MetodosAuxiliares.formatarDinheiro(valorDeposito));
		}
		System.out.println();
		System.out.println("Deseja confirmar o depósito?");
		System.out.println();
		System.out.println("1 - Sim");
		System.out.println("2 - Não");
		byte opcao = ler.nextByte();

		if (opcao == 1) {
			conta.setSaldo(conta.getSaldo() + valorDeposito);
			DbSetDadosContas.main(conta, conta.getBanco());
			Operacoes operacao = new Operacoes(0, 1, valorDeposito, conta.getBanco(), conta.getAgencia(),
					conta.getCodigo(), 0, 0, 0, MetodosAuxiliares.getDataAtual());
			operacao.setCodigo(DbGetCodigoNovaOperacao.main(conta.getBanco()) + 1);
			operacao.setSaldoConta(conta.getSaldo());
			DbInserirOperacao.main(operacao, conta.getBanco());
		}
		if (opcao == 2) {
			System.out.println("Operação cancelada pelo usuário!");
			AppCaixa.main();
		} else if (opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida!\nTente Novamente");
			AppCaixa.main();
		}
	}

	/**Método para realizar o saque de um valor, a partir do saldo ou do crédito em uma conta.
	 * @param conta Contas - número da conta a ser debitada*/
	private static void menuSacar(Contas conta) {
		Scanner ler = new Scanner(System.in);
		DecimalFormat decimal = new DecimalFormat("0.00");

		System.out.println("\nSAQUE\n");

		System.out.println("Digite o número da agência da conta:");
		int codAgencia = ler.nextInt();

		System.out.println("Digite o número da conta que deseja efetuar o saque: ");
		conta.setCodigo(ler.nextInt());

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());

		if (conta.getCliente() == 0) {
			System.out.println("Tente Novamente!");
			AppCaixa.main();
		} else if (codAgencia != conta.getAgencia()) {
			System.out.println("Agência inexistente.\nTente Novamente!");
			AppCaixa.main();
		}

		System.out.println("Selecione o tipo de saque: \n");
		System.out.println("1 - Sacar a partir do saldo da conta\n[SALDO DISPONÍVEL: R$"
				+ decimal.format(conta.getSaldo()) + "]\n");
		System.out.println("2 - Sacar a partir do crédito disponível da conta\n[CRÉDITO DISPONÍVEL: R$"
				+ decimal.format(conta.getCredito()) + "]\n");
		System.out.println("3 - Sair");
		byte opcao = ler.nextByte();

		if (opcao == 1) {

			Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
			cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

			System.out.println("Digite o valor do saque: ");
			double valorSaque = ler.nextDouble();
			ler.nextLine();

			if (valorSaque == 0) {
				System.out.println("O valor precisa ser diferente de R$0,00\nTente Novamente!");
				AppCaixa.main();
			}

			System.out.println("Informações da conta:");
			if (cliente.getTipo() == 1) {
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("CPF: " + cliente.getCpf());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
				System.out.println("Saldo disponível: R$" + decimal.format(conta.getSaldo()));
				System.out.println("Valor a ser sacado: R$" + MetodosAuxiliares.formatarDinheiro(valorSaque));
			} else {
				System.out.println("Razão Social: " + cliente.getRazaoSocial());
				System.out.println("CNPJ: " + cliente.getCnpj());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
				System.out.println("Saldo disponível: R$" + decimal.format(conta.getSaldo()));
				System.out.println("Valor a ser sacado: R$" + MetodosAuxiliares.formatarDinheiro(valorSaque));
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

						Operacoes operacao = new Operacoes(0, 2, valorSaque, conta.getBanco(), conta.getAgencia(),
								conta.getCodigo(), 0, 0, 0, MetodosAuxiliares.getDataAtual());

						operacao.setCodigo(DbGetCodigoNovaOperacao.main(conta.getBanco()) + 1);
						operacao.setSaldoConta(conta.getSaldo());
						DbInserirOperacao.main(operacao, conta.getBanco());
					} else {
						System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente!");
						AppCaixa.main();
					}
				} else {
					System.out.println("Erro: Senha incorreta!\nTente Novamente!");
					AppCaixa.main();
				}
			}
			if (opcao == 2) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			} else if (opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida!\nTente Novamente!");
				AppCaixa.main();
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
				System.out.println("Crédito disponível: R$" + decimal.format(conta.getCredito()));
				System.out.println("Valor a ser sacado: R$" + MetodosAuxiliares.formatarDinheiro(valorSaque));
			} else if (opcao != 1 || opcao != 2) {
				System.out.println("Razão Social: " + cliente.getRazaoSocial());
				System.out.println("CNPJ: " + cliente.getCnpj());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
				System.out.println("Crédito disponível: R$" + decimal.format(conta.getCredito()));
				System.out.println("Valor a ser sacado: R$" + MetodosAuxiliares.formatarDinheiro(valorSaque));
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
						System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente!");
						AppCaixa.main();
					}

				} else {
					System.out.println("Erro: Senha incorreta!Tente Novamente!");
					AppCaixa.main();
				}
			}
			if (opcao == 2) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			} else if (opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida!\nTente Novamente!");
				AppCaixa.main();
			}
		}
		if (opcao == 3) {
			System.out.println("Operação cancelada pelo usuário!");
			AppCaixa.main();
		} else if (opcao != 1 && opcao != 2 && opcao != 3) {
			System.out.println("Opção inválida!\nTente Novamente!");
			AppCaixa.main();
		}
	}

	/** Método para realizar a transferência de um valor de uma determinada conta para uma conta2.
	 *  @param conta Contas - número da conta a ser debitada 
	 *  @param conta2 Contas - número da conta a ser favorecida */
	private static void menuTransferir(Contas conta, Contas conta2) {

		System.out.println("\nTRANSFERÊNCIA ENTRE CONTAS\n");

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
			System.out.println("Agência inexistente.\nTente Novamente!");
			AppCaixa.main();
		}
		if (conta.getCliente() == conta2.getCliente()) {
			System.out.println("Contas iguais.\nTente Novamente!");
			AppCaixa.main();
		}

		Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		Clientes cliente2 = new Clientes(conta2.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());
		cliente2 = DbGetDadosClientes.main(cliente2.getCodigo(), conta2.getBanco());

		System.out.println("Digite o valor que deseja transferir: ");
		double valorTransferencia = ler.nextDouble();

		if (valorTransferencia == 0) {
			System.out.println("Digite um valor maior que R$0,00\nTente Novamente!");
			AppCaixa.main();
		}

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
		System.out.println("Valor da transferência: R$" + MetodosAuxiliares.formatarDinheiro(valorTransferencia));
		System.out.println();
		System.out.println("Deseja confirmar a operação?");
		System.out.println();
		System.out.println("1 - Sim");
		System.out.println("2 - Não");
		byte opcao = ler.nextByte();

		if (opcao == 1) {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.next();

			if (conta.getSenha().equals(senha)) {
				if (conta.getSaldo() >= valorTransferencia) {
					conta.setSaldo(conta.getSaldo() - valorTransferencia);
					conta2.setSaldo(conta2.getSaldo() + valorTransferencia);
					DbSetDadosContas.main(conta, conta.getBanco());
					DbSetDadosContas.main(conta2, conta2.getBanco());

					Operacoes operacao = new Operacoes(0, 3, valorTransferencia, conta.getBanco(), conta.getAgencia(),
							conta.getCodigo(), conta2.getCodigo(), 0, 0, MetodosAuxiliares.getDataAtual());

					operacao.setCodigo(DbGetCodigoNovaOperacao.main(conta.getBanco()) + 1);
					operacao.setSaldoConta(conta.getSaldo());
					operacao.setSaldoConta2(conta2.getSaldo());
					DbInserirOperacao.main(operacao, conta.getBanco());
				} else {
					System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente!");
					AppCaixa.main();
				}
			} else {
				System.out.println("Erro: Senha incorreta!\nTente Novamente!");
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

	/** Método para realizar o pagamento de um boleto informado ou da dívida de uma conta.
	 * @param conta Contas - número da conta a ser debitada a partir do pagamento */
	private static void menuPagar(Contas conta) {

		String menu = null;
		Scanner ler = new Scanner(System.in);

		System.out.println("\nPAGAMENTOS\n");
		System.out.println();
		System.out.println("Escolha uma opção:");
		System.out.println();
		System.out.println("1 - Pagar boleto");
		System.out.println("2 - Pagar fatura da conta");
		menu = ler.nextLine();

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

		switch (menu) {

		case "1": {

			System.out.println("Digite o código de barras do boleto: ");
			int codBoleto = ler.nextInt();
			ler.nextLine();

			System.out.println("Digite o valor do boleto:\n[Para sair digite 0]");
			double valorPagamento = ler.nextDouble();
			ler.nextLine();

			if (valorPagamento == 0) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			}
			if (valorPagamento < 0) {
				System.out.println("Erro: Digite um valor maior que 0!");
				AppCaixa.main();
			}

			Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
			cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

			System.out.println("Informações do boleto:");
			System.out.println("Código do boleto: " + codBoleto);
			System.out.println("Valor a pagar: R$" + MetodosAuxiliares.formatarDinheiro(valorPagamento));
			System.out.println();
			System.out.println("Deseja confirmar o pagamento?");
			System.out.println();
			System.out.println("1 - Sim");
			System.out.println("2 - Não");
			byte opcao = ler.nextByte();

			if (opcao == 1) {
				System.out.println("Digite a senha da conta: ");
				String senha = ler.next();
				ler.nextLine();

				if (conta.getSenha().equals(senha)) {
					if (conta.getSaldo() >= valorPagamento) {
						conta.setSaldo(conta.getSaldo() - valorPagamento);
						DbSetDadosContas.main(conta, conta.getBanco());

						Operacoes operacao = new Operacoes(0, 5, valorPagamento, conta.getBanco(), conta.getAgencia(),
								conta.getCodigo(), codBoleto, 0, 0, MetodosAuxiliares.getDataAtual());

						operacao.setCodigo(DbGetCodigoNovaOperacao.main(conta.getBanco()) + 1);
						operacao.setSaldoConta(conta.getSaldo());
						DbInserirOperacao.main(operacao, conta.getBanco());

					} else {
						System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente!");
						AppCaixa.main();
					}
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
			break;

		case "2": {

			System.out.println(
					"O valor da dívida dessa conta é: R$" + MetodosAuxiliares.formatarDinheiro(conta.getDivida()));

			System.out.println("Digite o valor que deseja pagar:\n[Para sair digite 0]");

			double valorPagamento = ler.nextDouble();
			ler.nextLine();

			if (valorPagamento == 0) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			}
			if (valorPagamento < 0) {
				System.out.println("Erro: Digite um valor maior que 0!");
				AppCaixa.main();
			}
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
				System.out.println("O valor da dívida: R$" + conta.getDivida());
				System.out.println("Valor a pagar: R$" + MetodosAuxiliares.formatarDinheiro(valorPagamento));
			} else {
				System.out.println("Razão Social: " + cliente.getRazaoSocial());
				System.out.println("CNPJ: " + cliente.getCnpj());
				System.out.println("O valor da dívida: R$" + conta.getDivida());
				System.out.println("Valor a pagar: R$" + MetodosAuxiliares.formatarDinheiro(valorPagamento));
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

					Operacoes operacao = new Operacoes(0, 4, valorPagamento, conta.getBanco(), conta.getAgencia(),
							conta.getCodigo(), 0, 0, 0, MetodosAuxiliares.getDataAtual());

					operacao.setCodigo(DbGetCodigoNovaOperacao.main(conta.getBanco()) + 1);
					operacao.setSaldoConta(conta.getSaldo());
					DbInserirOperacao.main(operacao, conta.getBanco());
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
			break;

		default: {
			System.out.println("Erro, digite entre as opções 1 e 2!");
			menuPagar(conta);
		}
		}

	}

	/** Método para realizar consultas de saldo, crédito, dívida ou solicitar o extrato de uma determinada conta.
	 * @param Contas - número da conta a ser realizada as consultas */
	private static void menuConsultar(Contas conta) {

		String menu = null;
		Scanner ler = new Scanner(System.in);
		DecimalFormat decimal = new DecimalFormat("0.00");

		System.out.println("Digite o número da agência da conta:");
		int codAgencia = ler.nextInt();

		System.out.println("Digite o número da conta que deseja efetuar a consulta: ");
		conta.setCodigo(ler.nextInt());

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());

		if (conta.getCliente() == 0) {
			System.out.println("Tente Novamente!");
			AppCaixa.main();
		} else if (codAgencia != conta.getAgencia()) {
			System.out.println("Agência inexistente.\nTente Novamente!");
			AppCaixa.main();
		}

		System.out.println();
		System.out.println("Escolha uma opção:");
		System.out.println();
		System.out.println("1 - Consultar saldo");
		System.out.println("2 - Consultar crédito");
		System.out.println("3 - Consultar dívida");
		System.out.println("4 - Extrato detalhado da conta");
		System.out.println("5 - Sair");

		menu = ler.next();
		ler.nextLine();

		switch (menu) {

		// Consultar saldo:
		case "1": {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.next();
			ler.nextLine();

			if (conta.getSenha().equals(senha)) {
				System.out.println("Saldo disponível: R$" + decimal.format(conta.getSaldo()));
				AppCaixa.main();
			} else {
				System.out.println("Erro: Senha incorreta!\nTente Novamente!");
				menuConsultar(conta);
			}
		}
			break;

		// Consultar Credito:
		case "2": {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.next();
			ler.nextLine();

			if (conta.getSenha().equals(senha)) {
				System.out.println("Credito disponível: R$" + decimal.format(conta.getCredito()));
				AppCaixa.main();
			} else {
				System.out.println("Erro: Senha incorreta!\nTente Novamente!");
				menuConsultar(conta);
			}
		}
			break;

		// Consultar Divida:
		case "3": {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.next();
			ler.nextLine();

			if (conta.getSenha().equals(senha)) {
				System.out.println("Divida da conta: R$" + decimal.format(conta.getDivida()));
				AppCaixa.main();
			} else {
				System.out.println("Erro: Senha incorreta!\nTente Novamente!");
				menuConsultar(conta);
			}
		}
			break;

		// Extrato:
		case "4": {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.next();
			ler.nextLine();

			if (conta.getSenha().equals(senha)) {
				AppExibirExtrato.main(conta.getCodigo(), conta.getBanco());
			} else {
				System.out.println("Erro: Senha incorreta!\nTente Novamente!");
				menuConsultar(conta);
			}
		}
			break;

		// Voltar
		case "5": {
			AppCaixa.main();
		}
			break;

		default: {
			System.out.println("Erro, digite entre as opções 1 e 5!");
			AppCaixa.main();
		}
		}

	}
}
