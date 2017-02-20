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
	public String setEtiqueta(String e){
		String temp = etiqueta;
		etiqueta = e;
		return temp;
	}
	public String getInstruccion(){
		return instruccion;
	}
	public String setInstruccion(String i){
		String temp = instruccion;
		instruccion = i;
		return temp;
	}
	public String getOperando(){
		return operando;
	}
	public String setOperando(String o){
		String temp = operando;
		operando = o;
		return temp;
	}
	
	private boolean test(){
		if(instruccion == "load"){

		}
		if(instruccion == "store"){
			if(!(operando.matches("^(=\\d+|*\\d+|\\d+)$")))
				return true;
		}
		if(instruccion == "add"){
			if(!(operando.matches("^(=\\d+|*\\d+|\\d+)$")))
				return true;
		}
		if(instruccion == "sub"){
			if(!(operando.matches("^(=\\d+|*\\d+|\\d+)$")))
				return true;
		}
		if(instruccion == "mul"){
			if(!(operando.matches("^(=\\d+|*\\d+|\\d+)$")))
				return true;
		}
		if(instruccion == "div"){
			if(!(operando.matches("^(=\\d+|*\\d+|\\d+)$")))
				return true;
		}
		if(instruccion == "read"){
			if(!(operando.matches("^(=\\d+|*\\d+|\\d+)$")))
				return true;
		}
		if(instruccion == "write")
			if(!(operando.matches("^(=\\d+|*\\d+|\\d+)$")))
				return true;
		if(instruccion == "jump")
			if(operando.matches("^(\\D\\w+)$"))
					return true;
		if(instruccion == "jzero")
			if(operando.matches("^(\\D\\w+)$"))
				return true;
		if(instruccion == "jgtz")
			if(operando.matches("^(\\D\\w+)$"))
				return true;
		if(instruccion == "halt")
			return true;
		return false;
	}
}
