package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe responsável por verificar se um cliente já existe no banco de dados através do cpf ou cnpj.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 18 de mar de 2016
 * @version 1.0
 */

public class DbVerificarCliente {

	/**
	 * 
	 * @param codBanco int - Código do banco para conexão do banco de dados
	 * @param cpfCnpj long - CPF ou CNPJ para verificação de existência no banco de dados
	 * @return retorno long - retorna o CPF ou CNPJ
	 */
	public static boolean main(int codBanco, long cpfCnpj) {

		long retornoDb;
		boolean retorno = false;
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT cpf_cnpj FROM clientes WHERE cpf_cnpj=" + cpfCnpj;

			ResultSet resultSet = statement.executeQuery(query);

			retornoDb = resultSet.getLong("cpf_cnpj");

			statement.close();
			
			if (retornoDb == cpfCnpj) {
				retorno = true;
			}

		} catch (Exception ex) {
			ex.getMessage();
		}

		return retorno;
	}

}
