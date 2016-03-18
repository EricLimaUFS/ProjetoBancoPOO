/**
 * 
 */
package projeto.banco.poo.core;

/**
 * Classe responsável por guardar o método construtor e os métodos acessores e modificadores de Agencia.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class Agencia {
	
	private int codigo;
	private int banco;
	private String endereco;
	private String dataCadastro;
	
	/**Método construtor, responsável por inicializar os atributos de Agencia.
	 * @param codigo int - código da agencia
	 * @param banco int - código do banco referente à agencia
	 * @param endereco String - endereço da agencia
	 * @param dataCadastro String - data em que a agencia foi criada */
	public Agencia(int codigo, int banco, String endereco, String dataCadastro) {
		
		this.codigo = codigo;
		this.banco = banco;
		this.endereco = endereco;
		this.dataCadastro = dataCadastro;
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getBanco() {
		return banco;
	}
	
	public void setBanco(int banco) {
		this.banco = banco;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
