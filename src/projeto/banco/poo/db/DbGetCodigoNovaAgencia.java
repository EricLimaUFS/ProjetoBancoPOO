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
 * Classe responsável por acessar o código da nova agencia para aplicar em outras classes.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class DbGetCodigoNovaAgencia {

	/**Método principal, responsável por realizar a busca do código da agencia no banco de dados.
	 * @param codBanco int - código do banco referente a agencia
	 */
	public static int main(int codBanco) {

		int retorno = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();
			
			String query = "SELECT codigo FROM agencias ORDER BY codigo DESC LIMIT 1";

			ResultSet resultSet = statement.executeQuery(query);
			
			retorno = resultSet.getInt("codigo");

			statement.close();
			conexao.close();
			resultSet.close();
		} catch (Exception ex) {
			ex.getMessage();
		}

		return retorno;

	}

}
