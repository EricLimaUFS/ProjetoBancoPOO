/**
 * 
 */
package projeto.banco.poo.core;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author Bruno Rodrigues dos Santos, Eric Fonseca Lima
 * @since 6 de mar de 2016
 */
public class MetodosAuxiliares {

	public static String getDataAtual() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
/*	public static int getCodigoBanco() {
		
		String sql = "SELECT codigo FROM banco ORDER BY codigo DESC LIMIT 1";
		statement.executeQuery(sql);
		codigo = getInt;
		return codigo;
	}
		*/
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
