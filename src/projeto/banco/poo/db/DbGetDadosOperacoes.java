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
 * Classe responsável por acessar os dados das operações a partir do banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 * @version 1.0
 */
public class DbGetDadosOperacoes {

	/**Método principal, responsável por realizar a consulta das operações no banco de dados e retornar suas informações para serem utilizadas no extrato.
	 * @param codConta int - código da conta
	 * @param codBanco int - código do banco referente a operação
	 * @return extrato ArrayList - retorna um ArrayList contendo todas as operações de uma conta, gravadas no banco de dados
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
