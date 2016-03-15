/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import projeto.banco.poo.core.Contas;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 15 de mar de 2016
 */
public class DbGetContasCliente {

	/**
	 * @param args
	 */
	public static ArrayList<Contas> main(int codBanco, int codCliente) {

		ArrayList<Contas> contas = new ArrayList<Contas>();
		

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT * FROM contas WHERE cliente='" + codCliente + "'";

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Contas conta = new Contas(0, 0, 0, 0, 0, 0, 0, null, null);
				
				conta.setCodigo(resultSet.getInt("codigo"));
				conta.setBanco(resultSet.getInt("banco"));
				conta.setAgencia(resultSet.getInt("agencia"));
				conta.setCliente(resultSet.getInt("cliente"));
				conta.setSaldo(resultSet.getDouble("saldo"));
				conta.setCredito(resultSet.getDouble("credito"));
				conta.setDivida(resultSet.getDouble("divida"));
				conta.setSenha(resultSet.getString("senha"));
				conta.setDataCadastro(resultSet.getString("data_cadastro"));
				
				contas.add(conta);
			}

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Cliente não encontrado.");
		}

		return contas;
	}

}
