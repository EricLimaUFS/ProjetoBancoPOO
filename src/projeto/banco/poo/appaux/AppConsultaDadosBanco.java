/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosBancos;

/**
 * Classe responsável por realizar a consulta dos dados de um determinado banco.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 10 de mar de 2016
 * @version 1.0
 */
public class AppConsultaDadosBanco {

	/**Método principal, responsável por recolher o código do banco e em seguida exibir a informação 
	 * solicitada a partir das opções dadas.
	 * @param args String
	 * @return boolean - retorno */
	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		Banco banco = new Banco(0, null, null, null, null, null);
		String menu = null;

		System.out.println("Digite o código do banco: ");
		banco.setCodigo(ler.nextInt());
		ler.nextLine();

		if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
			banco = DbGetDadosBancos.main(banco.getCodigo());

			System.out.println("		Banco Central		-		Consulta de Dados");
			System.out.println();
			System.out.println();
			System.out.println("1 - Exibir o montante em dinheiro aplicado no banco");
			System.out.println("2 - Exibir o montante em dinheiro aplicado numa determinada agência");
			System.out.println("3 - Exibir saldo de um cliente");
			System.out.println("4 - Exibir extrato detalhado de um cliente");
			System.out.println("5 - Exibir extrato detalhado de cliente em todos os bancos");
			System.out.println("6 - Voltar");
			menu = ler.next();
			ler.nextLine();

			switch (menu) {
			case "1": {
				// chamar app Db correspondente
			}
				break;
			case "2": {
				// perguntar qual agência e chamar app Db correspondente
			}
				break;
			case "3": {
				// chamar app correspondente
			}
				break;
			case "4": {
				// chamar app correspondente
			}
				break;
			case "5": {
				// chamar app correspondente
			}
				break;
			case "6": {
				// voltar
			}
				break;
			default: {
				System.out.println("Opção inválida! Tente novamente usando apenas números de 1 a 6.");
			}
			}
		} else {
			System.out.println(
					"Não foi possível encontrar um banco cadastrado com o código '" + banco.getCodigo() + "'.");
		}
	}
}