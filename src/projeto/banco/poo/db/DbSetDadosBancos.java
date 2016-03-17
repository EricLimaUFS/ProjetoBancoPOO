/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.core.Banco;

/**
 * Classe responsável por modificar os dados de um banco a partir do banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 8 de mar de 2016
 * @version 1.0
 */
public class DbSetDadosBancos {

	/**Método principal, responsável por modificar as informações de um objeto do tipo Banco no banco de dados e 
	 * retornar suas informações.
	 * @param novoObjetoBanco Banco - objeto do tipo Banco
	 * @return retorno boolean
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
