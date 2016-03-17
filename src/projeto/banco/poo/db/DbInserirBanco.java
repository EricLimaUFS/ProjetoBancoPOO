/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.*;

/**
 * Classe responsável por inserir um novo banco no banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class DbInserirBanco {

	/**Método principal, responsável por passar as informações do banco para o banco de dados.
	 * @param codBanco int - codigo do banco
	 * @return boolean - true
	 */
	public static boolean main(int codBanco) {
		
		boolean retorno = true;
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			statement = conexao.createStatement();
			

			// Criação das tabelas para o novo banco de dados criado
			String criarTableClientes = "CREATE TABLE clientes (codigo INT(11), cpf_cnpj BIGINT(20), "
					+ "nome_razaosocial VARCHAR(255), renda_mensal DOUBLE, data_cadastro VARCHAR(11), "
					+ "data_nascimento VARCHAR(11), nome_fantasia VARCHAR(255), tipo INT(1))";

			String criarTableAgencias = "CREATE TABLE agencias (codigo INT(11), banco INT(11), endereco VARCHAR(255), "
					+ "data_cadastro VARCHAR(11))";

			String criarTableContas = "CREATE TABLE contas (codigo INT(11), banco INT(11), agencia INT(11), "
					+ "cliente INT(11), saldo DOUBLE, credito DOUBLE, divida DOUBLE, senha VARCHAR(7), "
					+ "data_cadastro VARCHAR(11))";

			String criarTableOperacoes = "CREATE TABLE operacoes (codigo INT(11), tipo_operacao INT(1), valor_operacao DOUBLE, banco INT(11), "
					+ "agencia INT(11), conta INT(11), conta2 INT(11), saldo_conta DOUBLE, saldo_conta2 DOUBLE, data VARCHAR(11))";

			String criarTableBanco = "CREATE TABLE banco (codigo INT(11), razao_social VARCHAR(255), "
					+ "cnpj VARCHAR(20), nome_fantasia VARCHAR(255), endereco VARCHAR(255), data_cadastro VARCHAR(11))";

			statement.executeUpdate(criarTableAgencias);
			statement.executeUpdate(criarTableBanco);
			statement.executeUpdate(criarTableClientes);
			statement.executeUpdate(criarTableContas);
			statement.executeUpdate(criarTableOperacoes);
			//System.out.println("Tabelas criadas com sucesso!");
		} catch (Exception e)
	    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		}
		return retorno;
	}
	

}
