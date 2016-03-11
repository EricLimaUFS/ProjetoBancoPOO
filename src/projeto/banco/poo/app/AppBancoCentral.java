/**
 * 
 */
package projeto.banco.poo.app;

import projeto.banco.poo.appaux.AppAlterarBanco;
import projeto.banco.poo.appaux.AppExcluirBanco;
import projeto.banco.poo.appaux.AppInserirBanco;
import projeto.banco.poo.appaux.AppInserirPrimeiroBanco;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.db.DbBanco;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbPrimeiraConexao;
import projeto.banco.poo.db.DbSetDadosBancos;

import java.sql.*;
import java.util.Scanner;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 */
public class AppBancoCentral {

	/**
	 * @param args
	 */
	public static boolean main() {

		Scanner ler = new Scanner(System.in);
		byte menu = 0;
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
				menu = ler.nextByte();
				if (menu == 1) {
					AppInserirPrimeiroBanco.main();
					break;
				} else if (menu == 2) {
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
				System.out.println("Projeto App Banco				-			App Banco Central");
				System.out.println();
				System.out.println("Escolha uma opção:");
				System.out.println();
				System.out.println("1 - Cadastros de Bancos");
				System.out.println("2 - Consulta de Dados");
				System.out.println("3 - Sair");
				menu = ler.nextByte();
				switch (menu) {
				case 1: {
					if (menuCadastroDeBancos() == true);
					volta = true;
				}
					break;
				case 2: {
					System.out.println("Digite o número do banco:");
				}
					break;
				case 3: {
					retorno = true;
				}
					break;
				default: {
					System.out.println("Opção inválida! Tente novamente usando apenas os números 1 ou 2.");
					volta = true;
				}
				}
			} while (volta == true);
		}
		return retorno;

	}

	public static boolean menuCadastroDeBancos() {
		
		Scanner ler = new Scanner(System.in);
		System.out.println("1 - Inserir cadastro de banco");
		System.out.println("2 - Alterar cadastro de banco");
	//	System.out.println("3 - Excluir cadastro de banco"); -- não implementado
		System.out.println("3 - Voltar");
		byte menu = ler.nextByte();
		switch (menu) {
		case 1: {
			AppInserirBanco.main();
		}
			break;
		case 2: {
			AppAlterarBanco.main();
		}
			break;
		/*case 3: {
			AppExcluirBanco.main(); -- não implementado
			
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
