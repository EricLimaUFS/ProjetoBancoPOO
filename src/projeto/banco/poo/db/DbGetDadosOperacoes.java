/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.core.Operacoes;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 */
public class DbGetDadosOperacoes {

	/**
	 * @param args
	 */
	public static ArrayList<Operacoes> main(int codBanco, int codConta) {

		ArrayList<Operacoes> extrato = new ArrayList<Operacoes>();
		

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();

			String query = "SELECT * FROM operacoes WHERE conta='" + codConta + "' OR conta2='" + codConta + "'";

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Operacoes operacao = new Operacoes(0, 0, 0, 0, 0, 0, 0, 0, 0, null);
				
				operacao.setCodigo(resultSet.getInt("codigo"));
				operacao.setTipoOperacao(resultSet.getInt("tipo_operacao"));
				operacao.setValorOperacao(resultSet.getDouble("valor_operacao"));
				operacao.setBanco(resultSet.getInt("banco"));
				operacao.setAgencia(resultSet.getInt("agencia"));
				operacao.setConta(resultSet.getInt("conta"));
				operacao.setConta2(resultSet.getInt("conta2"));
				operacao.setSaldoConta(resultSet.getDouble("saldo_conta"));
				operacao.setSaldoConta2(resultSet.getDouble("saldo_conta2"));
				operacao.setData(resultSet.getString("data"));
				
				extrato.add(operacao);
			}

			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Conta não encontrada.");
		}

		return extrato;
	}

}
