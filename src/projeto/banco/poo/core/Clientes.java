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
	private long cpf;
	private long cnpj;
	private String nome;
	private String razaoSocial;
	private String nomeFantasia;
	private double rendaMensal;
	private String dataCadastro;
	private String dataNascimento;
	private int tipo; // Pessoa física ou jurídica
	
	/**
	 * 
	 */
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
