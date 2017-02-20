/**
 * Esta clase emulará la Unidad Arimetico-Lógica y de Control de una maquina RAM.
 * 
 * @author Alu0100888102
 * @version 1.0
 */
import java.io.*;
import static java.lang.System.*;

public class Alu {
	DataMemory memoriaDatos;
	ProgramMemory memoriaInstrucciones;
	InputUnit cintaEntrada;
	OutputUnit cintaSalida;
	
	public Alu(){
		memoriaDatos = new DataMemory();
		memoriaInstrucciones = new ProgramMemory();
		cintaEntrada = new InputUnit();
		cintaSalida = new OutputUnit();
	}
	public Alu(File programa, File entrada, File salida){
		memoriaDatos = new DataMemory();
		memoriaInstrucciones = new ProgramMemory(programa);
		cintaEntrada = new InputUnit(entrada);
		cintaSalida = new OutputUnit(salida);
	}
	
	public DataMemory getMemoriaDatos(){
		return memoriaDatos;
	}
	public ProgramMemory getMemoriaInstrucciones(){
		return memoriaInstrucciones;
	}
	public InputUnit getCintaEntrada(){
		return cintaEntrada;
	}
	public OutputUnit getCintaSalida(){
		return cintaSalida;
	}
	public void setMemoriaDatos(DataMemory memDat){
		memoriaDatos = memDat;
	}
	public void setMemoriaInstrucciones(ProgramMemory memProg){
		memoriaInstrucciones = memProg;
	}
	public void setCintaEntrada(InputUnit cE){
		cintaEntrada = cE;
	}
	public void setCintaSalida(OutputUnit cS){
		cintaSalida = cS;
	}
	
	/**
	 * Instrucciones de la máquina RAM.
	 */
	/**
	 * La instruccion load carga el valor en R0.
	 * @param operando
	 */
	public void load(String operando){
		memoriaDatos.loadData(operando);
	}
	/**
	 * La instrucción store carga el valor del R0 en el registro indicado
	 * @param operando
	 */
	public void store(String operando){
		memoriaDatos.storeData(operando);
	}
	/**
	 * El operando se suma a R0 y se guarda en R0.
	 * @param operando
	 */
	public void add(String operando){
		//si el operando empieza por = entonces es un valor inmediato
		if(operando.startsWith("=")){
			int op = Integer.parseInt(operando.substring(1));
			memoriaDatos.loadData(memoriaDatos.getAcc() + op);
			return;
		}
		//en otro caso, es un registro
		int op = memoriaDatos.getData(operando);
		memoriaDatos.loadData(memoriaDatos.getAcc() + op);
		return;
	}
	/**
	 * El operando se resta a R0 y se guarda en R0.
	 * @param operando
	 */
	public void sub(String operando){
		//si el operando empieza por = entonces es un valor inmediato
		if(operando.startsWith("=")){
			int op = Integer.parseInt(operando.substring(1));
			memoriaDatos.loadData(op - memoriaDatos.getAcc());
			return;
		}
		//en otro caso, es un registro
		int op = memoriaDatos.getData(operando);
		memoriaDatos.loadData(op - memoriaDatos.getAcc());
		return;
	}
	/**
	 * El operando se multiplica a R0 y se guarda en R0.
	 * @param operando
	 */
	public void mul(String operando){
		//si el operando empieza por = entonces es un valor inmediato
		if(operando.startsWith("=")){
			int op = Integer.parseInt(operando.substring(1));
			memoriaDatos.loadData(op * memoriaDatos.getAcc());
			return;
		}
		//en otro caso, es un registro
		int op = memoriaDatos.getData(operando);
		memoriaDatos.loadData(op * memoriaDatos.getAcc());
		return;
	}
	/**
	 * El operando se divide a R0 y se guarda en R0.
	 * @param operando
	 */
	public void div(String operando){
		//si el operando empieza por = entonces es un valor inmediato
		if(operando.startsWith("=")){
			int op = Integer.parseInt(operando.substring(1));
			memoriaDatos.loadData(op / memoriaDatos.getAcc());
			return;
		}
		//en otro caso, es un registro
		int op = memoriaDatos.getData(operando);
		memoriaDatos.loadData(op / memoriaDatos.getAcc());
		return;
	}
	/**
	 * Lee el siguiente valor de la cinta de entrada y la almacena en la memoria segun el operando
	 * @param operando
	 */
	public void read(String operando){
		//se añade "=" al principio para que la clase entienda que es directamente al directorio si no tiene *
		if(!operando.startsWith("*"))
			operando = "="+operando;
		memoriaDatos.setData(operando, cintaEntrada.getNext());
	}
	/**
	 * introduce el operando en la linea de salida
	 * @param operando
	 */
	public void write(String operando){
		//si el operando empieza por = entonces es un valor inmediato
		if(operando.startsWith("=")){
			cintaSalida.add(Integer.parseInt(operando.substring(1)));
			return;
		}
		//si empieza por * es direccionamiento indirecto
		if(operando.startsWith("*")){
			int register = memoriaDatos.getData(Integer.parseInt(operando.substring(1)));
			cintaSalida.add(memoriaDatos.getData(register));
			return;
		}
		// si no cumple lo anterior se asume que es direccionamiento directo
		cintaSalida.add(memoriaDatos.getData(Integer.parseInt(operando)));
	}
	/**
	 * Salta a la etiqueta pasada como operando
	 * @param operando
	 */
	public void jump(String operando){
		memoriaInstrucciones.jumpto(operando);
	}
	/**
	 * Salta a la etiqueta pasada como operando si R0 == 0
	 * @param operando
	 */
	public void jzero(String operando){
		if(memoriaDatos.getAcc() == 0)
			memoriaInstrucciones.jumpto(operando);
	}
	/**
	 * Salta a la etiqueta pasada como operando si R0 > 0
	 * @param operando
	 */
	public void jgtz(String operando){
		if(memoriaDatos.getAcc() > 0)
			memoriaInstrucciones.jumpto(operando);
	}
	/**
	 * termina la ejecucion del programa
	 */
	public void halt(){
		cintaSalida.write();
		exit(0);
	}
	
