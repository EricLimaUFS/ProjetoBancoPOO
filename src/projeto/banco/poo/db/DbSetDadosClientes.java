/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.core.Clientes;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 */
public class DbSetDadosClientes {

	/**
	 * @param args
	 */
	public static boolean main(Clientes novoObjetoCliente, int codBanco) {

		boolean retorno = true;
		Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
		cliente = novoObjetoCliente;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();
			
			if (cliente.getTipo() == 1) {
				String query = "UPDATE clientes SET renda_mensal='" + cliente.getRendaMensal() + "', "
						+ "data_nascimento='" + cliente.getDataNascimento() + "' WHERE codigo=" + cliente.getCodigo();
				
				statement.executeUpdate(query);

				statement.close();
			} else {
				String query = "UPDATE clientes SET renda_mensal='" + cliente.getRendaMensal() + "', "
						+ "nome_fantasia='" + cliente.getNomeFantasia() + "' WHERE codigo=" + cliente.getCodigo();
				
				statement.executeUpdate(query);

				statement.close();
			}


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
