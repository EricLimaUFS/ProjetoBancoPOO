/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.*;

import projeto.banco.poo.aux.AppInserirPrimeiroBanco;
import projeto.banco.poo.core.Banco;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 */
public class DbPrimeiraConexao {

	/**
	 * @param args
	 * 
	 */
	public static boolean main() {

		Banco banco = new Banco(0, null, null, null, null, null);
		boolean retorno = false;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco0.db");
			Statement statement = conexao.createStatement();

			String query = "SELECT * FROM banco";

			ResultSet resultSet = statement.executeQuery(query);

			statement.close();
			conexao.close();
		} catch (Exception ex) {
			ex.getMessage();
			// Erro banco de dados inexistente
			// System.out.println(sqlException.getMessage());
			retorno = true;
		}

		return retorno;
	}

}
