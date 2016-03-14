/**
 * 
 */
package projeto.banco.poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 */
public class DbSetDadosContas {

	/**
	 * @param args
	 */
	public static boolean main(Contas novoObjetoConta, int codBanco) {

		boolean retorno = true;
		Contas conta = new Contas(0, codBanco, 0, 0, 0, 0, 0, null, null);
		conta = novoObjetoConta;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();
			
			String query = "UPDATE contas SET saldo=" + conta.getSaldo() + ", "
						+ "credito=" + conta.getCredito() + ", divida=" + conta.getDivida() + ", "
						+ "senha='" + conta.getSenha() + "' WHERE codigo=" + conta.getCodigo();
				
			statement.executeUpdate(query);

			statement.close();

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
