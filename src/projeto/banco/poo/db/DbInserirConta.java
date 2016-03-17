/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.core.Contas;

/**
 * Classe responsável por inserir uma nova conta no banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 10 de mar de 2016
 * @version 1.0
 */
public class DbInserirConta {

	/**Método principal, responsável por passar as informações do objeto conta para o banco de dados.
	 * @param conta Contas - objeto do tipo Contas
	 * @return boolean - true
	 */
	public static boolean main(Contas conta) {
		
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + conta.getBanco() + ".db");
			statement = conexao.createStatement();
			
			String sql = "INSERT INTO contas (codigo, banco, agencia, cliente, saldo, credito, divida, senha, data_cadastro) " + "VALUES ('"
					+ conta.getCodigo() + "', '" + conta.getBanco() + "', " + "'" + conta.getAgencia() + "', '"
					+ conta.getCliente() + "', '" + conta.getSaldo() + "', '" + conta.getCredito() + "', '" 
					+ conta.getDivida() + "'" + ", '" + conta.getSenha() + "', '" + conta.getDataCadastro() + "')";
			
			statement.executeUpdate(sql);
			
			System.out.println("Conta cadastrada com sucesso! O código da conta é: " + conta.getCodigo());
			statement.close();
			conexao.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return true;
	}

}
