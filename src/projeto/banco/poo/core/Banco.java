/**
 * 
 */
package projeto.banco.poo.core;

/**
 * Classe responsável por guardar o método construtor e os métodos acessores e modificadores de Banco.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class Banco {

	private int codigo;
	private String razaoSocial;
	private String cnpj;
	private String nomeFantasia;
	private String endereco;
	private String dataCadastro;
	
	/**Método construtor, responsável por inicializar os atributos de Banco.
	 * @param codigo int - código do banco
	 * @param razaoSocial String - nome do banco
	 * @param cnpj String - cnpj do banco
	 * @param nomeFantasia String - nome fantasia do banco
	 * @param endereco String - endereço do banco
	 * @param dataCadastro String - data em que o banco foi criado */
	public Banco(int codigo, String razaoSocial, String cnpj, String nomeFantasia, String endereco, String dataCadastro) {
		
		this.codigo = codigo;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.endereco = endereco;
		this.dataCadastro = dataCadastro;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
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
