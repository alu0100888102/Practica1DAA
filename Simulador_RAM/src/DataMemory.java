/**
 * Esta clase emular� la memoria de datos de la m�quina ram. En principio ser� de solo 20 registros, siendo el registro 0 el acumuldor.
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
	 * De aqu� en adelante est�n los metodos que se corresponden con las instrucciones que reconoce la m�quina RAM
	 * y que trabajan con la memoria de datos.
	 */
	
	/**
	 * Ejecuta la operaci�n LOAD, cargando el valor en R0.
	 * @param data
	 */
	public void loadData(int data){
		this.setData(0, data);
	}
	/**
	 * Ejecuta la operaci�n STORE, cargando el contenido del acumulador en el registro indicado.
	 * @param register
	 */
	public void sotoreData(int register){
		if((register== 0))
			throw new IllegalArgumentException("ERROR DE REGISTRO: No se puede cargar al acumulador");
		if((register >= registros.size()))
			throw new IllegalArgumentException("ERROR DE REGISTRO: No existe el registro indicado");
		this.setData(register, this.getData(0));
	}
}
