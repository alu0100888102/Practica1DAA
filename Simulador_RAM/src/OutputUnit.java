/**
 * Esta clase emulará la cinta de salida de una maquina RAM.
 * 
 * @author Alu0100888102
 * @version 1.0
 */

import java.util.ArrayList;
import java.io.*;

public class OutputUnit {
	ArrayList<Integer> cinta;

	public OutputUnit(){
		cinta = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getCinta(){
		return cinta;
	}
	
	public void add(int data){
		cinta.add(data);
	}
	
	public void write(File output){
		try{
			FileWriter writer = new FileWriter(output);
			for(int i =0; i < cinta.size(); i++){
				writer.write(cinta.get(i)+"\n");
			}
			writer.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error en el fichero: no se encuentra " + e);
		}
		catch(IOException e){
			System.out.println("Error en el fichero: error de entrada/salida " + e);
		}
	}
}