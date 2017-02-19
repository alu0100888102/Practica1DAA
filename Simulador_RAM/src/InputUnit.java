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
		}
	}
	public ArrayList<Integer> getCinta(){
		return cinta;
	}
	public int getIndex(){
		return index;
	}
	public int setIndex(int i){
		int temp = index;
		index = i;
		return temp;
	}
	
	public int getNext(){
		return cinta.get(index);
	}
}
