package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbVerificarCliente {

	public static long main(int codBanco, long cpfCnpj) {

		long retorno;
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT cpf_cnpj FROM clientes WHERE cpf_cnpj=" + cpfCnpj;

			ResultSet resultSet = statement.executeQuery(query);

			retorno = resultSet.getLong("cpf_cnpj");

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
			//System.out.println("Cliente não encontrado.");
			retorno = 0;
		}

		return retorno;
	}

}
