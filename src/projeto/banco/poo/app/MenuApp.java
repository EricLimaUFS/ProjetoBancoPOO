package projeto.banco.poo.app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import projeto.banco.poo.appaux.AppAlterarAgencia;
import projeto.banco.poo.appaux.AppAlterarBanco;
import projeto.banco.poo.appaux.AppAlterarCliente;
import projeto.banco.poo.appaux.AppExcluirCliente;
import projeto.banco.poo.appaux.AppExcluirConta;
import projeto.banco.poo.appaux.AppExibirExtrato;
import projeto.banco.poo.appaux.AppGerirContasEClientes;
import projeto.banco.poo.appaux.AppInserirAgencia;
import projeto.banco.poo.appaux.AppInserirBanco;
import projeto.banco.poo.appaux.AppInserirCliente;
import projeto.banco.poo.appaux.AppInserirConta;
import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.core.Operacoes;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetCodigoNovaOperacao;
import projeto.banco.poo.db.DbGetCodigoNovoCliente;
import projeto.banco.poo.db.DbGetContasCliente;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbGetDadosContas;
import projeto.banco.poo.db.DbInserirOperacao;
import projeto.banco.poo.db.DbPesquisarCliente;
import projeto.banco.poo.db.DbSetDadosContas;

public class MenuApp {

