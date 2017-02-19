/**
 * Esta clase emulará la memoria de datos de la máquina ram. En principio será de solo 20 registros, siendo el registro 0 el acumuldor.
 * 
 * @author Alu0100888102
 * @version 1.0
 */
import java.util.ArrayList;

public class DataMemory {
	ArrayList<Integer> registros;
	
	public DataMemory (){
		registros = new ArrayList<Integer>(20);
	}
	
	public int getData(int i){
		return registros.get(i);
	}
	public int setData(int index, int data){
		int temp = registros.get(index);
		registros.set(index, data);
		return temp;
	}
	
	/**
	 * De aquí en adelante están los metodos que se corresponden con las instrucciones que reconoce la máquina RAM
	 * y que trabajan con la memoria de datos.
	 */
	
	/**
	 * Ejecuta la operación LOAD, cargando el valor en R0.
	 * @param data
	 */
	public void loadData(int data){
		this.setData(0, data);
	}
	public void loadData(String data){
		//si el operando empieza por = entonces es un valor inmediato
		if(data.startsWith("=")){
			this.loadData(Integer.parseInt(data.substring(1)));				
			return;
		}
		//si empieza por * es direccionamiento indirecto
		if(data.startsWith("*")){
			int register = this.getData(Integer.parseInt(data.substring(1)));
			this.loadData(this.getData(register));
			return;
		}
		// si no cumple lo anterior se asume que es direccionamiento directo
		this.loadData(this.getData(Integer.parseInt(data)));
	}
	/**
	 * Ejecuta la operación STORE, cargando el contenido del acumulador en el registro indicado.
	 * @param register
	 */
	public void storeData(int register){
		if((register== 0))
			throw new IllegalArgumentException("ERROR DE REGISTRO: No se puede cargar al acumulador");
		if((register >= registros.size()))
			throw new IllegalArgumentException("ERROR DE REGISTRO: No existe el registro indicado");
		this.setData(register, this.getData(0));
	}
	public void storeData(String register){
		//si el operando empieza por = entonces es un valor inmediato
		if(register.startsWith("=")){
			this.storeData(Integer.parseInt(register.substring(1)));
			return;
		}
		//si empieza por * es direccionamiento indirecto
		if(register.startsWith("*")){
			int indirectRegister = this.getData(Integer.parseInt(register.substring(1)));
			this.storeData(this.getData(indirectRegister));
			return;
		}
		// si no cumple lo anterior se asume que es direccionamiento directo
		this.storeData(this.getData(Integer.parseInt(register)));		
	}
	/**
	 * devuelve el valor del acumulador (R0)
	 * @return
	 */
	public int getAcc(){
		return this.getData(0);
	}
	/**
	 * Devuelve el dato en función de un operando
	 * @param index
	 * @return
	 */
	public int getData(String index){
		//si el operando empieza por = entonces es un valor inmediato
		if(index.startsWith("=")){
			return this.getData(Integer.parseInt(index.substring(1)));
		}
		//si empieza por * es direccionamiento indirecto
		if(index.startsWith("*")){
			int register = this.getData(Integer.parseInt(index.substring(1)));
			return this.getData(this.getData(register));
		}
		// si no cumple lo anterior se asume que es direccionamiento directo
		return this.getData(this.getData(Integer.parseInt(index)));				
	}
	/**
	 * introduce un dato utilizando un operando como indice
	 * @param index
	 * @param data
	 * @return
	 */
	public int setData(String index, int data){
		//si el operando empieza por = entonces es un valor inmediato
		if(index.startsWith("=")){
			return this.setData(Integer.parseInt(index.substring(1)), data);
		}
		//si empieza por * es direccionamiento indirecto
		if(index.startsWith("*")){
			int register = this.getData(Integer.parseInt(index.substring(1)));
			return this.setData(this.getData(register), data);
		}
		// si no cumple lo anterior se asume que es direccionamiento directo
		return this.setData(this.getData(Integer.parseInt(index)), data);
	}
}
