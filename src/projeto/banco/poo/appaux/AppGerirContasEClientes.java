/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;

import projeto.banco.poo.app.menuApp;
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
			menuApp.menuGerirContas(codBanco, codAgencia);
		}
			break;
		case "2": {
			menuApp.menuGerirClientes(codBanco, codAgencia);
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

	}
