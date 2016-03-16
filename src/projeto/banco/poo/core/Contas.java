/**
 * 
 */
package projeto.banco.poo.core;

import projeto.banco.poo.db.DbGetDadosClientes;

/**
 * Classe responsável por guardar o método construtor e os métodos acessores e modificadores de Contas.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
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
	
	/**Método construtor, responsável por inicializar os atributos de Contas.
	 * @param codigo int - código da conta
	 * @param banco int - banco da conta referente
	 * @param agencia int - agencia da conta referente
	 * @param cliente int - codigo do cliente da conta referente
	 * @param saldo double - saldo da conta
	 * @param credito double - credito da conta
	 * @param divida double - divida da conta
	 * @param senha String - senha da conta
	 * @param dataCadastro String - data em que a conta foi criada */
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
	
	public static double CalcularCredito(int codCliente, int codBanco) {

		double novoCredito = 0;
		Clientes cliente = new Clientes(codCliente, 0, 0, null, null, null, 0, null, null, 0);
		cliente = DbGetDadosClientes.main(codCliente, codBanco);

		if (cliente.getTipo() == 1) {
			novoCredito = (cliente.getRendaMensal() * 0.40);
		} else {
			novoCredito = (cliente.getRendaMensal() * 0.65);
		}
		return novoCredito;
	}
	
}
