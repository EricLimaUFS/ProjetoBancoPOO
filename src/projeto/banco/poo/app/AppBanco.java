/**
 * 
 */
package projeto.banco.poo.app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import projeto.banco.poo.appaux.AppAlterarAgencia;
import projeto.banco.poo.appaux.AppExibirExtrato;
import projeto.banco.poo.appaux.AppGerirAgencia;
import projeto.banco.poo.appaux.AppInserirAgencia;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetContasCliente;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbPesquisarCliente;


/**
 * Classe para realizar o gerenciamento do menu do banco, cadastrar agencias e consultar dados 
 * relacionados ao cliente e ao banco.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class AppBanco {

	/**Método principal, responsável por chamar os métodos de gerir agencias, cadastrar agencias e consultar dados.
	 * @return boolean - true */
	public static boolean main() {

		Scanner ler = new Scanner(System.in);
		Banco banco = new Banco(0, null, null, null, null, null);
		String menu = null;

		System.out.println("Digite o código do banco que deseja conectar-se: ");
		banco.setCodigo(ler.nextInt());
		ler.nextLine();

		if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
			banco = DbGetDadosBancos.main(banco.getCodigo());

			System.out.println("	  " + banco.getNomeFantasia() + "		-		App Banco");
			System.out.println();
			System.out.println();
			System.out.println("1 - Cadastros de Agências");
			System.out.println("2 - Consulta de Dados");
			System.out.println("3 - Gerir Agência");
			System.out.println("4 - Sair");
			menu = ler.next();
			ler.nextLine();

			switch (menu) {

			case "1": {
				menuApp.menuCadastroDeAgencias(banco.getCodigo());
			}
				break;
			case "2": {
				menuApp.menuConsultaDeDados(banco.getCodigo());
			}
				break;
			case "3": {
				AppGerirAgencia.main(banco);
			}
				break;
			case "4": {
				AppPrincipal.main(null);
			}
				break;
			default: {
				System.out.println("Opção inválida! Tente novamente usando apenas números de 1 a 4.");
			}
			}
		} else {
			System.out.println(
					"Não foi possível encontrar um banco cadastrado com o código '" + banco.getCodigo() + "'.");
		}
		return true;
	}

}
