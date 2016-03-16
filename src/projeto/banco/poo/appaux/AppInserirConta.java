/**
 * 
 */
package projeto.banco.poo.appaux;

import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoNovaConta;
import projeto.banco.poo.db.DbInserirConta;

import java.util.Scanner;

/**
 * Classe responsável por inserir os dados de uma conta.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 10 de mar de 2016
 * @version 1.0
 */
public class AppInserirConta {

	/**Método principal, responsável por recolher os dados necessários para inserir no objeto conta e enviá-los ao 
	 * banco de dados.
	 * @param codAgencia int - código da agencia que a conta irá pertencer
	 * @param codBanco int - código do banco que a conta irá pertencer
	 * @param codCliente int - código do cliente que a conta irá pertencer
	 * @return boolean - retorno */
	public static boolean main(int codAgencia, int codBanco, int codCliente) {

		Scanner ler = new Scanner(System.in);
		Contas conta = new Contas(0, codBanco, codAgencia, codCliente, 0, 0, 0, null, null);
     
		System.out.println("Insira os dados da conta\n");
		
		System.out.println("Saldo inicial da conta:");
		conta.setSaldo(ler.nextDouble());
		ler.nextLine();
		System.out.println("Senha da conta:");
		conta.setSenha(ler.nextLine());
		
		conta.setCredito(Contas.CalcularCredito(codCliente, codBanco));
		
		conta.setDataCadastro(MetodosAuxiliares.getDataAtual());

		conta.setCodigo((DbGetCodigoNovaConta.main(codBanco) + 1));

		DbInserirConta.main(conta);
		
		return true;
	}

}
