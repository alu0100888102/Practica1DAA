/**
 * Esta clase sirve para representar las isntrucciones de la memoria de programas.
 * @author Angel
 * @version 1.0
 */

public class Instruccion {
	String etiqueta;
	String instruccion;
	String operando;
	
	public Instruccion(){}
	public Instruccion(String inst){
		instruccion = inst;
		operando = null;
		etiqueta = null;
	}
	public Instruccion(String inst, String op){
		instruccion = inst;
		operando = op;
		etiqueta = null;
	}
	public Instruccion(String inst, String op, String et){
		instruccion = inst;
		operando = op;
		etiqueta = et;
	}
	//TODO: hacer que lasoperaciones se comprueben
}
