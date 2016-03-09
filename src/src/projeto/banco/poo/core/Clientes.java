/**
 * 
 */
package projeto.banco.poo.core;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 */
public class Clientes {
	
	private int codigo;
	private int cpf;
	private int cnpj;
	private String nome;
	private String razaoSocial;
	private String nomeFantasia;
	private double rendaMensal;
	private String dataCadastro;
	private String dataNascimento;
	
	/**
	 * 
	 */
	public Clientes(int codigo, int cpf, int cnpj, String nome, String razaoSocial, String nomeFantasia,
					double rendaMensal, String dataCadastro, String dataNascimento) {

		this.codigo = codigo;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.rendaMensal = rendaMensal;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public int getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(int cnpj) {
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

}
