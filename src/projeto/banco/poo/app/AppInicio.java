/**
 * 
 */
package projeto.banco.poo.app;

import java.util.Scanner;


/**
 * Classe para aplicação do menu interativo, onde estão disponíveis as opções para o funcionário do 
 * banco central, ciente e para o funcionário de um banco 
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class AppInicio {

	/**Método principal, responsável por exibir o menu e chamar a classe correspondente a opção selecionada.
	 * @param args */
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
			System.out.println("1 - Sou o Banco Central		[Cadastrar, alterar e consultar dados de bancos]\n");
			System.out.println("2 - Sou um Banco		[Cadastrar, alterar e consultar dados de um banco]\n				[incluindo suas agências, contas e clientes]\n");
			System.out.println("3 - Sou um cliente		[Terminal de auto atendimento]\n");
			System.out.println("4 - Voltar");

			menu = ler.nextByte();
			
			switch (menu) {
			case 1: {
				if (AppBancoCentral.main() == true)
					retorno = true;
			}
				break;
			case 2: {
				if (AppBanco.main() == true)
					retorno = true;
			}
				break;
			case 3: {
				if (AppCaixa.main() == true)
					retorno = true;				
			}
				break;
			case 4: {
				AppPrincipal.main(null);
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
