/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import projeto.banco.poo.core.Clientes;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 */
public class DbGetDadosClientes {

	/**
	 * @param args
	 */
	public static Clientes main(int codCliente, int codBanco) {

		Clientes cliente = new Clientes(codCliente, 0, 0, null, null, null, 0, null, null, 0);

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT * FROM clientes WHERE codigo='" + codCliente + "'";

			ResultSet resultSet = statement.executeQuery(query);

			if (resultSet.getInt("tipo") == 1) {
				cliente.setCodigo(resultSet.getInt("codigo"));
				cliente.setCpf(resultSet.getLong("cpf_cnpj"));
				cliente.setNome(resultSet.getString("nome_razaosocial"));
				cliente.setRendaMensal(resultSet.getDouble("renda_mensal"));
				cliente.setDataCadastro(resultSet.getString("data_cadastro"));
				cliente.setDataNascimento(resultSet.getString("data_nascimento"));
				cliente.setTipo(resultSet.getInt("tipo"));

			} else {
				cliente.setCodigo(resultSet.getInt("codigo"));
				cliente.setCnpj(resultSet.getLong("cpf_cnpj"));
				cliente.setRazaoSocial(resultSet.getString("nome_razaosocial"));
				cliente.setRendaMensal(resultSet.getDouble("renda_mensal"));
				cliente.setDataCadastro(resultSet.getString("data_cadastro"));
				cliente.setNomeFantasia(resultSet.getString("nome_fantasia"));
				cliente.setTipo(resultSet.getInt("tipo"));
			}

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Cliente não encontrado.");
		}

		return cliente;
	}

}
