/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Operacoes;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 13 de mar de 2016
 */
public class DbInserirOperacao {

	/**
	 * @param args
	 */
	public static boolean main(Operacoes operacao, int codBanco) {
		
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			statement = conexao.createStatement();
			
			
			String sql = "INSERT INTO operacoes (codigo, tipo_operacao, banco, agencia, conta, conta2, data) " + "VALUES ('"
					+ operacao.getCodigo() + "', '" + operacao.getTipoOperacao() + "', " + "'" + operacao.getBanco() + "', '"
					+ operacao.getAgencia() + "', '" + operacao.getConta() + "', '" + operacao.getConta2() + "', '" 
					+ operacao.getData() + "')";
			
			statement.executeUpdate(sql);
			
			System.out.println("Operação realizada com sucesso!");
			statement.close();
			conexao.close();
			
			
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return true;
	}

}
