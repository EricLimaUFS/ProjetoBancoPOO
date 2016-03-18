/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;

import projeto.banco.poo.app.AppBanco;
import projeto.banco.poo.app.menuApp;
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
				menuApp.menuCadastroContas(agencia, objBanco);
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

}
