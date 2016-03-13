/**
 * 
 */
package projeto.banco.poo.appaux;

import java.util.*;

import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbSetDadosClientes;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 11 de mar de 2016
 */
public class AppAlterarCliente {

	/**
	 * @param args
	 */
	public static boolean main(int codCliente, int codBanco) {

		boolean retorno = true;
		Scanner ler = new Scanner(System.in);
		Clientes cliente = new Clientes(0, 0, 0, null, null, null, 0, null, null, 0);
		byte menu = 0;
		String strNovosDados;
		double dblNovosDados;

		cliente = DbGetDadosClientes.main(codCliente, codBanco);
		
		

		if (cliente.getTipo() == 1) {
			System.out.println("Cliente selecionado: " + cliente.getNome() + "\n");
			System.out.println("Escolha o que deseja alterar:\n");
			System.out.println("1 - Renda Mensal");
			System.out.println("2 - Voltar");
			menu = ler.nextByte();
			ler.nextLine();
			
			switch (menu) {
			case 1: {
				System.out.println("Renda Mensal atual: " + cliente.getRendaMensal());
				System.out.println("\nDigite a nova renda mensal:\n\nPara cancelar a operação, digite o número '1' (um).");
				dblNovosDados = ler.nextDouble();
				if (dblNovosDados == 1.0) {
					// voltar
				} else {
					cliente.setRendaMensal(dblNovosDados);
					DbSetDadosClientes.main(cliente, codBanco);
				}
			}
				break;
			case 2: {
				// voltar
				retorno = false;
			}
				break;
			default: {
				System.out.println("Opção inválida! Tente novamente usando apenas os números 1 ou 2.");
			}
			}
		} else {
			System.out.println("Cliente selecionado: " + cliente.getRazaoSocial() + "\n");
			System.out.println("Escolha o que deseja alterar:\n");
			System.out.println("1 - Renda Mensal");
			System.out.println("2 - Nome Fantasia");
			System.out.println("3 - Voltar");
			menu = ler.nextByte();
			ler.nextLine();
			
			switch (menu) {
			case 1: {
				System.out.println("Renda Mensal atual: " + cliente.getRendaMensal());
				System.out.println("\nDigite a nova renda mensal:\n\nPara cancelar a operação, digite o número '1' (um).");
				dblNovosDados = ler.nextDouble();
				ler.nextLine();
				if (dblNovosDados == 1.0) {
					// voltar
				} else {
					cliente.setRendaMensal(dblNovosDados);
					DbSetDadosClientes.main(cliente, codBanco);
				}
			}
				break;
			case 2: {
				System.out.println("Nome Fantasia atual: " + cliente.getNomeFantasia());
				System.out.println("\nDigite o novo nome fantasia:\n\nPara cancelar a operação, digite 'cancelar'.");
				strNovosDados = ler.nextLine();
				if (strNovosDados.equals("cancelar")) {
					// voltar
				} else {
					cliente.setNomeFantasia(strNovosDados);
					DbSetDadosClientes.main(cliente, codBanco);
				}
			}
				break;
			case 3: {
				// voltar
				retorno = false;
			}
			default: {
				System.out.println("Opção inválida! Tente novamente usando apenas números de 1 a 3.");
			}
			}
		}

		

		return retorno;
	}

}
