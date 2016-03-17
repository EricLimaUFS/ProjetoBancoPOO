/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.core.Clientes;

/**
 * Classe responsável por inserir um novo cliente no banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 * @version 1.0
 */
public class DbInserirCliente {

	/**Método principal, responsável por passar as informações do objeto cliente para o banco de dados.
	 * @param cliente Clientes - objeto do tipo Clientes
	 * @param codBanco int - codigo do banco
	 * @return boolean - true
	 */
	public static boolean main(Clientes cliente, int codBanco) {
		
		Connection conexao = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			statement = conexao.createStatement();
			
			if (cliente.getTipo() == 1) {
			String sql = "INSERT INTO clientes (codigo, cpf_cnpj, nome_razaosocial, renda_mensal, data_cadastro, data_nascimento, nome_fantasia, tipo) " + "VALUES ('"
					+ cliente.getCodigo() + "', '" + cliente.getCpf() + "', " + "'" + cliente.getNome() + "', '"
					+ cliente.getRendaMensal() + "', '" + cliente.getDataCadastro() + "', '" + cliente.getDataNascimento() + "', '" 
					+ cliente.getNomeFantasia() + "'" + ", '" + cliente.getTipo() + "')";
			
			statement.executeUpdate(sql);
			
			System.out.println("Cliente cadastrado com sucesso! O código do cliente é: " + cliente.getCodigo());
			statement.close();
			conexao.close();
			} else {
				String sql = "INSERT INTO clientes (codigo, cpf_cnpj, nome_razaosocial, renda_mensal, data_cadastro, data_nascimento, nome_fantasia, tipo) " + "VALUES ('"
						+ cliente.getCodigo() + "', '" + cliente.getCnpj() + "', " + "'" + cliente.getRazaoSocial() + "', '"
						+ cliente.getRendaMensal() + "', '" + cliente.getDataCadastro() + "', '" + cliente.getDataNascimento() + "', '" 
						+ cliente.getNomeFantasia() + "'" + ", '" + cliente.getTipo() + "')";
				
				statement.executeUpdate(sql);
				
				System.out.println("Cliente cadastrado com sucesso! O código do cliente é: " + cliente.getCodigo());
				statement.close();
				conexao.close();
			}
			
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return true;
	}

}
