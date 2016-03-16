/**
 * 
 */
package projeto.banco.poo.appaux;

import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoNovaConta;
import projeto.banco.poo.db.DbInserirCliente;
import projeto.banco.poo.db.DbInserirConta;

import java.util.Scanner;

/**
 * Classe responsável por inserir os dados de um cliente.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 12 de mar de 2016
 * @version 1.0
 */
public class AppInserirCliente {

	/**Método principal, responsável por recolher os dados necessários para inserir no objeto cliente e enviá-los ao 
	 * banco de dados.
	 * @param codBanco int - código do banco que o cliente irá pertencer
	 * @return boolean - retorno */
	public static boolean main(int codBanco) {

		Scanner ler = new Scanner(System.in);
		Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
		String menu = null;
		
		System.out.println("Escolha o tipo de cliente:\n");
		System.out.println("1 - Pessoa Física");
		System.out.println("2 - Pessoa Jurídica");
		menu = ler.next();
		ler.nextLine();
		
		if (menu == "1") {
			System.out.println("Insira os dados do cliente\n");
			System.out.println("Nome:");
			cliente.setNome(ler.nextLine());
			System.out.println("CPF (apenas números):");
			cliente.setCpf(ler.nextLong());
			System.out.println("Renda Mensal:");
			cliente.setRendaMensal(ler.nextDouble());
			ler.nextLine();
			System.out.println("Data de Nascimento:");
			cliente.setDataNascimento(ler.nextLine());
			
			cliente.setTipo(1);
			cliente.setDataCadastro(MetodosAuxiliares.getDataAtual());
			cliente.setCodigo(DbGetCodigoNovaConta.main(codBanco) + 1);
			
			DbInserirCliente.main(cliente, codBanco);
			
		} else {
			System.out.println("Insira os dados do cliente\n");
			System.out.println("Razão Social:");
			cliente.setRazaoSocial(ler.nextLine());
			System.out.println("CNPJ (apenas números):");
			cliente.setCnpj(ler.nextLong());
			System.out.println("Renda Mensal:");
			cliente.setRendaMensal(ler.nextDouble());
			ler.nextLine();
			System.out.println("Nome Fantasia:");
			cliente.setNomeFantasia(ler.nextLine());
			
			cliente.setTipo(2);
			cliente.setDataCadastro(MetodosAuxiliares.getDataAtual());
			cliente.setCodigo(DbGetCodigoNovaConta.main(codBanco) + 1);
			
			DbInserirCliente.main(cliente, codBanco);
		}
		
		return true;
	}

}
