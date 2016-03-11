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
 * @since 10 de mar de 2016
 */
public class DbInserirAgencia {

	/**
	 * @param args
	 */
	public static boolean main(Agencia agencia) {
		
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + agencia.getBanco() + ".db");
			statement = conexao.createStatement();
			
			String sql = "INSERT INTO agencias (codigo, banco, endereco, data_cadastro) " + "VALUES ('"
					+ agencia.getCodigo() + "', '" + agencia.getBanco() + "', " + "'" + agencia.getEndereco() + "', '"
					+ agencia.getDataCadastro() + "')";
			
			statement.executeUpdate(sql);
			
			System.out.println("Agência cadastrada com sucesso! O código da agência é: " + agencia.getCodigo());
			statement.close();
			conexao.close();
		} catch (Exception e) {
			System.err.println("Erro no banco de dados! ");
			System.err.println(e.getMessage());
		}
		return true;
	}

}
