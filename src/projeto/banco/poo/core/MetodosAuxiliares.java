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

			montante = resultSet.getDouble(0);

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

			montante = resultSet.getDouble(0);

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		return montante;
	}

	/**
	 * @param args
	 */
	

}