	/**
	 * Método para realizar o depósito de um valor em uma conta.
	 * 
	 * @param conta Contas - número da conta favorecida
	 */
	protected static void menuDepositar(Contas conta) {

		System.out.println("\nDEPÓSITO EM CONTA\n");

		Scanner ler = new Scanner(System.in);

		System.out.println("Insira os dados do favorecido:\n");

		System.out.println("Digite o número da agência: ");
		int codAgencia = ler.nextInt();
		ler.nextLine();

		System.out.println("Digite o número da conta:");
		conta.setCodigo(ler.nextInt());
		ler.nextLine();

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

		if (valorDeposito <= 0) {
			System.out.println("O valor deve ser maior que R$0,00\nTente Novamente!");
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
		String opcao = ler.nextLine();

		if (opcao.equals("1")) {
			conta.setSaldo(conta.getSaldo() + valorDeposito);
			DbSetDadosContas.main(conta, conta.getBanco());
			Operacoes operacao = new Operacoes(0, 1, valorDeposito, conta.getBanco(), conta.getAgencia(),
					conta.getCodigo(), 0, 0, 0, MetodosAuxiliares.getDataAtual());
			operacao.setCodigo(DbGetCodigoNovaOperacao.main(conta.getBanco()) + 1);
			operacao.setSaldoConta(conta.getSaldo());
			DbInserirOperacao.main(operacao, conta.getBanco());
			AppCaixa.main();
		}
		if (opcao.equals("2")) {
			System.out.println("Operação cancelada pelo usuário!");
			AppCaixa.main();
		} else if (!opcao.equals("1") && !opcao.equals("2")) {
			System.out.println("Opção inválida!\nTente Novamente");
			AppCaixa.main();
		}
	}

	/**
	 * Método para realizar o saque de um valor, a partir do saldo ou do crédito
	 * em uma conta.
	 * 
	 * @param conta Contas - número da conta a ser debitada
	 */
	protected static void menuSacar(Contas conta) {
		Scanner ler = new Scanner(System.in);
		DecimalFormat decimal = new DecimalFormat("0.00");

		System.out.println("\nSAQUE\n");

		System.out.println("Digite o número da agência da conta:");
		int codAgencia = ler.nextInt();
		ler.nextLine();

		System.out.println("Digite o número da conta que deseja efetuar o saque: ");
		conta.setCodigo(ler.nextInt());
		ler.nextLine();
		
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
		System.out.println("3 - Voltar");
		String opcao = ler.nextLine();

		if (opcao.equals("1")) {

			Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
			cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

			System.out.println("Digite o valor do saque: ");
			double valorSaque = ler.nextDouble();
			ler.nextLine();

			if (valorSaque <= 0) {
				System.out.println("O valor precisa maior que R$0,00\nTente Novamente!");
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
			opcao = ler.nextLine();

			if (opcao.equals("1")) {

				System.out.println("Digite a senha da conta: ");
				String senha = ler.nextLine();

				if (conta.getSenha().equals(senha)) {
					if (conta.getSaldo() >= valorSaque) {
						conta.setSaldo(conta.getSaldo() - valorSaque);
						DbSetDadosContas.main(conta, conta.getBanco());

						Operacoes operacao = new Operacoes(0, 2, valorSaque, conta.getBanco(), conta.getAgencia(),
								conta.getCodigo(), 0, 0, 0, MetodosAuxiliares.getDataAtual());

						operacao.setCodigo(DbGetCodigoNovaOperacao.main(conta.getBanco()) + 1);
						operacao.setSaldoConta(conta.getSaldo());
						DbInserirOperacao.main(operacao, conta.getBanco());
						AppCaixa.main();
					} else {
						System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente!");
						AppCaixa.main();
					}
				} else {
					System.out.println("Erro: Senha incorreta!\nTente Novamente!");
					AppCaixa.main();
				}
			}
			if (opcao.equals("2")) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			} else if (!opcao.equals("1") && !opcao.equals("2")) {
				System.out.println("Opção inválida!\nTente Novamente!");
				AppCaixa.main();
			}
		}

		if (opcao.equals("2")) {

			Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
			cliente = DbGetDadosClientes.main(cliente.getCodigo(), conta.getBanco());

			System.out.println("Digite o valor do saque: ");
			double valorSaque = ler.nextDouble();
			ler.nextLine();
			
			if (valorSaque <= 0) {
				System.out.println("O valor precisa ser maior que R$0,00!\nTente novamente.");
				AppCaixa.main();
			}

			System.out.println("Informações da conta:");
			if (cliente.getTipo() == 1) {
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("CPF: " + cliente.getCpf());
				System.out.println("Conta: " + conta.getCodigo() + " | Agencia: " + conta.getAgencia());
				System.out.println("Crédito disponível: R$" + decimal.format(conta.getCredito()));
				System.out.println("Valor a ser sacado: R$" + MetodosAuxiliares.formatarDinheiro(valorSaque));
			} else {
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

			opcao = ler.nextLine();

			if (opcao.equals("1")) {

				System.out.println("Digite a senha da conta: ");
				String senha = ler.nextLine();

				if (conta.getSenha().equals(senha)) {

					if (conta.getCredito() >= valorSaque) {
						conta.setCredito(conta.getCredito() - valorSaque);
						conta.setDivida(conta.getDivida() + valorSaque);
						DbSetDadosContas.main(conta, conta.getBanco());
						AppCaixa.main();
					} else {
						System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente!");
						AppCaixa.main();
					}

				} else {
					System.out.println("Erro: Senha incorreta!Tente Novamente!");
					AppCaixa.main();
				}
			}
			if (opcao.equals("2")) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			} else if (!opcao.equals("1") && !opcao.equals("2")) {
				System.out.println("Opção inválida!\nTente Novamente!");
				AppCaixa.main();
			}
		}
		if (opcao.equals("2")) {
			System.out.println("Operação cancelada pelo usuário!");
			AppCaixa.main();
		}
		if (opcao.equals("3")) {
			AppCaixa.main();
		} else if (!opcao.equals("1") && !opcao.equals("2")) {
			System.out.println("Opção inválida!\nTente Novamente!");
			AppCaixa.main();
		}
	}

	/**
	 * Método para realizar a transferência de um valor de uma determinada conta
	 * para uma conta2.
	 * 
	 * @param conta Contas - número da conta a ser debitada
	 * @param conta2 Contas - número da conta a ser favorecida
	 */
	protected static void menuTransferir(Contas conta, Contas conta2) {

		System.out.println("\nTRANSFERÊNCIA ENTRE CONTAS\n");

		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o número da sua agência:");
		int codAgencia = ler.nextInt();
		ler.nextLine();

		System.out.println("Digite o número da sua conta: ");
		conta.setCodigo(ler.nextInt());
		ler.nextLine();

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
		ler.nextLine();

		System.out.println("Digite o número da conta do favorecido: ");
		conta2.setCodigo(ler.nextInt());
		ler.nextLine();

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
		ler.nextLine();

		if (valorTransferencia <= 0) {
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
		String opcao = ler.nextLine();

		if (opcao.equals("1")) {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.nextLine();

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
					AppCaixa.main();
				} else {
					System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente!");
					AppCaixa.main();
				}
			} else {
				System.out.println("Erro: Senha incorreta!\nTente Novamente!");
				AppCaixa.main();
			}

		}
		if (opcao.equals("2")) {
			System.out.println("Operação cancelada pelo usuário!");
			AppCaixa.main();
		} else if (!opcao.equals("1") && !opcao.equals("2")) {
			System.out.println("Opção inválida!");
			menuTransferir(conta, conta2);
		}
	}

