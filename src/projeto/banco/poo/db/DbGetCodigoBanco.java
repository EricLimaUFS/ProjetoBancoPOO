/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import projeto.banco.poo.core.Banco;

/**
 * Classe responsável acessar o código atual do banco para aplicar em outras classes.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class DbGetCodigoBanco {

	/**Método principal, responsável por realizar a busca do código do banco no banco de dados.
	 * @param codBanco int - código do banco
	 */
	public static boolean main(int codBanco) {

		Banco banco = new Banco(0, null, null, null, null, null);
		boolean retorno = false;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();
			
			String query = "SELECT * FROM banco";

			ResultSet resultSet = statement.executeQuery(query);

			statement.close();
			conexao.close();
		} catch (Exception ex) {
			ex.getMessage();
			retorno = true;
		}

		return retorno;

	}

}
