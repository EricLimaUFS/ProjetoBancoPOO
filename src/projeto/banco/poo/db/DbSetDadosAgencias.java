/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.core.Agencia;

/**
 * Classe responsável por modificar os dados da agencia a partir do banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class DbSetDadosAgencias {

	/**Método principal, responsável por modificar as informações de um objeto do tipo Agencia no banco de dados e retornar suas informações.
	 * @param novoObjetoAgencia Agencia - objeto do tipo Agencia
	 * @param codBanco int - código do banco referente a agencia
	 * @return retorno boolean
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
