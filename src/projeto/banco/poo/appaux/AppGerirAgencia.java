/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;

import projeto.banco.poo.app.AppBanco;
import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.db.DbGetCodigoNovoCliente;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbPesquisarCliente;

/**
 * Classe responsável por gerenciar os dados de uma determinada agencia.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 11 de mar de 2016
 * @version 1.0
 */
public class AppGerirAgencia {

	/**Método principal, responsável por chamar os métodos de cadastro de contas e o de fazer o 
	 * gerenciamento de contas e clientes.
	 * @param objBanco Banco - objeto do tipo Banco a ser referenciado para obter informações necessárias
	 * @return boolean - retorno */
	public static boolean main(Banco objBanco) {

		Scanner ler = new Scanner(System.in);
		Agencia agencia = new Agencia(0, objBanco.getCodigo(), null, null);
		System.out.println("Digite o código da agência que deseja se conectar:");
		agencia.setCodigo(ler.nextInt());
		String menu = null;

		if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
			agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());

			System.out.println("'" + objBanco.getNomeFantasia() + "' | Agência '" + agencia.getCodigo()
					+ "' 		-		 App Agencia");
			System.out.println();
			System.out.println();
			System.out.println("1 - Cadastro de Contas");
			System.out.println("2 - Gerir Contas e Clientes");
			System.out.println("3 - Voltar");
			menu = ler.next();
			ler.nextLine();

			switch (menu) {

			case "1": {
				menuCadastroContas(agencia, objBanco);
			}
				break;

			case "2": {
				AppGerirContasEClientes.main(agencia.getBanco(), agencia.getCodigo());
			}
				break;

			case "3": {
				AppBanco.main();
			}
				break;

			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}

			}

		} else {
			System.out.println(
					"Não foi possível encontrar a agência '" + agencia.getCodigo() + "' no banco '" + objBanco.getCodigo() + "'.");
			AppBanco.main();
		}
		return true;
	}

	/**Método responsável pelo cadastro de contas.
	 * @param codAgencia int - código da agencia que a conta pertence
	 * @param objBanco Banco - objeto do tipo Banco a ser referenciado para obter informações necessárias */
	public static void menuCadastroContas(Agencia agencia, Banco objBanco) {
		System.out.println("1 - Inserir Conta");
		System.out.println("2 - Excluir Conta");
		System.out.println("3 - Voltar");

		Scanner ler = new Scanner(System.in);
		String menu = ler.next();

		switch (menu) {
		case "1": {
			Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
			
			System.out.println("Deseja vincular a conta à ser criada à um cliente, "
					+ "ou deseja cadastrar um novo cliente para conta? ");

			System.out.println("1 - Vincular à um cliente");
			System.out.println("2 - Cadastrar um novo cliente para conta");

			byte opcao = ler.nextByte();
			ler.nextLine();
			
			if (opcao == 1) {
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
					opcao = ler.nextByte();
					if (opcao == 1) {
						AppInserirConta.main(agencia.getCodigo(), agencia.getBanco(), cliente.getCodigo());
						menuCadastroContas(agencia, objBanco);
					} else {
						menuCadastroContas(agencia, objBanco);
					}
				}

			}
			if (opcao == 2) {
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
			AppGerirAgencia.main(objBanco);
		}
		}
	}

}
