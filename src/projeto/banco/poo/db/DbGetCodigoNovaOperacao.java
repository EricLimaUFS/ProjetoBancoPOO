/**
 * 
 */

package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 13 de mar de 2016
 */
public class DbGetCodigoNovaOperacao {
	
	/**
	 * @param args
	 */
	public static int main(int codBanco) {

		int retorno = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT codigo FROM operacoes ORDER BY codigo DESC LIMIT 1";

			ResultSet resultSet = statement.executeQuery(query);

			retorno = resultSet.getInt("codigo");

			statement.close();
			conexao.close();
			resultSet.close();
		} catch (Exception ex) {
			ex.getMessage();
			retorno = 0;
		}

		return retorno;

	}

}
