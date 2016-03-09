/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.Scanner;

import projeto.banco.poo.app.AppBancoCentral;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.db.DbExcluirBanco;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosBancos;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 8 de mar de 2016
 */
public class AppExcluirBanco {

	/**
	 * @param args
	 */
	public static boolean main() {

		boolean retorno = true;
		Scanner ler = new Scanner(System.in);
		Banco banco = new Banco(0, null, null, null, null, null);
		byte menu = 0;
		String simOuNao;

		System.out.println("Digite o código do banco: ");
		banco.setCodigo(ler.nextInt());
		ler.nextLine();

		if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
			banco = DbGetDadosBancos.main(banco.getCodigo());
			System.out.println("---------- Excluir cadastro de banco ----------");
			System.out.println();
			System.out.println();
			System.out.println("Código do banco: " + banco.getCodigo());
			System.out.println("Razão Social: " + banco.getRazaoSocial());
			System.out.println("CNPJ: " + banco.getCnpj());
			System.out.println("Nome Fantasia: " + banco.getNomeFantasia());
			System.out.println("Endereço: " + banco.getEndereco());
			System.out.println("Data de cadastro: " + banco.getDataCadastro());
			System.out.println();
			System.out.println("Tem certeza que deseja excluir este banco?\nDigite S para SIM ou N para NÃO:");
			
			simOuNao = ler.next();

			if (simOuNao.equals("S") || simOuNao.equals("s")) {
				DbExcluirBanco.main(banco.getCodigo());

			} else if ((simOuNao.equals("N") == false) || (simOuNao.equals("n") == false)) {
				System.out.println("Opção inválida! Utilize apenas as letras 'S' ou 'N'.");

			}
		}
		return retorno;
	}

}
