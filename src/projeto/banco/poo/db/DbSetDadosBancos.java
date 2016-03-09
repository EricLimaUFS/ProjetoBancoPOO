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
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 8 de mar de 2016
 */
public class DbSetDadosBancos {

	/**
	 * @param args
	 */
	public static boolean main(Banco novoObjetoBanco) {

		boolean retorno = true;
		Banco banco = new Banco(0, null, null, null, null, null);
		banco = novoObjetoBanco;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + banco.getCodigo() + ".db");
			Statement statement = conexao.createStatement();

			String query = "UPDATE banco SET razao_social='" + banco.getRazaoSocial() + "', nome_fantasia='"
					+ banco.getNomeFantasia() + "', endereco='" + banco.getEndereco() + "' WHERE codigo=" + banco.getCodigo();

			statement.executeUpdate(query);

			statement.close();

			System.out.println("Dados atualizados com sucesso!");
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
			System.out.println("Erro ao atualizar dados no banco de dados.");
			retorno = false;
		}

		return retorno;
	}

}
