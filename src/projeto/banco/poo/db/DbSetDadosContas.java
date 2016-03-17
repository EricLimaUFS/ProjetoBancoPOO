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
 * Classe responsável por modificar os dados da conta a partir do banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 17 de mar de 2016
 * @version 1.0
 */
public class DbSetDadosContas {

	/**Método principal, responsável por modificar as informações de um objeto do tipo Contas no banco de 
	 * dados e retornar suas informações.
	 * @param novoObjetoConta Conttas - objeto do tipo Contas
	 * @param codBanco int - código do banco referente a conta
	 * @return retorno boolean
	 */
	public static boolean main(Contas novoObjetoConta, int codBanco) {

		boolean retorno = true;
		Contas conta = new Contas(0, codBanco, 0, 0, 0, 0, 0, null, null);
		conta = novoObjetoConta;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + codBanco + ".db");
			Statement statement = conexao.createStatement();
			
			String query = "UPDATE contas SET saldo='" + conta.getSaldo() + "', "
						+ "credito='" + conta.getCredito() + "', divida='" + conta.getDivida() + "', "
						+ "senha='" + conta.getSenha() + "' WHERE codigo='" + conta.getCodigo() +"'";
				
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
