/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * Classe responsável por efetuar a exclusão de uma determinada conta no banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 15 de mar de 2016
 * @version 1.0
 */
public class DbExcluirConta {

	/**Método principal, responsável por recolher os dados de uma determinada conta e em seguida, excluí-la.
	 * @param codConta int - código da conta a ser referenciada
	 * @param codBanco int - código do banco que a conta pertence
	 * @return retorno boolean - retorna verdade ou falso para a classe que a chamou */
	public static boolean main(int codConta, int codBanco) {
		
		boolean retorno = true;
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			statement = conexao.createStatement();
			
			String drop = "UPDATE contas SET codigo=null, banco=null, agencia=null, cliente=null, saldo=null, credito=null, "
					+ "divida=null, senha=null, data_cadastro=null WHERE codigo=" + codConta;
			statement.executeUpdate(drop);
			
			statement.close();
			System.out.println("Conta removida.");
		} catch (Exception e)
	    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		}
		return retorno;
	}

}
