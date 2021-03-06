/**
 * 
 */
package projeto.banco.poo.core;

/**
 * Classe responsável por armazenar as operações realizadas a fim de exibir o extrato.
 * 
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 * @version 1.0
 */
public class Operacoes {

	private int codigo;
	private int tipoOperacao;
	private double valorOperacao;
	private int banco;
	private int agencia;
	private int conta;
	private int conta2;
	private double saldoConta;
	private double saldoConta2;
	private String data;

	/**Método construtor, responsável por inicializar os atributos de Operacoes.
	 * @param codigo int - código da conta
	 * @param tipoOperacao int - código referente ao tipo de operação
	 * @param valorOperacao double - valor da operação realizada
	 * @param banco int - banco referente a operação
	 * @param agencia int - agencia referente a operação
	 * @param conta int - conta referente a operação
	 * @param conta2 int - segunda conta referente a operação
	 * @param saldoConta double - saldo da conta após a operação
	 * @param saldoConta2 double - saldo da segunda conta após a operação
	 * @param data String - data da operação */
	public Operacoes(int codigo, int tipoOperacao, double valorOperacao, int banco, int agencia, int conta, int conta2,
			double saldoConta, double saldoConta2, String data) {

		this.codigo = codigo;
		this.tipoOperacao = tipoOperacao;
		this.valorOperacao = valorOperacao;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.conta2 = conta2;
		this.data = data;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(int tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public double getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(double valorOperacao) {
		this.valorOperacao = valorOperacao;
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

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public int getConta2() {
		return conta2;
	}

	public void setConta2(int conta2) {
		this.conta2 = conta2;
	}

	public double getSaldoConta() {
		return saldoConta;
	}

	public void setSaldoConta(double saldoConta) {
		this.saldoConta = saldoConta;
	}

	public double getSaldoConta2() {
		return saldoConta2;
	}

	public void setSaldoConta2(double saldoConta2) {
		this.saldoConta2 = saldoConta2;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
