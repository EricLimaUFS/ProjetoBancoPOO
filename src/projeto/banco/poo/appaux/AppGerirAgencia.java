/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;

import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.db.DbGetDadosAgencias;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 11 de mar de 2016
 */
public class AppGerirAgencia {

	/**
	 * @param args
	 */
	public static boolean main(int codBanco) {
		
		Scanner ler = new Scanner(System.in);
		Agencia agencia = new Agencia(0, codBanco, null, null);
		System.out.println("Digite o código da agência que deseja se conectar:");
		agencia.setCodigo(ler.nextInt());
		byte menu = 0;
		
		if (DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco()).getDataCadastro() != null) {
			agencia = DbGetDadosAgencias.main(agencia.getCodigo(), agencia.getBanco());
			
			System.out.println("Banco '" + agencia.getBanco() + "' | Agência '" + agencia.getCodigo() + "' 		-		 App Agencia");
			System.out.println();
			System.out.println();
			System.out.println("1 - Cadastro de Contas");
			System.out.println("2 - Gerir Contas");
			System.out.println("3 - Voltar");
			menu = ler.nextByte();
			ler.nextLine();
			
			switch (menu) {
			case 1: {
				// cadastro
			} break;
			case 2: {
				// gerir contas
			} break;
			case 3: {
				// voltar
			} break;
			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}
			}
			
		} else {
			System.out.println("Não foi possível encontrar a agência '" + agencia.getCodigo() + "' no banco '" + codBanco + "'.");
		}
		
		return true;
	}

}
