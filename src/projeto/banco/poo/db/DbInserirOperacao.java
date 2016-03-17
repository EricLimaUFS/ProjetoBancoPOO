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
 * Classe responsável por inserir uma nova operação no banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 13 de mar de 2016
 * @version 1.0
 */
public class DbInserirOperacao {

	/**Método principal, responsável por passar as informações do objeto operação para o banco de dados.
	 * @param operacao Operacões - objeto do tipo Operações
	 * @param codBanco int - codigo do banco
	 * @return boolean - true
	 */
	public static boolean main(Operacoes operacao, int codBanco) {

		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			statement = conexao.createStatement();

			String sql = "INSERT INTO operacoes (codigo, tipo_operacao, valor_operacao, banco, agencia, conta, conta2, saldo_conta, saldo_conta2, data) "
					+ "VALUES ('" + operacao.getCodigo() + "', '" + operacao.getTipoOperacao() + "', '"
					+ operacao.getValorOperacao() + "', '" + operacao.getBanco() + "', '" + operacao.getAgencia()
					+ "', '" + operacao.getConta() + "', '" + operacao.getConta2() + "', '" + operacao.getSaldoConta()
					+ "', '" + operacao.getSaldoConta2() + "', '" + operacao.getData() + "')";

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
