/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.core.Agencia;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 11 de mar de 2016
 */
public class DbSetDadosAgencias {

	/**
	 * @param args
	 */
	public static boolean main(Agencia novoObjetoAgencia, int codBanco) {

		boolean retorno = true;
		Agencia agencia = new Agencia(0, codBanco, null, null);
		agencia = novoObjetoAgencia;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "UPDATE agencias SET endereco='" + agencia.getEndereco() + "' WHERE codigo=" + agencia.getCodigo();

			statement.executeUpdate(query);

			statement.close();

			System.out.println("Operação realizada com sucesso!");
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
			System.out.println("Erro ao atualizar dados no banco de dados.");
			retorno = false;
		}

		return retorno;
	}

}
