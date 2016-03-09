/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.app.AppBancoCentral;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 8 de mar de 2016
 */
public class DbExcluirBanco {

	/**
	 * @param args
	 */
	public static boolean main(int codBanco) {
		
		boolean retorno = true;
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			statement = conexao.createStatement();
			
			String drop = "DROP TABLE agencias";
			statement.executeUpdate(drop);
			drop = "UPDATE banco SET razao_social='Banco removido', nome_fantasia='Banco removido', endereco='Banco removido' WHERE codigo=" + codBanco;
			statement.executeUpdate(drop);
			drop = "DROP TABLE clientes";
			statement.executeUpdate(drop);
			drop = "DROP TABLE contas";
			statement.executeUpdate(drop);
			drop = "DROP TABLE operacoes";
			statement.executeUpdate(drop);
			
			statement.close();
			System.out.println("Banco excluído.");
			AppBancoCentral.main();
		} catch (Exception e)
	    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		}
		return retorno;
	}

}
