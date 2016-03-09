/**
 * 
 */
package projeto.banco.poo.aux;

import java.sql.*;
import java.util.*;

import projeto.banco.poo.core.Banco;
import projeto.banco.poo.db.DbDadosBancos;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbSetDadosBancos;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 8 de mar de 2016
 */
public class AppAlterarBanco {

	/**
	 * @param args
	 */
	public static boolean main() {

		boolean retorno = true;
		Scanner ler = new Scanner(System.in);
		Banco banco = new Banco(0, null, null, null, null, null);
		byte menu = 0;
		String novosDados;

		System.out.println("Digite o código do banco: ");
		banco.setCodigo(ler.nextInt());
		ler.nextLine();

		if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
			banco = DbGetDadosBancos.main(banco.getCodigo());

			System.out.println("Escolha o que deseja alterar:");
			System.out.println("1 - Razão Social");
			System.out.println("2 - Nome Fantasia");
			System.out.println("3 - Endereço");
			System.out.println("4 - Voltar");
			menu = ler.nextByte();
			ler.nextLine();

			switch (menu) {
			case 1: {
				System.out.println("Razão Social atual: " + banco.getRazaoSocial());
				System.out.println("\nDigite a nova Razão Social:\n\nPara cancelar a operação, digite 'cancelar'.");
				ler.nextLine();
				novosDados = ler.nextLine();
				if (novosDados.equals("cancelar")) {
					// voltar
				} else {
					banco.setRazaoSocial(novosDados);
					DbSetDadosBancos.main(banco);
				}
			}
				break;
			case 2: {
				System.out.println("Nome Fantasia atual: " + banco.getNomeFantasia());
				System.out.println("\nDigite o novo Nome Fantasia:\n\nPara cancelar a operação, digite 'cancelar'.");
				ler.nextLine();
				novosDados = ler.nextLine();
				if (novosDados.equals("cancelar")) {
					// voltar
				} else {
					banco.setNomeFantasia(novosDados);
					DbSetDadosBancos.main(banco);
				}
			}
				break;
			case 3: {
				System.out.println("Endereço atual: " + banco.getEndereco());
				ler.nextLine();
				System.out.println("\nDigite o novo Endereço:\n\nPara cancelar a operação, digite 'cancelar'.");
				novosDados = ler.nextLine();
				if (novosDados.equals("cancelar")) {
					// voltar
				} else {
					banco.setEndereco(novosDados);
					DbSetDadosBancos.main(banco);
				}
			}
				break;
			case 4: {
				// voltar
				retorno = false;
			}
				break;
			default: {
				System.out.println("Opção inválida! Tente novamente usando apenas números de 1 a 3.");
			}
			}
		} else {
			System.out.println("Não foi possível encontrar um banco cadastrado com o código '" + banco.getCodigo() + "'.");
			retorno = false;
		}
		return retorno;
	}

}
