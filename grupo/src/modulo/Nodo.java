package modulo;

import java.util.Vector;

public class Nodo {
	
	Vector<Arista> aristasColindantes;
	
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
}
