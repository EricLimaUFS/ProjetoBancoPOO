/**
 * 
 */
package projeto.banco.poo.appaux;

import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbInserirBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Classe responsável por inserir os dados de um banco.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 10 de mar de 2016
 * @version 1.0
 */
public class AppInserirBanco {

	/**Método principal, responsável por recolher os dados necessários para inserir o banco e enviá-los ao banco de dados.
	 * @return boolean - retorno */
	public static boolean main() {

		Scanner ler = new Scanner(System.in);
		Banco banco = new Banco(0, null, null, null, null, null);

		System.out.println("Insira os dados do banco\n");
		System.out.println("Razao Social:");
		banco.setRazaoSocial(ler.nextLine());
		System.out.println("CNPJ:");
		banco.setCnpj(ler.nextLine());
		System.out.println("Nome Fantasia:");
		banco.setNomeFantasia(ler.nextLine());
		System.out.println("Endereço:");
		banco.setEndereco(ler.nextLine());

		banco.setDataCadastro(MetodosAuxiliares.getDataAtual());

		int i = 0;
		for (i = 0; i < 10; i++) {
			if (DbGetCodigoBanco.main(i) == true)
				break;
		}
		banco.setCodigo(i);

		if (DbInserirBanco.main(banco.getCodigo()) == true)
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conexao = DriverManager.getConnection("jdbc:sqlite:banco" + banco.getCodigo() + ".db");
				Statement statement = conexao.createStatement();
				String sql = "INSERT INTO banco (codigo, razao_social, cnpj, nome_fantasia, endereco, data_cadastro) "
						+ "VALUES ('" + banco.getCodigo() + "', '" + banco.getRazaoSocial() + "', " + "'"
						+ banco.getCnpj() + "', '" + banco.getNomeFantasia() + "', " + "'" + banco.getEndereco()
						+ "', '" + banco.getDataCadastro() + "')";
				statement.executeUpdate(sql);
				System.out.println(
						banco.getRazaoSocial() + " cadastrado com sucesso! O código do banco é: " + banco.getCodigo());
			} catch (Exception e) {
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
			}
		return true;
	}

}
