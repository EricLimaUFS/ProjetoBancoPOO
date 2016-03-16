/**
 * 
 */
package projeto.banco.poo.db;

/**
 * Classe responsável por guardar o método construtor e os métodos acessores e modificadores de Banco no banco de dados.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class DbBanco {

	private String banco;
	private int codigo;

	/**
	 * 
	 */
	public DbBanco(String banco, int codigo) {

		this.banco = banco;
		this.codigo = codigo;

	}
	
	public String getBanco() {
		return banco;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param args
	 */

	public static void main(String[] args) {

	}

}
