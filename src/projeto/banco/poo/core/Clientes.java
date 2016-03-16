/**
 * 
 */
package projeto.banco.poo.core;

/**
 * Classe responsável por guardar o método construtor e os métodos acessores e modificadores de Cliente.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 5 de mar de 2016
 * @version 1.0
 */
public class Clientes {
	
	private int codigo;
	private long cpf;
	private long cnpj;
	private String nome;
	private String razaoSocial;
	private String nomeFantasia;
	private double rendaMensal;
	private String dataCadastro;
	private String dataNascimento;
	private int tipo; // Pessoa física ou jurídica
	
	/**Método construtor, responsável por inicializar os atributos de Clientes.
	 * @param codigo int - código do cliente
	 * @param cpf int - cpf do cliente, em caso de pessoa física
	 * @param cnpj String - cnpj do cliente, em caso de pessoa jurídica
	 * @param nome String - nome do cliente, em caso de pessoa física
	 * @param razaoSocial String - nome do cliente, em caso de pessoa jurídica
	 * @param nomeFantasia String - nome fantasia do cliente, em caso de pessoa jurídica
	 * @param rendaMensal double - renda mensal do cliente 
	 * @param dataCadastro String - data em que a conta foi criada 
	 * @param dataNascimento String - data de nascimento 
	 * @param tipo int - tipo do cliente, 1 para pessoa física e 2 para pessoa jurídica*/
	public Clientes(int codigo, long cpf, long cnpj, String nome, String razaoSocial, String nomeFantasia,
					double rendaMensal, String dataCadastro, String dataNascimento, int tipo) {

		this.codigo = codigo;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.rendaMensal = rendaMensal;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
		this.tipo = tipo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public long getCpf() {
		return cpf;
	}
	
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	
	public long getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	public double getRendaMensal() {
		return rendaMensal;
	}
	
	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}
	
	public String getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
