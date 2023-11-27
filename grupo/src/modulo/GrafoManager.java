package modulo;

import java.util.Vector;

import com.fasterxml.jackson.databind.JsonNode;

public class GrafoManager {
	
	// Ciudades / Grafo
	Vector<Nodo> grafoCiudades;
	JsonNode jsonObject;
	
	public GrafoManager(JsonNode jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	// Getters & setters
	public Vector<Nodo> getGrafo(){
		return grafoCiudades;
	}
	
	public void setGrafo(Vector<Nodo> grafoNuevo) {
		grafoCiudades = grafoNuevo;
	}

}
