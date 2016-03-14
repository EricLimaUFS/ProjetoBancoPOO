/**
 * 
 */
package projeto.banco.poo.appaux;
import java.util.*;

import projeto.banco.poo.core.Contas;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 14 de mar de 2016
 */
public class AppExcluirConta {

	/**
	 * @param args
	 */
	public static void main(int codBanco, int codAgencia) {
		
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite o número da conta que deseja excluir: ");
		int codConta = ler.nextInt();
		Contas conta = new Contas(0, codBanco, codAgencia, 0, 0, 0, 0, null, null);

	}

}
