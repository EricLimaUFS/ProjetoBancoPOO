/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import projeto.banco.poo.core.Contas;

/**
 * Classe responsável por acessar os dados das contas a partir do banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 * @version 1.0
 */
public class DbGetDadosContas {

	/**Método principal, responsável por realizar a busca da conta no banco de dados e retornar suas informações.
	 * @param codConta int - código da conta
	 * @param codBanco int - código do banco referente a conta
	 */
	public static Contas main(int codConta, int codBanco) {

		Contas conta = new Contas(codConta, codBanco, 0, 0, 0, 0, 0, null, null);

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT * FROM contas WHERE codigo='" + codConta + "'";

			ResultSet resultSet = statement.executeQuery(query);

			conta.setCodigo(resultSet.getInt("codigo"));
			conta.setBanco(resultSet.getInt("banco"));
			conta.setAgencia(resultSet.getInt("agencia"));
			conta.setCliente(resultSet.getInt("cliente"));
			conta.setSaldo(resultSet.getDouble("saldo"));
			conta.setCredito(resultSet.getDouble("credito"));
			conta.setDivida(resultSet.getDouble("divida"));
			conta.setSenha(resultSet.getString("senha"));
			conta.setDataCadastro(resultSet.getString("data_cadastro"));

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Conta não encontrada.");
		}

		return conta;
	}

}
