/**
 * 
 */
package projeto.banco.poo.app;

import java.util.Scanner;

import projeto.banco.poo.appaux.AppInserirPrimeiroBanco;
import projeto.banco.poo.db.DbPrimeiraConexao;

/**
 * Classe para realizar o gerenciamento de bancos.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class AppBancoCentral {

	/**Método principal, responsável por chamar os métodos de cadastrar bancos e consultar dados relacionados ao banco.
	 * @return boolean - true */
	public static boolean main() {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean retorno = false;
		boolean volta = true;

		// Limpar tela
		for (int i = 0; i < 18; i++) {
			System.out.println();
		}

		if (DbPrimeiraConexao.main() == true) {

			do {
				volta = false;
				System.out.println("ATENÇÃO - Não há banco cadastrado! Deseja cadastrar o banco?");
				System.out.println();
				System.out.println("1 - Sim");
				System.out.println("2 - Não");
				menu = ler.nextLine();
				
				if (menu.equals("1")) {
					AppInserirPrimeiroBanco.main();
					break;
				} else if (menu.equals("2")) {
					retorno = true;
					break;
				} else {
					System.out.println("Opção inválida! Tente novamente usando apenas os números 1 ou 2.");
					volta = true;
				}

			} while (volta = true);
		}

		if (DbPrimeiraConexao.main() == false) {

			do {
				volta = false;
				System.out.println();
				System.out.println("Projeto App Banco		-   	App Banco Central");
				System.out.println();
				System.out.println("Escolha uma opção:");
				System.out.println();
				System.out.println("1 - Cadastros de Bancos");
				System.out.println("2 - Consulta de Dados");
				System.out.println("3 - Sair");
				menu = ler.nextLine();
				
				switch (menu) {
				case "1": {
					if (MenuApp.menuCadastroDeBancos() == true)
						;
					volta = true;
				}
					break;
				case "2": {
					int codBanco;
					System.out.println("Digite o número do banco:");
					codBanco = ler.nextInt();
					MenuApp.menuConsultaDeDadosBanco(codBanco);
				}
					break;
				case "3": {
					retorno = true;
				}
					break;
				default: {
					System.out.println("Opção inválida! Tente novamente usando apenas os números 1 e 3.");
					volta = true;
				}
				}
			} while (volta == true);
		}
		return retorno;

	}
}
