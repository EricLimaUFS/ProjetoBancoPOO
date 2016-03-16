/**
 * 
 */
package projeto.banco.poo.app;

import java.util.Scanner;

import projeto.banco.poo.core.Banco;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetDadosBancos;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 15 de mar de 2016
 */
public class AppMainSemDados {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;

		while (volta = true) {
			volta = false;
			System.out.println("Projeto App Banco - Universidade Federal de Sergipe - DCOMP\n");
			System.out.println("Programação Orientada a Objetos	  -   Turma 02   -   2015.2\n");
			System.out.println("Docente: Kalil Araujo Bispo\n");
			System.out.println("Discentes: Bruno Rodrigues dos Santos  -  Eric Fonseca Lima\n\n");
			System.out.println("1 - Cadastros");
			System.out.println("2 - Operações");
			System.out.println("3 - Menu Interativo (opcional)");
			menu = ler.nextLine();

			switch (menu) {
			case "1": {
				menuCadastros();
			}
				break;
			case "2": {
				// menu operacoes
			}
				break;
			case "3": {
				AppInicio.main(args);
			}
				break;
			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}
			}
		}

	}

	private static void menuCadastros() {

		Scanner ler = new Scanner(System.in);
		String menu = null;
		boolean volta = true;

		System.out.println("1 - Cadastro de bancos");
		System.out.println("2 - Cadastro de agências");
		System.out.println("3 - Cadastro de clientes");
		System.out.println("4 - Cadastro de contas");
		System.out.println("5 - Voltar");
		menu = ler.nextLine();

		switch (menu) {
		case "1": {
			AppBancoCentral.menuCadastroDeBancos();
		}
			break;
		case "2": {
			Banco banco = new Banco(0, null, null, null, null, null);

			System.out.println("Digite o código do banco que deseja conectar-se: ");
			banco.setCodigo(ler.nextInt());
			ler.nextLine();

			if (DbGetCodigoBanco.main(banco.getCodigo()) == false) {
				banco = DbGetDadosBancos.main(banco.getCodigo());
				AppBanco.menuCadastroDeAgencias(banco.getCodigo());
			}
		}
			break;
		case "3": {
			// menu cadastro de clientes
		}
			break;
		case "4": {
			// menu cadastro de contas
		}
			break;
		case "5": {
			// voltar
		}
			break;
		default: {
			System.out.println("Opção inválida! Digite apenas números de 1 a 5.");
		}
		}
	}

}
