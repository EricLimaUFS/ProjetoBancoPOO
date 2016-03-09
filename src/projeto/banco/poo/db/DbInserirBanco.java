/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.*;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 */
public class DbInserirBanco {

	/**
	 * @param args
	 * 
	 */
	public static boolean main(int codBanco) {
		
		boolean retorno = true;
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			statement = conexao.createStatement();
			
			System.out.println("Banco de dados 'banco" + codBanco + "' criado com sucesso!");

			// Criação das tabelas para o novo banco de dados criado
			String criarTableClientes = "CREATE TABLE clientes (codigo INT(11), cpf_cnpj VARCHAR(20), "
					+ "nome_razaosocial VARCHAR(255), renda_mensal DOUBLE, data_cadastro VARCHAR(11), "
					+ "data_nascimento VARCHAR(11), nome_fantasia VARCHAR(255))";

			String criarTableAgencias = "CREATE TABLE agencias (codigo INT(11), banco INT(11), endereco VARCHAR(255), "
					+ "data_cadastro VARCHAR(11))";

			String criarTableContas = "CREATE TABLE contas (codigo INT(11), banco INT(11), agencia INT(11), "
					+ "cliente INT(11), saldo DOUBLE, credito DOUBLE, divida DOUBLE, senha VARCHAR(7), "
					+ "data_cadastro VARCHAR(11))";

			String criarTableOperacoes = "CREATE TABLE operacoes (codigo INT(11), tipo_operacao INT(1), banco INT(11), "
					+ "agencia INT(11), cliente INT(11), cliente2 INT(11), data VARCHAR(11))";

			String criarTableBanco = "CREATE TABLE banco (codigo INT(11), razao_social VARCHAR(255), "
					+ "cnpj VARCHAR(20), nome_fantasia VARCHAR(255), endereco VARCHAR(255), data_cadastro VARCHAR(11))";

			statement.executeUpdate(criarTableAgencias);
			statement.executeUpdate(criarTableBanco);
			statement.executeUpdate(criarTableClientes);
			statement.executeUpdate(criarTableContas);
			statement.executeUpdate(criarTableOperacoes);
			System.out.println("Tabelas criadas com sucesso!");
		} catch (Exception e)
	    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		}
		return retorno;
	}
	

}
