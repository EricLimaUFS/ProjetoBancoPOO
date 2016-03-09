/**
 * 
 */
package projeto.banco.poo.app;

import java.util.Scanner;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 */
public class AppInicio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		byte menu;
		boolean retorno = true;

		do {

			retorno = false;
			System.out.println("Projeto App Banco - Universidade Federal de Sergipe - DCOMP");
			System.out.println();
			System.out.println("Programação Orientada a Objetos	  -   Turma 02   -   2015.2");
			System.out.println();
			System.out.println("Docente: Kalil Araujo Bispo");
			System.out.println();
			System.out.println("Discentes: Bruno Rodrigues dos Santos  -  Eric Fonseca Lima");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Escolha uma opção para iniciar o aplicativo correspondente:");
			System.out.println();
			System.out.println("1 - Sou o Banco Central");
			System.out.println("2 - Sou um Banco");
			System.out.println("3 - Sou um cliente");
			System.out.println("4 - Sair");

			menu = ler.nextByte();
			
			switch (menu) {
			case 1: {
				if (AppBancoCentral.main() == true)
					retorno = true;
			}
				break;
			case 2: {
				if (AppBanco.main(args) == true)
					retorno = true;
			}
				break;
			case 3: {
				if (AppCaixa.main(args) == true)
					retorno = true;
			}
				break;
			case 4: {
				System.exit(1);
			}
				break;
			default: {
				System.out.println("Opção inválida! Tente novamente utilizando apenas números de 1 a 4.\n");
				retorno = true;
			}
			}
		} while (retorno = true);
	}

}
