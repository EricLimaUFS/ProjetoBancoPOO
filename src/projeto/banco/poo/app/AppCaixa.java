/** 
 */
package projeto.banco.poo.app;

import java.util.Scanner;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbPrimeiraConexao;

/**
 * Classe para aplicação de operações bancárias, onde estão contidos métodos
 * relacionados à esse objetivo.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class AppCaixa {

	/**Método principal, responsável por chamar os métodos de operações bancárias.
	 * @return boolean - retorno */
	public static boolean main() {

		Contas conta = new Contas(0, 0, 0, 0, 0, 0, 0, null, null);
		Contas conta2 = new Contas(0, 0, 0, 0, 0, 0, 0, null, null);
		Banco banco = new Banco(0, null, null, null, null, null);
		boolean retorno = false;
		Scanner ler = new Scanner(System.in);
		String menu = null;
		int i = 0;

		for (i = 0; i < 10; i++) {
			if (DbGetCodigoBanco.main(i) == true)
				break;
		}
		if (DbPrimeiraConexao.main() == true) {
			System.out.println("ERRO: Não há banco cadastrado!");
			retorno = true;
		} else {

			System.out.println("Digite o número do banco para conectar-se:");
			banco.setCodigo(ler.nextInt());
			ler.nextLine();

			conta.setBanco(banco.getCodigo());
			conta2.setBanco(conta.getBanco());

			if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
				banco = DbGetDadosBancos.main(banco.getCodigo());

				System.out.println();
				System.out.println("Escolha uma opção:");
				System.out.println();
				System.out.println("1 - Depósito");
				System.out.println("2 - Saque");
				System.out.println("3 - Transferência");
				System.out.println("4 - Pagamento");
				System.out.println("5 - Realizar consulta");
				System.out.println("6 - Sair");
				menu = ler.nextLine();

				switch (menu) {

				// Depositar:
				case "1": {
					MenuApp.menuDepositar(conta);
				}
					break;

				// Sacar:
				case "2": {
					MenuApp.menuSacar(conta);
				}
					break;

				// Transferir:
				case "3": {
					MenuApp.menuTransferir(conta, conta2);
				}
					break;

				// Pagar
				case "4": {
					MenuApp.menuPagar(conta);
				}
					break;

				// Consultar
				case "5": {
					MenuApp.menuConsultar(conta);
				}
					break;

				// Voltar
				case "6": {
					AppPrincipal.main(null);
				}
					break;

				default: {
					System.out.println("Erro, digite entre as opções 1 e 6!");
					AppCaixa.main();
				}
				}
			} else {
				System.out.println("Erro: O banco digitado não existe!");
			}
		}

		return retorno;
	}


}
