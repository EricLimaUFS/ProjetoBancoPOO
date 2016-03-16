/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 15 de mar de 2016
 */
public class DbExcluirCliente {

	/**
	 * @param args
	 */
	public static boolean main(int codCliente, int codBanco) {
		
		boolean retorno = true;
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			statement = conexao.createStatement();
			
			String drop = "UPDATE clientes SET codigo=null, cpf_cnpj=null, nome_razaosocial=null, renda_mensal=null, "
					+ "data_cadastro=null, data_nascimento=null, nome_fantasia=null, tipo=null WHERE codigo=" + codCliente;
			statement.executeUpdate(drop);
			
			statement.close();
			System.out.println("Cliente removido.");
		} catch (Exception e)
	    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		}
		return retorno;
	}

}
