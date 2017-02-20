/**
 * Esta clase emulará la Unidad Arimetico-Lógica y de Control de una maquina RAM.
 * 
 * @author Alu0100888102
 * @version 1.0
 */
import java.io.*;

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
		memoriaInstrucciones.jumto(operando);
	}
	/**
	 * Salta a la etiqueta pasada como operando si R0 == 0
	 * @param operando
	 */
	public void jzero(String operando){
		if(memoriaDatos.getAcc() == 0)
			memoriaInstrucciones.jumto(operando);
	}
	/**
	 * Salta a la etiqueta pasada como operando si R0 > 0
	 * @param operando
	 */
	public void jgtz(String operando){
		if(memoriaDatos.getAcc() > 0)
			memoriaInstrucciones.jumto(operando);
	}
	/**
	 * termina la ejecucion del programa
	 */
	public void halt(){
		cintaSalida.write();
		System.exit(0);
	}
	
	/**
	 * El método run es el que se encarga de la ejecución del programa.
	 */
	public void run(){}
	public void runDebug(){}
	
	
	//args: 0= programa, 1= entrada, 2= salida, 3= debug
	public static void main(String args[]){
		File ficheroPrograma = new File(args[0]);
		File ficheroEntrada = new File(args[1]);
		File ficheroSalida = new File(args[2]);
		int debugMode = Integer.parseUnsignedInt(args[3]);
		
		Alu maquinaRam = new Alu(ficheroPrograma, ficheroEntrada, ficheroSalida);
		
		if(debugMode == 1)
			maquinaRam.runDebug();
		else
			maquinaRam.run();
	}

} 