	/**
	 * Método para realizar o pagamento de um boleto informado ou da dívida de
	 * uma conta.
	 * 
	 * @param conta Contas - número da conta a ser debitada a partir do pagamento
	 */
	protected static void menuPagar(Contas conta) {

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
		ler.nextLine();

		System.out.println("Digite o número da conta que deseja utilizar para efetuar o pagamento: ");
		conta.setCodigo(ler.nextInt());
		ler.nextLine();

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

			System.out.println("Digite o código de barras do boleto: (Até 5 dígitos)");
			int codBoleto = ler.nextInt();
			ler.nextLine();
			
			if (codBoleto <= 99999){
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
			String opcao = ler.nextLine();

			if (opcao.equals("1")) {
				System.out.println("Digite a senha da conta: ");
				String senha = ler.nextLine();

				if (conta.getSenha().equals(senha)) {
					if (conta.getSaldo() >= valorPagamento) {
						conta.setSaldo(conta.getSaldo() - valorPagamento);
						DbSetDadosContas.main(conta, conta.getBanco());

						Operacoes operacao = new Operacoes(0, 5, valorPagamento, conta.getBanco(), conta.getAgencia(),
								conta.getCodigo(), codBoleto, 0, 0, MetodosAuxiliares.getDataAtual());

						operacao.setCodigo(DbGetCodigoNovaOperacao.main(conta.getBanco()) + 1);
						operacao.setSaldoConta(conta.getSaldo());
						DbInserirOperacao.main(operacao, conta.getBanco());
						AppCaixa.main();

					} else {
						System.out.println("Erro: O valor solicitado é maior que o disponível!\nTente Novamente!");
						AppCaixa.main();
					}
				} else {
					System.out.println("Erro: Senha incorreta!\nTente Novamente!");
					AppCaixa.main();
				}

			}
			if (opcao.equals("2")) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			} else if (!opcao.equals("1") && !opcao.equals("2")) {
				System.out.println("Opção inválida!\nTente Novamente");
				AppCaixa.main();
			}
			} else if (codBoleto > 99999){
				System.out.println("Erro: O código do boleto deve ter até 5 dígitos\nTente Novamente!");
				menuPagar(conta);
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
			String opcao = ler.nextLine();

			if (opcao.equals("1")) {
				System.out.println("Digite a senha da conta: ");
				String senha = ler.nextLine();

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
					AppCaixa.main();
				} else {
					System.out.println("Erro: Senha incorreta!\nTente Novamente!");
					AppCaixa.main();
				}

			}
			if (opcao.equals("2")) {
				System.out.println("Operação cancelada pelo usuário!");
				AppCaixa.main();
			} else if (!opcao.equals("1") && !opcao.equals("2")) {
				System.out.println("Opção inválida!\nTente Novamente");
				AppCaixa.main();
			}

		}
			break;

		default: {
			System.out.println("Erro, escolha entre as opções dadas!");
			menuPagar(conta);
		}
		}

	}

	/**
	 * Método para realizar consultas de saldo, crédito, dívida ou solicitar o
	 * extrato de uma determinada conta.
	 * 
	 * @param conta Contas - número da conta a ser realizada as consultas
	 */
	protected static void menuConsultar(Contas conta) {

		String menu = null;
		Scanner ler = new Scanner(System.in);
		DecimalFormat decimal = new DecimalFormat("0.00");

		System.out.println("Digite o número da agência da conta:");
		int codAgencia = ler.nextInt();
		ler.nextLine();

		System.out.println("Digite o número da conta que deseja efetuar a consulta: ");
		conta.setCodigo(ler.nextInt());
		ler.nextLine();

		conta = DbGetDadosContas.main(conta.getCodigo(), conta.getBanco());

		if (conta.getCliente() == 0) {
			System.out.println("Tente Novamente!");
			AppCaixa.main();
		} else if (codAgencia != conta.getAgencia()) {
			System.out.println("Agência inexistente.\nTente Novamente!");
			menuConsultar(conta);
		}

		System.out.println();
		System.out.println("Escolha uma opção:");
		System.out.println();
		System.out.println("1 - Consultar saldo");
		System.out.println("2 - Consultar crédito");
		System.out.println("3 - Consultar dívida");
		System.out.println("4 - Extrato detalhado da conta");
		System.out.println("5 - Sair");
		menu = ler.nextLine();

		switch (menu) {

		// Consultar saldo:
		case "1": {
			System.out.println("Digite a senha da conta: ");
			String senha = ler.nextLine();

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
			String senha = ler.nextLine();

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
			String senha = ler.nextLine();

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
			String senha = ler.nextLine();

			if (conta.getSenha().equals(senha)) {
				AppExibirExtrato.main(conta.getCodigo(), conta.getBanco());
				AppCaixa.main();
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

	/**
	 * Método para realizar a consulta de dados, como o montante de dinheiro,
	 * saldo e extrato do cliente à partir do código de um banco dado.
	 * 
	 * @param codBanco int - código do banco
	 */
	protected static boolean menuConsultaDeDados(int codBanco) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;

		System.out.println("\nConsulta de Dados\n");
		System.out.println("1 - Exibir o montante em dinheiro aplicado");
		System.out.println("2 - Exibir saldo do cliente");
		System.out.println("3 - Exibir extrato detalhado do cliente no banco");
		System.out.println("4 - Voltar");
		menu = ler.nextLine();

		switch (menu) {
		case "1": {
			System.out.println("1 - No banco");
			System.out.println("2 - Numa determinada agência");
			System.out.println("3 - Voltar");
			menu = ler.nextLine();

			if (menu == "1") {
				System.out.println("Montante aplicado no banco: R$" + MetodosAuxiliares.getMontanteBanco(codBanco));
				menuConsultaDeDados(codBanco);
			} else if (menu == "2") {
				System.out.println("Digite o código da agência:");
				int codAgencia = ler.nextInt();
				ler.nextLine();
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
					if (menu.equals("1")) {
						System.out.println("Digite o código da agência:");
						int codAgencia = ler.nextInt();
						ler.nextLine();
						if (DbGetDadosAgencias.main(codAgencia, codBanco).getCodigo() != 0) {
							System.out.println("Saldo total do cliente na agência '" + codAgencia + "': " + "R$"
									+ MetodosAuxiliares.getSaldoTotalClienteAg(codBanco, codAgencia,
											cliente.getCodigo()));
							menuConsultaDeDados(codBanco);
						} else {
							System.out.println("Agência não encontrada! Tente novamente.");
							menuConsultaDeDados(codBanco);
						}
					} else if (menu.equals("2")) {
						System.out.println("Saldo total do cliente no banco: " + "R$"
								+ MetodosAuxiliares.getSaldoTotalCliente(codBanco, cliente.getCodigo()));
						menuConsultaDeDados(codBanco);
					} else if (menu.equals("2")) {
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

	/**
	 * Método para realizar a consulta de dados de saldo de um banco, de uma
	 * agencia ou de um cliente e exibir o extrato de um cliente do banco.
	 * 
	 * @param codBanco int - código do banco referente as informações
	 * @return boolean - true
	 */
	protected static boolean menuConsultaDeDadosBanco(int codBanco) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;

		System.out.println("\nConsulta de Dados\n");
		System.out.println("1 - Exibir o montante em dinheiro aplicado no banco");
		System.out.println("2 - Exibir o montante em dinheiro aplicado numa determinada agência");
		System.out.println("3 - Exibir saldo do cliente");
		System.out.println("4 - Exibir extrato detalhado do cliente no banco");
		System.out.println("5 - Voltar");
		menu = ler.nextLine();

		switch (menu) {
		case "1": {
			System.out.println("Montante aplicado no banco: R$"
					+ MetodosAuxiliares.formatarDinheiro(MetodosAuxiliares.getMontanteBanco(codBanco)));
			menuConsultaDeDados(codBanco);
		}
			break;
		case "2": {
			System.out.println("Digite o código da agência:");
			int codAgencia = ler.nextInt();
			ler.nextLine();
			System.out.println("Montante aplicado na agência: R$"
					+ MetodosAuxiliares.formatarDinheiro(MetodosAuxiliares.getMontanteAgencia(codBanco, codAgencia)));
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

					menu = ler.nextLine();

					if (menu.equals("1")) {
						System.out.println("Digite o código da agência:");
						int codAgencia = ler.nextInt();
						ler.nextLine();
						if (DbGetDadosAgencias.main(codAgencia, codBanco).getCodigo() != 0) {
							System.out.println("Saldo total do cliente na agência '" + codAgencia + "': " + "R$"
									+ MetodosAuxiliares.getSaldoTotalClienteAg(codBanco, codAgencia,
											cliente.getCodigo()));
							menuConsultaDeDados(codBanco);
						} else {
							System.out.println("Agência não encontrada! Tente novamente.");
							menuConsultaDeDados(codBanco);
						}
					} else if (menu.equals("2")) {
						System.out.println(
								"Saldo total do cliente no banco: " + "R$" + MetodosAuxiliares.formatarDinheiro(
										MetodosAuxiliares.getSaldoTotalCliente(codBanco, cliente.getCodigo())));
						menuConsultaDeDados(codBanco);
					} else if (menu.equals("3")) {
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

	/**
	 * Método para realizar o cadastro e alteração de agencias dentro de um
	 * determinado banco.
	 * 
	 * @param codBanco int - código do banco
	 */
	protected static void menuCadastroDeAgencias(int codBanco) {

		Banco banco = new Banco(0, null, null, null, null, null);
		Scanner ler = new Scanner(System.in);
		boolean volta = true;
		String menu = null;

		while (volta = true) {
			volta = false;
			System.out.println("1 - Inserir cadastro de agência");
			System.out.println("2 - Alterar cadastro de agência");
			System.out.println("3 - Voltar");
			menu = ler.nextLine();
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
			case "3": {
				menuCadastros();
			}
				break;
			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}
			}
		}

	}

	/**
	 * Método para realizar ou alterar o cadastro de bancos.
	 * 
	 * @return boolean - true
	 */
	protected static boolean menuCadastroDeBancos() {

		Scanner ler = new Scanner(System.in);
		System.out.println("1 - Inserir cadastro de banco");
		System.out.println("2 - Alterar cadastro de banco");
		System.out.println("3 - Voltar");
		String menu = ler.nextLine();
		switch (menu) {
		case "1": {
			AppInserirBanco.main();
		}
			break;
		case "2": {
			AppAlterarBanco.main();
		}
			break;

		case "3": {
			menuCadastros();
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
		}
		}
		return true;
	}

	/**
	 * Método de cadastro, responsável por cadastrar bancos, agencias, clientes
	 * e contas.
	 */
	protected static void menuCadastros() {

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
			MenuApp.menuCadastroDeBancos();
		}
			break;
		case "2": {
			Banco banco = new Banco(0, null, null, null, null, null);

			System.out.println("Digite o código do banco que deseja conectar-se: ");
			banco.setCodigo(ler.nextInt());
			ler.nextLine();

			if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
				banco = DbGetDadosBancos.main(banco.getCodigo());
				MenuApp.menuCadastroDeAgencias(banco.getCodigo());
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
					String opcao = ler.nextLine();

					if (opcao.equals("1")) {
						AppInserirCliente.main(banco.getCodigo());
						volta = true;
					} else if (opcao.equals("2")) {
						codCliente = DbPesquisarCliente.main(banco.getCodigo());
						if (codCliente != 0) {
							AppAlterarCliente.main(codCliente, banco.getCodigo());
						}
						volta = true;
					} else if (opcao.equals("3")) {
						codCliente = DbPesquisarCliente.main(banco.getCodigo());
						if (codCliente != 0) {
							AppExcluirCliente.main(codCliente, banco.getCodigo());
						}
						volta = true;
					} else if (opcao.equals("4")) {
						menuCadastros();
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
				
				if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()) != null) {
					agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());
					MenuApp.menuCadastroContas(agencia, banco);
				} else {
					System.out.println("Agência não encontrada");
				}
			} else {
				System.out.println("Banco não encontrado.");
			}
		}
			break;
		case "5": {
			AppPrincipal.main(null);
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 5.");
		}
		}
	}

	/**
	 * Método de operações, responsável pela consulta de dados e por realizar
	 * operações financeiras.
	 */
	protected static void menuOperacoes() {
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
					System.out.println("Montante aplicado no banco: R$" + MetodosAuxiliares
							.formatarDinheiro(MetodosAuxiliares.getMontanteBanco(banco.getCodigo())));
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
						System.out.println("Montante aplicado na agência: R$" + MetodosAuxiliares.formatarDinheiro(
								MetodosAuxiliares.getMontanteAgencia(banco.getCodigo(), agencia.getCodigo())));
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
						cliente = DbGetDadosClientes.main(DbPesquisarCliente.main(banco.getCodigo()),
								banco.getCodigo());

						if (cliente.getDataCadastro() != null) {
							System.out
									.println("Saldo total do cliente na agência '" + agencia.getCodigo() + "': " + "R$"
											+ MetodosAuxiliares.formatarDinheiro(
													MetodosAuxiliares.getSaldoTotalClienteAg(banco.getCodigo(),
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
								+ MetodosAuxiliares.formatarDinheiro(MetodosAuxiliares
										.getSaldoTotalCliente(banco.getCodigo(), cliente.getCodigo())));
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

						Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
						cliente.setCodigo(DbPesquisarCliente.main(banco.getCodigo()));
						cliente = DbGetDadosClientes.main(cliente.getCodigo(), banco.getCodigo());
						
						if (cliente.getDataCadastro() != null) {
							
							ArrayList<Contas> contas = new ArrayList<Contas>();
							contas = DbGetContasCliente.main(banco.getCodigo(), cliente.getCodigo());

							for (int i = 0; i < contas.size(); i++) {
								AppExibirExtrato.main(contas.get(i).getCodigo(), banco.getCodigo());
							}
						} else {
							System.out.println("Cliente não encontrado");
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

	/**
	 * Método responsável pelo cadastro de contas.
	 * 
	 * @param agencia Agencia - código da agencia que a conta pertence
	 * @param objBanco Banco - objeto do tipo Banco a ser referenciado para obter informações necessárias
	 */
	public static void menuCadastroContas(Agencia agencia, Banco objBanco) {
		System.out.println("1 - Inserir Conta");
		System.out.println("2 - Excluir Conta");
		System.out.println("3 - Voltar");

		Scanner ler = new Scanner(System.in);
		String menu = ler.nextLine();

		switch (menu) {
		case "1": {
			Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);

			System.out.println("Deseja vincular a conta à ser criada à um cliente, "
					+ "ou deseja cadastrar um novo cliente para conta? ");

			System.out.println("1 - Vincular à um cliente");
			System.out.println("2 - Cadastrar um novo cliente para conta");

			String opcao = ler.nextLine();

			if (menu.equals("1")) {
				cliente.setCodigo(DbPesquisarCliente.main(agencia.getBanco()));
				if (cliente.getCodigo() != 0) {
					cliente = DbGetDadosClientes.main(cliente.getCodigo(), agencia.getBanco());
					System.out.println("Deseja vincular a conta à este cliente?\n");
					if (cliente.getTipo() == 1) {
						System.out.println("Nome: " + cliente.getNome());
						System.out.println("CPF: " + cliente.getCpf());
					} else {
						System.out.println("Razão Social: " + cliente.getRazaoSocial());
						System.out.println("CNPJ: " + cliente.getCnpj());
					}
					System.out.println("1 - SIM");
					System.out.println("2 - NÃO");
					opcao = ler.nextLine();
					if (menu.equals("1")) {
						AppInserirConta.main(agencia.getCodigo(), agencia.getBanco(), cliente.getCodigo());
						menuCadastroContas(agencia, objBanco);
					} else {
						menuCadastroContas(agencia, objBanco);
					}
				}

			}
			if (menu.equals("2")) {
				AppInserirCliente.main(agencia.getBanco());
				cliente.setCodigo(DbGetCodigoNovoCliente.main(agencia.getBanco()));
				AppInserirConta.main(agencia.getCodigo(), agencia.getBanco(), cliente.getCodigo());
				menuCadastroContas(agencia, objBanco);
			}
		}
			break;
		case "2": {
			AppExcluirConta.main(agencia.getBanco(), agencia.getCodigo());
			menuCadastroContas(agencia, objBanco);
		}
			break;
		case "3": {
			menuCadastros();
		}
		}
	}

	/**
	 * Método responsável por gerenciar contas de um determinado banco ou
	 * agencia.
	 * 
	 * @param codAgencia  int - código da agencia que a conta pertence ou o cliente pertencem
	 * @param codBanco int - código do banco que a conta, o cliente ou a agencia pertencem
	 */
	public static void menuGerirContas(int codBanco, int codAgencia) {
		Scanner ler = new Scanner(System.in);
		Contas conta = new Contas(0, codBanco, codAgencia, 0, 0, 0, 0, null, null);
		int codConta;
		String menu = null;
		boolean volta = true;

		System.out.println("Digite o número da conta:");
		codConta = ler.nextInt();
		ler.nextLine();

		conta = DbGetDadosContas.main(codConta, codBanco);
		if (conta.getDataCadastro() != null) {
			System.out.println("1 - Realizar alterações financeiras");
			System.out.println("2 - Consultar ou alterar dados da conta");
			System.out.println("3 - Voltar");
			menu = ler.nextLine();

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
						ler.nextLine();
						DbSetDadosContas.main(conta, codBanco);
						volta = true;
					} else if (menu.equals("2")) {
						System.out
								.println("Crédito atual: R$" + MetodosAuxiliares.formatarDinheiro(conta.getCredito()));
						System.out.println("Digite o novo valor de crédito: ");
						conta.setCredito(ler.nextDouble());
						ler.nextLine();
						DbSetDadosContas.main(conta, codBanco);
						volta = true;
					} else if (menu.equals("3")) {
						System.out.println("Dívida atual: R$" + MetodosAuxiliares.formatarDinheiro(conta.getDivida()));
						System.out.println("Digite o novo valor da dívida: ");
						conta.setDivida(ler.nextDouble());
						ler.nextLine();
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

	/**
	 * Método responsável por gerenciar os clientes de um determinado banco ou
	 * agencia.
	 * 
	 * @param codAgencia  int - código da agencia que a conta pertence ou o cliente pertencem
	 * @param codBanco int - código do banco que a conta, o cliente ou a agencia pertencem
	 */
	public static void menuGerirClientes(int codBanco, int codAgencia) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		int codCliente = 0;
		boolean volta = true;
		System.out.println("1 - Cadastro de Clientes");
		System.out.println("2 - Consulta de Dados");
		System.out.println("3 - Voltar");
		menu = ler.nextLine();

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
