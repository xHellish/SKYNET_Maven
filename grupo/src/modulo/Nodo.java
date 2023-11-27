package modulo;

import java.util.Iterator;
import java.util.Vector;

public class Nodo {
	
	Vector<Arista> aristasColindantes = new Vector<Arista>();
	
	String nombre;
	int soldados;
	int misiles;
	int nivelTecnologico;
	
	public Nodo (String nombre, int soldados, int misiles, int nivelTecnologico) {
		this.nombre = nombre;
		this.soldados = soldados;
		this.misiles = misiles;
		this.nivelTecnologico = nivelTecnologico;
		
		aristasColindantes = new Vector<Arista>();
	}
	
	//Métodos
	public void agregarArista(Arista arista) {
		aristasColindantes.add(arista);
	}
	
	public void eliminarArista(Arista aristaBorrar) {
	    Iterator<Arista> iterator = aristasColindantes.iterator();

	    while (iterator.hasNext()) {
	        Arista arista = iterator.next();
	        if (aristaBorrar.equals(arista)) {
	            iterator.remove();
	        }
	    }
	}
	
	//Getters & setters
	public String getNombre() {
		return nombre;
	}
	
	public int getSoldados() {
		return soldados;
	}

	public int getMisiles() {
		return misiles;
	}

	public int getNivelTecnologico() {
		return nivelTecnologico;
	}

	public Vector<Arista> getAristas(){
		return aristasColindantes;
	}
}
