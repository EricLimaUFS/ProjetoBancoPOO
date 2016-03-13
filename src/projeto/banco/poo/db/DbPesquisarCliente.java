/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 */
public class DbPesquisarCliente {

	/**
	 * @param args
	 */
	public static int main(int codBanco) {
		
		long cpfCnpj;
		int codCliente = 0;
		
		Scanner input = new Scanner(System.in);

		System.out.println("Digite o CPF ou o CNPJ (Apenas números): ");
		cpfCnpj = input.nextLong();
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT codigo FROM clientes WHERE cpf_cnpj=" + cpfCnpj;

			ResultSet resultSet = statement.executeQuery(query);

			codCliente = resultSet.getInt("codigo");
			
			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Cliente não encontrado.");
		}

		return codCliente;
	}

}
