/**
 * 
 */
package projeto.banco.poo.core;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 */
public class MetodosAuxiliares {
	
	public static String getDataAtual() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static double getMontanteBanco(int codBanco) {
		double montante = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT SUM(saldo) FROM contas";

			ResultSet resultSet = statement.executeQuery(query);

			montante = resultSet.getDouble("SUM(saldo)");

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		return montante;
	}
	
	public static double getMontanteAgencia(int codBanco, int codAgencia) {
		double montante = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT SUM(saldo) FROM contas WHERE agencia='" + codAgencia + "'";

			ResultSet resultSet = statement.executeQuery(query);

			montante = resultSet.getDouble("SUM(saldo)");

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		return montante;
	}

	public static double getSaldoTotalClienteAg(int codBanco, int codAgencia, int codCliente) {
		double soma = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT SUM(saldo) FROM contas WHERE agencia='" + codAgencia + "' AND cliente='" + codCliente + "'";

			ResultSet resultSet = statement.executeQuery(query);

			soma = resultSet.getDouble("SUM(saldo)");

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		return soma;
	}
	
	public static double getSaldoTotalCliente(int codBanco, int codCliente) {
		double soma = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT SUM(saldo) FROM contas WHERE cliente='" + codCliente + "'";

			ResultSet resultSet = statement.executeQuery(query);

			soma = resultSet.getDouble("SUM(saldo)");

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		return soma;
	}
	/**
	 * @param args
	 */
	

}
