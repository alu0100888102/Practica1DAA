/**
 * Esta clase emulará la cinta de entrada de una maquina RAM.
 * 
 * @author Alu0100888102
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class InputUnit {
	ArrayList<Integer> cinta;
	int index;
	
	public InputUnit(){
		cinta = new ArrayList<Integer>();
		index =0;
	}
	/**
	 * Construye la cinta a partir de un fichero.
	 * @param entrada
	 */
	public InputUnit (File entrada){
		index =0;
		cinta = new ArrayList<Integer>();
		try{
			Scanner scanner = new Scanner(entrada);
			while(scanner.hasNextInt()){
				cinta.add(scanner.nextInt());
			}
			scanner.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error en el fichero: no se encuentra " + e);
			System.exit(1);
		}
	}
	public ArrayList<Integer> getCinta(){
		return cinta;
	}
	public int getIndex(){
		return index;
	}
	public void setIndex(int i){
		index = i;
	}
	
	public int getNext(){
		index++;
		return cinta.get(index-1);
	}
	
	public String toString(){
		String salida = new String();
		for(int i = 0; i< cinta.size(); i++){
			if (index-1 == i)
				salida = salida + ">";
			salida = salida+cinta.get(i);
			if (index-1 == i)
				salida = salida + "<";
			salida = salida + " ";
		}
		salida = salida + "\n";
		return salida;
	}
}
