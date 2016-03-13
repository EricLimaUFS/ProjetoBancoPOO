/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.Operacoes;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 */
public class DbGetDadosOperacoes {

	/**
	 * @param args
	 */
	public static Operacoes main(int codBanco, Contas conta) {

		Operacoes operacao = new Operacoes(0, 0, 0, codBanco, 0, 0, 0, null);

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT * FROM operacoes WHERE conta='" + codConta + "'";

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
