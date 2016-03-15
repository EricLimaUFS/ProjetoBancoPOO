/**
 * 
 */
package projeto.banco.poo.appaux;
import java.util.*;

import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.Operacoes;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbGetDadosContas;
import projeto.banco.poo.db.DbGetDadosOperacoes;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 13 de mar de 2016
 */
public class AppExibirExtrato {

	/**
	 * @param args
	 */
	public static void main(int codConta, int codBanco) {
		
		Scanner ler = new Scanner(System.in);
		Contas conta = new Contas(codConta, codBanco, 0, 0, 0, 0, 0, null, null);
		conta = DbGetDadosContas.main(codConta, codBanco);
		Clientes cliente = new Clientes(conta.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(conta.getCliente(), codBanco);
		
		ArrayList<Operacoes> extrato = new ArrayList<Operacoes>();
		
		extrato = DbGetDadosOperacoes.main(0, codConta);
		
		if (cliente.getTipo() == 1)
			System.out.println("				EXTRATO 		- 		" + cliente.getNome() + " - Conta: " + codConta);
		else
			System.out.println("				EXTRATO 		-		" + cliente.getRazaoSocial());
		
		System.out.println("__________________________________________________________________________"
				+ "_______________________________________________");
		System.out.println("|     DATA	|				HISTÓRICO				|	VALOR	|	SALDO	|");
		
		for (int i = 0; i < extrato.size(); i++) {
			
			System.out.println("|_______________|_________________________________________________________"
					+ "______________|_______________|_______________|");
			
			if (extrato.get(i).getTipoOperacao() == 1) {
				
				System.out.print("|  " + extrato.get(i).getData() + "	|");
				System.out.print("	Depósito em dinheiro						|");
				System.out.print("+ R$" + extrato.get(i).getValorOperacao() + "	|");
				System.out.println(" R$" + extrato.get(i).getSaldoConta() + "	|");
				
			} else if (extrato.get(i).getTipoOperacao() == 2) {
				
				System.out.print("|  " + extrato.get(i).getData() + "	|");
				System.out.print("	Saque em terminal						|");
				System.out.print("- R$" + extrato.get(i).getValorOperacao() + "	|");
				System.out.println(" R$" + extrato.get(i).getSaldoConta() + "	|");
				
			} else if ((extrato.get(i).getTipoOperacao() == 3) && (extrato.get(i).getConta() == codConta) && (cliente.getTipo() == 1)) {
				
				Contas conta2 = new Contas(extrato.get(i).getConta2(), codBanco, 0, 0, 0, 0, 0, null, null);
				conta2 = DbGetDadosContas.main(extrato.get(i).getConta2(), codBanco);
				Clientes cliente2 = new Clientes(conta2.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
				cliente2 = DbGetDadosClientes.main(conta2.getCliente(), codBanco);
				
				System.out.print("|  " + extrato.get(i).getData() + "	|");
				System.out.print("	Débito ref. Transferência p/ " + cliente2.getNome() + "				|");
				System.out.print("- R$" + extrato.get(i).getValorOperacao() + "	|");
				System.out.println(" R$" + extrato.get(i).getSaldoConta() + "	|");
				
			} else if ((extrato.get(i).getTipoOperacao() == 3) && (extrato.get(i).getConta() == codConta) && (cliente.getTipo() == 2)) {
				
				Contas conta2 = new Contas(extrato.get(i).getConta2(), codBanco, 0, 0, 0, 0, 0, null, null);
				conta2 = DbGetDadosContas.main(extrato.get(i).getConta2(), codBanco);
				Clientes cliente2 = new Clientes(conta2.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
				cliente2 = DbGetDadosClientes.main(conta2.getCliente(), codBanco);
				
				System.out.print("|  " + extrato.get(i).getData() + "	|");
				System.out.print("	Débito ref. Transferência p/ " + cliente2.getNomeFantasia() + "				|");
				System.out.print("- R$" + extrato.get(i).getValorOperacao() + "	|");
				System.out.println(" R$" + extrato.get(i).getSaldoConta() + "	|");
				
			} else if ((extrato.get(i).getTipoOperacao() == 3) && (extrato.get(i).getConta() != codConta) && (cliente.getTipo() == 1)) {
				
				Contas conta2 = new Contas(extrato.get(i).getConta(), codBanco, 0, 0, 0, 0, 0, null, null);
				conta2 = DbGetDadosContas.main(extrato.get(i).getConta(), codBanco);
				Clientes cliente2 = new Clientes(conta2.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
				cliente2 = DbGetDadosClientes.main(conta2.getCliente(), codBanco);
				
				System.out.print("|  " + extrato.get(i).getData() + "	|");
				System.out.print("	Crédito ref. Transferência de " + cliente2.getNome() + "				|");
				System.out.print("+ R$" + extrato.get(i).getValorOperacao() + "	|");
				System.out.println(" R$" + extrato.get(i).getSaldoConta2() + "	|");
				
			} else if ((extrato.get(i).getTipoOperacao() == 3) && (extrato.get(i).getConta() != codConta) && (cliente.getTipo() == 2)) {
				
				Contas conta2 = new Contas(extrato.get(i).getConta(), codBanco, 0, 0, 0, 0, 0, null, null);
				conta2 = DbGetDadosContas.main(extrato.get(i).getConta(), codBanco);
				Clientes cliente2 = new Clientes(conta2.getCliente(), 0, 0, null, null, null, 0, null, null, 0);
				cliente2 = DbGetDadosClientes.main(conta2.getCliente(), codBanco);
				
				System.out.print("|  " + extrato.get(i).getData() + "	|");
				System.out.print("	Crédito ref. Transferência de " + cliente2.getNomeFantasia() + "				|");
				System.out.print("+ R$" + extrato.get(i).getValorOperacao() + "	|");
				System.out.println(" R$" + extrato.get(i).getSaldoConta2() + "	|");
				
			} else if (extrato.get(i).getTipoOperacao() == 4) {
				
				System.out.print("|  " + extrato.get(i).getData() + "	|");
				System.out.print("	Débito ref. Pagamento						|");
				System.out.print("- R$" + extrato.get(i).getValorOperacao() + "	|");
				System.out.println(" R$" + extrato.get(i).getSaldoConta() + "	|");
				
			}
			
		}
		System.out.println("|_______________|_________________________________________________________"
				+ "______________|_______________|_______________|");

	}

}
