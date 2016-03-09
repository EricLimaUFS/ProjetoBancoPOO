/**
 * 
 */
package projeto.banco.poo.app;

import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbPrimeiraConexao;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 */
public class AppCaixa {

	/**
	 * @param args
	 */
	public static boolean main(String[] args) {

		boolean retorno = false;
		
		int i = 0;
		for (i = 0; i < 10; i++) {
			if (DbGetCodigoBanco.main(i) == true)
				break;
		}
		if (DbPrimeiraConexao.main() == true) {
			System.out.println("ERRO: Não há banco cadastrado!");
			retorno = true;
		} else {

			System.out.println("Digite o número do banco que deseja se conectar:");
			//Conectar ao banco de dados
			//setBanco
			System.out.println("Digite o número da agência deseja se conectar:");
			//setAgencia
			System.out.println("Digite o da conta que deseja se conectar:");
			//setConta
			System.out.println();
			System.out.println("Escolha uma opção:");
			System.out.println();
			System.out.println("1 - Depósito");
			System.out.println("2 - Saque");
			System.out.println("3 - Transferência");
			System.out.println("4 - Pagamento");
			System.out.println("5 - Sair");

		}
		return retorno;
	}

}
