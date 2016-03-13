/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;

import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.db.DbGetCodigoNovoCliente;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbPesquisarCliente;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 11 de mar de 2016
 */
public class AppGerirAgencia {

	/**
	 * @param args
	 */
	public static boolean main(int codBanco) {

		Scanner ler = new Scanner(System.in);
		Agencia agencia = new Agencia(0, codBanco, null, null);
		System.out.println("Digite o código da agência que deseja se conectar:");
		agencia.setCodigo(ler.nextInt());
		byte menu = 0;

		if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
			agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());

			System.out.println("Banco '" + agencia.getBanco() + "' | Agência '" + agencia.getCodigo()
					+ "' 		-		 App Agencia");
			System.out.println();
			System.out.println();
			System.out.println("1 - Cadastro de Contas");
			System.out.println("2 - Gerir Contas");
			System.out.println("3 - Voltar");
			menu = ler.nextByte();
			ler.nextLine();

			switch (menu) {

			case 1: {
				menuCadastroContas(agencia);
			}
				break;

			case 2: {
				// AppGerirConta.main();
			}
				break;

			case 3: {
				// voltar
			}
				break;

			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}

			}

		} else {
			System.out.println(
					"Não foi possível encontrar a agência '" + agencia.getCodigo() + "' no banco '" + codBanco + "'.");
		}
		return true;
	}

	public static void menuCadastroContas(Agencia agencia) {
		System.out.println("1 - Inserir Conta");
		System.out.println("2 - Excluir Conta");
		System.out.println("3 - Voltar");

		Scanner ler = new Scanner(System.in);
		byte menu = ler.nextByte();

		switch (menu) {
		case 1: {
			Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
			System.out.println(
					"Deseja vincular a conta à ser criada à um cliente, ou deseja cadastrar um novo cliente para conta? ");
			System.out.println("1 - Vincular à um cliente");
			System.out.println("2 - Cadastrar um novo cliente para conta");
			byte opcao = ler.nextByte();
			if (opcao == 1) {
				cliente.setCodigo(DbPesquisarCliente.main(agencia.getBanco()));
				AppInserirConta.main(agencia.getCodigo(), agencia.getBanco(), cliente.getCodigo());
			}
			if (opcao == 2) {
				AppInserirCliente.main(agencia.getBanco());
				cliente.setCodigo(DbGetCodigoNovoCliente.main(agencia.getBanco()));
				AppInserirConta.main(agencia.getCodigo(), agencia.getBanco(), cliente.getCodigo());
			}
		}
			break;
		case 2: {
			// AppExcluirConta();
		}
			break;
		}
	}

}
