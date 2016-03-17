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
 * Classe responsável por acessar os dados do banco a partir do banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class DbGetDadosBancos {

	/**Método principal, responsável por realizar a busca do banco no banco de dados e retornar suas informações.
	 * @param codBanco int - código do banco 
	 */
	public static Banco main(int codBanco) {
		
		Banco banco = new Banco(0, null, null, null, null, null);
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT * FROM banco";

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				banco.setCodigo(resultSet.getInt("codigo"));
				banco.setRazaoSocial(resultSet.getString("razao_social"));
				banco.setCnpj(resultSet.getString("cnpj"));
				banco.setNomeFantasia(resultSet.getString("nome_fantasia"));
				banco.setEndereco(resultSet.getString("endereco"));
				banco.setDataCadastro(resultSet.getString("data_cadastro"));
			}
			statement.close();
			
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println(
					"Não foi possível encontrar um banco cadastrado com o código '" + codBanco + "'.");
		}
		
		return banco;
	}
	

}
