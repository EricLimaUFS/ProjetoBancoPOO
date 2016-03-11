/**
 * 
 */
package projeto.banco.poo.appaux;

import java.sql.*;
import java.util.*;

import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbSetDadosAgencias;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 11 de mar de 2016
 */
public class AppAlterarAgencia {

	/**
	 * @param args
	 */
	public static boolean main(int codBanco) {

		boolean retorno = true;
		Scanner ler = new Scanner(System.in);
		Agencia agencia = new Agencia(0, codBanco, null, null);
		byte menu = 0;
		String novosDados;

		System.out.println("Digite o código da agência: ");
		agencia.setCodigo(ler.nextInt());
		ler.nextLine();

		if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
			agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());

			System.out.println("Escolha o que deseja alterar:");
			System.out.println("1 - Endereço");
			System.out.println("2 - Voltar");
			menu = ler.nextByte();
			ler.nextLine();

			switch (menu) {
			case 1: {
				System.out.println("Endereço atual: " + agencia.getEndereco());
				System.out.println("\nDigite o novo endereço:\n\nPara cancelar a operação, digite 'cancelar'.");
				novosDados = ler.nextLine();
				if (novosDados.equals("cancelar")) {
					// voltar
				} else {
					agencia.setEndereco(novosDados);
					DbSetDadosAgencias.main(agencia, codBanco);
				}
			}
				break;
			case 2: {
				// voltar
				retorno = false;
			}
				break;
			default: {
				System.out.println("Opção inválida! Tente novamente usando apenas números de 1 a 3.");
			}
			}
		} else {
			System.out.println("Não foi possível encontrar uma agência cadastrada com o código '" + agencia.getCodigo() + "'.");
			retorno = false;
		}
		return retorno;
	}

}
