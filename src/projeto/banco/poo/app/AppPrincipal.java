/**
 * 
 */
package projeto.banco.poo.app;

import java.util.ArrayList;
import java.util.Scanner;

import projeto.banco.poo.appaux.AppAlterarCliente;
import projeto.banco.poo.appaux.AppExcluirCliente;
import projeto.banco.poo.appaux.AppExibirExtrato;
import projeto.banco.poo.appaux.AppGerirAgencia;
import projeto.banco.poo.appaux.AppInserirCliente;
import projeto.banco.poo.core.Agencia;
import projeto.banco.poo.core.Banco;
import projeto.banco.poo.core.Clientes;
import projeto.banco.poo.core.Contas;
import projeto.banco.poo.core.MetodosAuxiliares;
import projeto.banco.poo.db.DbGetCodigoBanco;
import projeto.banco.poo.db.DbGetContasCliente;
import projeto.banco.poo.db.DbGetDadosAgencias;
import projeto.banco.poo.db.DbGetDadosBancos;
import projeto.banco.poo.db.DbGetDadosClientes;
import projeto.banco.poo.db.DbGetDadosContas;
import projeto.banco.poo.db.DbPesquisarCliente;

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
				menuApp.menuCadastros();
			}
				break;
			case "2": {
				menuApp.menuOperacoes();
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
