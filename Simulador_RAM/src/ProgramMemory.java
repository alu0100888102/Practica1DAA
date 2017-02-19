/**
 * Esta clase emulará la memoria de instrucciones de la máquina ram.
 * 
 * @author Alu0100888102
 * @version 1.0
 */

import java.util.ArrayList;
import java.io.*;

public class ProgramMemory{
	ArrayList<Instruccion> registros;
	int ip;
	
	public ProgramMemory(){
		registros = new ArrayList<Instruccion>();
		ip = 0;
	}
	/**
	 * Este constructor carga las instrucciones en la memoria de datos a partir del fichero.
	 * @param program
	 */
	public ProgramMemory(File program){
		ip =0;
		try{
			FileInputStream istream = new FileInputStream(program);
			 
			//Construct BufferedReader from InputStreamReader
			BufferedReader bufferreader = new BufferedReader(new InputStreamReader(istream));
		 
			String line = null;
			while ((line = bufferreader.readLine()) != null) {
				if (line.charAt(0) == '#')
					continue;
				Instruccion nuevainstruccion;
				//TODO: confirmar el formateo de las etiquetas.
				String[] division = line.split("\\s+");
				if(division.length == 3){
					//hay etiqueta, le qitamos los ":" del final.
					String etiqueta = division[0].substring(0, division[0].length()-1);
					String instruccion = division[1];
					String operando = division[2];
					nuevainstruccion = new Instruccion(instruccion.toLowerCase(), operando.toLowerCase(), etiqueta.toLowerCase());
				}
				else{
					String instruccion = division[0];
					String operando = division[1];
					nuevainstruccion = new Instruccion(instruccion.toLowerCase(), operando.toLowerCase());
				}
				registros.add(nuevainstruccion);
			}
			bufferreader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error en el fichero: no se encuentra " + e);
		}
		catch(IOException e){
			System.out.println("Error en el fichero: error de entrada/salida " + e);
		}
		catch(IllegalArgumentException e){
			System.out.println("Error en el fichero: error de entrada/salida " + e);
		}
	}
	
	public Instruccion getInstrucccion (int i){
		return registros.get(i);
	}
	public int setIp(int i){
		int temp = ip;
		ip = i;
		return temp;
	}
	public int getIp(){
		return ip;
	}
	public Instruccion nextInstruccion(){
		if(ip >= registros.size())
			return null;
		int temp = ip;
		ip++;
		return registros.get(temp);
	}
	/**
	 * Este metodo ejecuta las distintas operaciones de salto.
	 * @param etiqueta
	 * @return
	 */
	public boolean jumto(String etiqueta){
		for(int i =0; i < registros.size(); i++){
			if(registros.get(i).getEtiqueta() == etiqueta.toLowerCase()){
				ip = i;
				return true;
			}
		}
		return false;
	}
}
