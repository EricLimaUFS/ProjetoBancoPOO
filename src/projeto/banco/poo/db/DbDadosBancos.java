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
 * Classe responsável por gerenciar os dados de um determinado banco no banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 8 de mar de 2016
 * @version 1.0
 */
public class DbDadosBancos {

	/**
	 * @param args
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
