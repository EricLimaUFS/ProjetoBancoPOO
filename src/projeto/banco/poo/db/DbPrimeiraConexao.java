/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.*;

import projeto.banco.poo.appaux.AppInserirPrimeiroBanco;
import projeto.banco.poo.core.Banco;

/**
 * Classe responsável por realizar a primeira conexão com o banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class DbPrimeiraConexao {

	/**Método principal, responsável por realizar a conexão com o banco de dados e checar a existência do banco de dados
	 * @return retorno boolean
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
