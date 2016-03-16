/**
 * 
 */
package projeto.banco.poo.appaux;
import java.util.*;

import projeto.banco.poo.core.Contas;
import projeto.banco.poo.db.DbExcluirConta;
import projeto.banco.poo.db.DbGetDadosContas;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 14 de mar de 2016
 */
public class AppExcluirConta {

	/**
	 * @param args
	 */
	public static void main(int codBanco, int codAgencia) {
		
		Scanner ler = new Scanner(System.in);
		byte menu = 0;
		System.out.println("Digite o número da conta que deseja excluir: ");
		int codConta = ler.nextInt();
		Contas conta = new Contas(0, codBanco, codAgencia, 0, 0, 0, 0, null, null);
		conta = DbGetDadosContas.main(codConta, codBanco);
		
		if ((conta.getDataCadastro() != null) && (conta.getSaldo() == 0) && (conta.getDivida() == 0)) {
			System.out.println("Tem certeza que deseja excluir esta conta?\nApós a operação a ação não poderá ser desfeita.");
			System.out.println("1 - Sim\n2 - Não");
			menu = ler.nextByte();
			ler.nextLine();
			
			switch (menu) {
			case 1: {
				DbExcluirConta.main(codConta, codBanco);
			} break;
			case 2: {
				// volta
			}
			}
			
		} else if ((conta.getDataCadastro() != null) && (conta.getSaldo() != 0) || (conta.getDivida() != 0)){
			System.out.println("A conta possui saldo disponível ou há dívida com o banco!"
					+ "\nTente novamente após remover o saldo ou a dívida.\n\nPressione ENTER para continuar.");
			ler.nextLine();
			ler.nextLine();
		}

	}

}
