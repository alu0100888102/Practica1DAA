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
		if(!this.test())
			throw new IllegalArgumentException("ERROR DE INSTRUCCION: No se reconoce la instrucción");
		
	}
	public Instruccion(String inst, String op){
		instruccion = inst;
		operando = op;
		etiqueta = null;
		if(!this.test())
			throw new IllegalArgumentException("ERROR DE INSTRUCCION: No se reconoce la instrucción");
	}
	public Instruccion(String inst, String op, String et){
		instruccion = inst;
		operando = op;
		etiqueta = et;
		if(!this.test())
			throw new IllegalArgumentException("ERROR DE INSTRUCCION: No se reconoce la instrucción");
	}
	
	public String getEtiqueta(){
		return etiqueta;
	}
	public void setEtiqueta(String e){
		etiqueta = e;
	}
	public String getInstruccion(){
		return instruccion;
	}
	public void setInstruccion(String i){
		instruccion = i;
	}
	public String getOperando(){
		return operando;
	}
	public void setOperando(String o){
		operando = o;
	}
	
	private boolean test(){
		if(instruccion.matches("load"))
			if(!(operando.matches("^(= \\d + | * \\d + | \\d + )$")))
				return true;
		if(instruccion.matches("store"))
			if(!(operando.matches("^( * \\d + | \\d + )$")))
				return true;
		if(instruccion.matches("add"))
			if(!(operando.matches("^(= \\d + | * \\d + | \\d + )$")))
				return true;
		if(instruccion.matches("sub"))
			if(!(operando.matches("^(= \\d + | * \\d + | \\d + )$")))
				return true;
		if(instruccion.matches("mul"))
			if(!(operando.matches("^(= \\d + | * \\d + | \\d + )$")))
				return true;
		if(instruccion.matches("div"))
			if(!(operando.matches("^(= \\d + | * \\d + | \\d + )$")))
				return true;
		if(instruccion.matches("read"))
			if(!(operando.matches("^( * \\d + | \\d + )$")))
				return true;
		if(instruccion.matches("write"))
			if(!(operando.matches("^(= \\d + | * \\d + | \\d + )$")))
				return true;
		if(instruccion.matches("jump"))
			if(!(operando.matches("^(\\D . *)$")))
				return true;
		if(instruccion.matches("jzero"))
			if(!(operando.matches("^(\\D . *)$")))
				return true;
		if(instruccion.matches("jgtz"))
			if(!(operando.matches("^(\\D . *)$")))
				return true;
		if(instruccion.matches("halt"))
			return true;
		return false;
	}
	
	public String toString(){
		String salida = "";
		if(etiqueta != null)
			salida = salida + etiqueta+": ";
		else
			salida = salida + "          ";
		salida = salida + instruccion +" ";
		if(operando != null)
			salida = salida+ operando;
		salida = salida + "\n";
		return salida;
	}
}
