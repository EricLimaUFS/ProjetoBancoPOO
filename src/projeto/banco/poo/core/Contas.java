/**
 * 
 */
package projeto.banco.poo.core;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 */
public class Contas {
	
	private int codigo;
	private int banco;
	private int agencia;
	private int cliente;
	private double saldo;
	private double credito;
	private double divida;
	private String senha;
	private String dataCadastro;
	
	/**
	 * 
	 */
	public Contas(int codigo, int banco, int agencia, int cliente, double saldo, double credito, double divida,
					String senha, String dataCadastro) {

		this.codigo = codigo;
		this.banco = banco;
		this.agencia = agencia;
		this.cliente = cliente;
		this.saldo = saldo;
		this.credito = credito;
		this.divida = divida;
		this.senha = senha;
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
	
	public int getAgencia() {
		return agencia;
	}
	
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	
	public int getCliente() {
		return cliente;
	}
	
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public double getCredito() {
		return credito;
	}
	
	public void setCredito(double credito) {
		this.credito = credito;
	}
	
	public double getDivida() {
		return divida;
	}
	
	public void setDivida(double divida) {
		this.divida = divida;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
		
}
