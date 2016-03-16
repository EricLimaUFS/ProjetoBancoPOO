/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;

import projeto.banco.poo.core.Contas;
import projeto.banco.poo.db.DbExcluirCliente;
import projeto.banco.poo.db.DbGetContasCliente;

/**
 * Classe responsável exluir os dados de um determinado cliente.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 15 de mar de 2016
 * @version 1.0
 */
public class AppExcluirCliente {

	/**Método principal, responsável por recolher os dados de um determinado cliente e em seguida, excluí-lo.
	 * @param codCliente int - código do cliente a ser referenciado
	 * @param codBanco int - código do banco que o cliente referenciado pertence */
	public static void main(int codCliente, int codBanco) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;
		ArrayList<Contas> contas = new ArrayList<Contas>();
		contas = DbGetContasCliente.main(codBanco, codCliente);

		if (contas.isEmpty() == true) {
			while (volta = true) {
				volta = false;

				System.out.println(
						"Tem certeza que deseja excluir este cliente?\nApós a operação a ação não poderá ser desfeita.");
				System.out.println("1 - Sim\n2 - Não");
				menu = ler.next();
				ler.nextLine();

				if (menu == "1") {
					DbExcluirCliente.main(codCliente, codBanco);
					break;
				} else if (menu == "2") {
					break;
				} else {
					System.out.println("Opção inválida! Digite apenas 1 ou 2.");
					volta = true;
				}
			}
		} else {
			System.out.println(
					"Este cliente possui uma ou mais contas vinculadas a seu cadastro!\nRemova as contas antes de excluir o cliente.");
		}

	}

}
