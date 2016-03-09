/**
 * 
 */
package projeto.banco.poo.app;

import projeto.banco.poo.db.DbPrimeiraConexao;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 */
public class AppBanco {

	/**
	 * @param args
	 */
	public static boolean main(String[] args) {
		
		boolean retorno = false;
		
		if (DbPrimeiraConexao.main() == true) {
			System.out.println("ERRO: Não há banco cadastrado!");
			retorno = true;
		} else {

			System.out.println("Digite o número do banco que deseja se conectar:");
			// Conectar ao banco de dados
			// setBanco
			System.out.println();
			System.out.println("Projeto App Banco				-			Menu Banco");
			System.out.println();
			System.out.println("Escolha uma opção:");
			System.out.println();
			System.out.println("1 - Cadastros de Agências");
			System.out.println("2 - Consulta de Dados");
			System.out.println("3 - Gerir Agência");
			System.out.println("4 - Sair");

		}
		
		return retorno;
	}

}
