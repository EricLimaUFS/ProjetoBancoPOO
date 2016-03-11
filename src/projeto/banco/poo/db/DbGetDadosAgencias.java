/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import projeto.banco.poo.core.Agencia;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 11 de mar de 2016
 */
public class DbGetDadosAgencias {

	/**
	 * @param args
	 */
	public static Agencia main(int codAgencia, int codBanco) {
		
		Agencia agencia = new Agencia(codAgencia, codBanco, null, null);
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT * FROM agencias WHERE codigo='" + codAgencia + "'";

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				agencia.setCodigo(resultSet.getInt("codigo"));
				agencia.setBanco(resultSet.getInt("banco"));
				agencia.setEndereco(resultSet.getString("endereco"));
				agencia.setDataCadastro(resultSet.getString("data_cadastro"));
			}
			statement.close();
			
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println(
					"Não foi possível encontrar uma agência cadastrada com o código '" + codAgencia + "'.");
		}
		
		return agencia;
	}
	

}
