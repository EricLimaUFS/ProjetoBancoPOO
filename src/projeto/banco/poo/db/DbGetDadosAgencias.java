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
 * Classe responsável por acessar os dados da agencia a partir do banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class DbGetDadosAgencias {

	/**Método principal, responsável por realizar a busca da agencia no banco de dados e retornar suas informações.
	 * @param codBanco int - código do banco referente a agencia
	 * @param codAgencia int - código da agencia
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
