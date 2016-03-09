/**
 * 
 */
package projeto.banco.poo.core;

/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 */
public class Operacoes {
	
	private int codigo;
	private int tipoOperacao;
	private int banco;
	private int agencia;
	private int cliente;
	private int cliente2;
	private String data;
	
	
	/**
	 * 
	 */
	public Operacoes(int codigo, int tipoOperacao, int banco, int agencia, int cliente, int cliente2, String data) {

		this.codigo = codigo;
		this.tipoOperacao = tipoOperacao;
		this.banco = banco;
		this.agencia = agencia;
		this.cliente = cliente;
		this.cliente2 = cliente2;
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


	public int getCliente2() {
		return cliente2;
	}


	public void setCliente2(int cliente2) {
		this.cliente2 = cliente2;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
	
	
	
	

}
