/**
 * 
 */
package projeto.banco.poo.app;

import java.util.Scanner;

import projeto.banco.poo.appaux.AppAlterarBanco;
import projeto.banco.poo.appaux.AppInserirBanco;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbPrimeiraConexao;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 */
public class AppBanco {

	/**
	 * @param args
	 */
	public static boolean main() {

		Scanner ler = new Scanner(System.in);
		Banco banco = new Banco(0, null, null, null, null, null);
		byte menu = 0;

		System.out.println("Digite o código do banco com o qual se conectar: ");
		banco.setCodigo(ler.nextInt());
		ler.nextLine();

		if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
			banco = DbGetDadosBancos.main(banco.getCodigo());

			System.out.println("		" + banco.getNomeFantasia() + "		-		App Banco");
			System.out.println();
			System.out.println();
			System.out.println("1 - Cadastros de Agências");
			System.out.println("2 - Consulta de Dados");
			System.out.println("3 - Gerir Agência");
			System.out.println("4 - Sair");
			menu = ler.nextByte();
			ler.nextLine();

			switch (menu) {
			case 1: {
				// chamar app Db correspondente
			}
				break;
			case 2: {
				// perguntar qual agência e chamar app Db correspondente
			}
				break;
			case 3: {
				// chamar app correspondente
			}
				break;
			case 4: {
				// chamar app correspondente
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
	
public static boolean menuCadastroDeAgencias() {
		
		Scanner ler = new Scanner(System.in);
		System.out.println("1 - Inserir cadastro de agência");
		System.out.println("2 - Alterar cadastro de agência");
	//	System.out.println("3 - Excluir cadastro de agência"); -- não implementado
		System.out.println("3 - Voltar");
		byte menu = ler.nextByte();
		switch (menu) {
		case 1: {
			AppInserirAgencia.main();
		}
			break;
		case 2: {
			AppAlterarAgencia.main();
		}
			break;
		/*case 3: {
			AppExcluirAgencia.main(); -- não implementado
			
		}*/
		case 3: {
			// Volta
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
		}
		}
		return true;
	}
	
}
