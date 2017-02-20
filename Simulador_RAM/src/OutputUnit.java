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
	File outputFile;

	public OutputUnit(){
		cinta = new ArrayList<Integer>();
		outputFile = null;
	}
	public OutputUnit(File output){
		cinta = new ArrayList<Integer>();
		outputFile = output;
	}
	
	public ArrayList<Integer> getCinta(){
		return cinta;
	}
	
	public void add(int data){
		cinta.add(data);
	}
	
	public void write(){
		try{
			FileWriter writer = new FileWriter(outputFile);
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