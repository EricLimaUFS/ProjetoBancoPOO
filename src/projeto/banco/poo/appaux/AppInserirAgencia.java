/**
 * 
 */
package projeto.banco.poo.appaux;

import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoNovaAgencia;
import projeto.banco.poo.db.DbInserirAgencia;

import java.util.Scanner;

/**
 * Classe responsável por inserir os dados de uma agencia.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 10 de mar de 2016
 * @version 1.0
 */
public class AppInserirAgencia {

	/**Método principal, responsável por recolher os dados necessários para inserir na agencia e enviá-los ao banco de dados.
	 * @param codBanco int - código do banco que a agencia irá pertencer
	 * @return boolean - retorno */
	public static boolean main(int codBanco) {

		Scanner ler = new Scanner(System.in);
		Agencia agencia = new Agencia(0, codBanco, null, null);

		System.out.println("Insira os dados da agência\n");
		System.out.println("Endereço:");
		agencia.setEndereco(ler.nextLine());

		agencia.setDataCadastro(MetodosAuxiliares.getDataAtual());

		agencia.setCodigo((DbGetCodigoNovaAgencia.main(codBanco) + 1));

		DbInserirAgencia.main(agencia);
		
		return true;
	}

}
