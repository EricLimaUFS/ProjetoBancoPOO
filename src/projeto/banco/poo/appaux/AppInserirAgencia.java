/**
 * 
 */
package projeto.banco.poo.appaux;

import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetCodigoNovaAgencia;
import projeto.banco.poo.db.DbInserirBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 10 de mar de 2016
 */
public class AppInserirAgencia {

	/**
	 * @param args
	 */
	public static boolean main(int codBanco) {

		Scanner ler = new Scanner(System.in);
		Agencia agencia = new Agencia(0, codBanco, null, null);

		System.out.println("Insira os dados da agência\n");
		System.out.println("Endereço:");
		agencia.setEndereco(ler.nextLine());

		agencia.setDataCadastro(MetodosAuxiliares.getDataAtual());

		agencia.setCodigo(DbGetCodigoNovaAgencia.main(codBanco));

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
