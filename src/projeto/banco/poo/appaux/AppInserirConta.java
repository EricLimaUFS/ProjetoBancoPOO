/**
 * CRIADO POR BRUNO, FALTA CONFERIR!
 */
package projeto.banco.poo.appaux;

import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetCodigoNovaAgencia;
import projeto.banco.poo.db.DbInserirAgencia;
import projeto.banco.poo.db.DbInserirConta;
import projeto.banco.poo.db.DbInserirBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 10 de mar de 2016
 */
public class AppInserirConta {

	/**
	 * @param args
	 *
	 */
	public static boolean main(int codBanco) {

		Agencia agencia = new Agencia(0, codBanco, null, null); // CONFERIR ISSO!
		Scanner ler = new Scanner(System.in);
		Contas conta = new Contas(0, codBanco, agencia.getCodigo(), 0, 0, 0, 0, null, null); // CONFERIR ISSO!
     
		System.out.println("Insira os dados da conta\n");
		
		System.out.println("CPF do cliente:"); //Para criar a conta é necessário vincular ao cliente, deseja adicionar um cliente ou deseja utilizar um cadastrado
		conta.setCliente(ler.nextInt());
		
		
		
		System.out.println("Saldo inicial da conta:");
		conta.setSaldo(ler.nextDouble());
		
		System.out.println("Credito da conta:");
		//MétodoCredito(renda do clente) retorna credito;
		
		System.out.println("Divida da conta:");
		conta.setDivida(ler.nextDouble());
		
		System.out.println("Senha da conta:");
		conta.setSenha(ler.nextLine());

		conta.setDataCadastro(MetodosAuxiliares.getDataAtual());

		conta.setCodigo((DbGetCodigoNovaAgencia.main(codBanco) + 1));

		DbInserirConta.main(conta);
		
		return true;
	}

}
