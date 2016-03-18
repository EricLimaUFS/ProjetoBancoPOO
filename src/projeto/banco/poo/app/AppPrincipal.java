/**
 * 
 */
package projeto.banco.poo.app;

import java.util.Scanner;


/**
 * Classe para exibição do menu início
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class AppPrincipal {

	/**Método principal, responsável por exibir o menu e chamar a classe correspondente a opção selecionada.
	 * @param args */
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
			System.out.println("4 - Sair");
			menu = ler.nextLine();

			switch (menu) {
			case "1": {
				MenuApp.menuCadastros();
			}
				break;
			case "2": {
				MenuApp.menuOperacoes();
			}
				break;
			case "3": {
				AppInicio.main(args);
			}
				break;
			case "4": {
				System.exit(1);
			}
			default: {
				System.out.println("Opção inválida! Digite apenas números de 1 a 3.");
			}
			}
		}

	}

}