	/**
	 * El método run es el que se encarga de la ejecución del programa.
	 */
	public void run(int debug){
		int actionCounter= 0;
		Instruccion siguienteInstruccion = memoriaInstrucciones.nextInstruccion();
		while(siguienteInstruccion != null){
			actionCounter++;
			if (siguienteInstruccion.getInstruccion().matches("load"))
				this.load(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("store"))
				this.store(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("add"))
				this.add(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("sub"))
				this.sub(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("mul"))
				this.mul(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("div"))
				this.div(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("read"))
				this.read(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("write"))
				this.write(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("jump"))
				this.jump(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("jzero"))
				this.jzero(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("jgtz"))
				this.jgtz(siguienteInstruccion.getOperando());
			if (siguienteInstruccion.getInstruccion().matches("halt")){
				out.println("El numero de acciones ejecutadas es: " +  actionCounter);
				this.halt();
			}
			
			if(debug == 1){
				out.println("Instruccion numero " +  actionCounter);
				out.println("IP = "+(memoriaInstrucciones.getIp()-1));
				out.println("Memoria de programas:\n"+ memoriaInstrucciones);
				out.println("Memoria de datos:\n"+ memoriaDatos);
				out.println("Cinta de entrada:\n" + cintaEntrada);
				out.println("Cinta de salida:\n" +  cintaSalida);
			}
			
			siguienteInstruccion = memoriaInstrucciones.nextInstruccion();
		}
		out.println("El numero de acciones ejecutadas es: " +  actionCounter);
		exit(0);
	}
	
	//args: 0= programa, 1= entrada, 2= salida, 3= debug
	public static void main(String args[]){
		File ficheroPrograma = new File("programas/test1.ram");
		File ficheroEntrada = new File("programas/input_tape.in");
		File ficheroSalida = new File("programas/output_tape.out");
		int debugMode = Integer.parseUnsignedInt("1");
		
		Alu maquinaRam = new Alu(ficheroPrograma, ficheroEntrada, ficheroSalida);
		maquinaRam.run(debugMode);
	}

} 
