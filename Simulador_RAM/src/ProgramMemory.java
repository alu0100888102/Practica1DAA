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
		int nlinea=0;
		ip =0;
		registros = new ArrayList<Instruccion>();
		try{
			FileInputStream istream = new FileInputStream(program);
			 
			//Construct BufferedReader from InputStreamReader
			BufferedReader bufferreader = new BufferedReader(new InputStreamReader(istream));
		 
			String line = null;
			while ((line = bufferreader.readLine()) != null) {
				if(line.isEmpty())
					continue;
				if (line.charAt(0) == '#')
					continue;
				Instruccion nuevainstruccion;
				String[] division = line.split("\\s+");
				if(division.length == 2){
					if (!division[0].isEmpty() && !division[1].isEmpty()){
						String etiqueta = division[0].substring(0, division[0].length()-1);
						String instruccion = division[1];
						nuevainstruccion = new Instruccion(instruccion.toLowerCase(), null, etiqueta.toLowerCase());
					}
					else{
						String instruccion = division[1];
						nuevainstruccion = new Instruccion(instruccion.toLowerCase());
					}
					registros.add(nuevainstruccion);
				}
				if(division.length == 3){
					if(!division[0].isEmpty() && !division[1].isEmpty() && !division[2].isEmpty()){
						//hay etiqueta, le qitamos los ":" del final.
						String etiqueta = division[0].substring(0, division[0].length()-1);
						String instruccion = division[1];
						String operando = division[2];
						nuevainstruccion = new Instruccion(instruccion.toLowerCase(), operando.toLowerCase(), etiqueta.toLowerCase());
					}
					else if(division[0].isEmpty() && !division[1].isEmpty() && !division[2].isEmpty()){
						String instruccion = division[1];
						String operando = division[2];
						nuevainstruccion = new Instruccion(instruccion.toLowerCase(), operando.toLowerCase());
					}
					else{
						nuevainstruccion = new Instruccion(division[1].toLowerCase());
					}
					registros.add(nuevainstruccion);
				}
				nlinea++;
			}
			bufferreader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error en el fichero: no se encuentra " + e);
			System.exit(1);
		}
		catch(IOException e){
			System.out.println("Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
		catch(IllegalArgumentException e){
			System.out.println("Linea "+nlinea+" Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
	}
	
	public Instruccion getInstrucccion (int i){
		return registros.get(i);
	}
	public void setIp(int i){
		ip = i;
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
	public boolean jumpto(String etiqueta){
		for(int i =0; i < registros.size(); i++){
			if(registros.get(i).getEtiqueta() != null)
				if(registros.get(i).getEtiqueta().matches(etiqueta)){
					ip = i;
					return true;
				}
		}
		return false;
	}
	
	public String toString(){
		String salida = new String();
		for(int i=0; i< registros.size(); i++){
			if(ip-1 == i)
				salida = salida + "> ";
			salida = salida + registros.get(i);
		}
		salida = salida+"\n";
		return salida;
	}
}
