package graphic;

import java.util.Vector;

import com.fasterxml.jackson.databind.JsonNode;

import data.FileChooser;
import data.JsonGrafoParser;
import grafoLogic.JungGraphViewer;
import modulo.GrafoManager;
import modulo.Nodo;

public class WindowMainModel {
	
	// Manager de los cálculos.
	GrafoManager grafoManager;
	
	public WindowMainModel() {
		
	}
	
	// Setter del grafo original y su primer visualización.
	public void getGrafoRuta() {
		FileChooser chooser = new FileChooser();
        String pathGrafo = chooser.getPathGrafo();

        if (pathGrafo != null && !pathGrafo.isEmpty()) {
            JsonGrafoParser parser = new JsonGrafoParser();
            
            try {
                JsonNode grafoNodo = parser.parseJsonFromFile(pathGrafo);

                if (grafoNodo != null) {
                	
                	//Grafo manager
                	grafoManager = new GrafoManager(grafoNodo);
                	
					//Ventana grafo
					JungGraphViewer ventanaGrafo = new JungGraphViewer(); 
					ventanaGrafo.setGrafoNodo(grafoNodo);
					ventanaGrafo.showGrafoJson();
					
                } else {
                    System.out.println("Error: No se pudo parsear el archivo JSON correctamente.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado un archivo.");
        }
	}
	
	// Ver el grafo actual modificado.
	public void grafoActualModificado() {
		JungGraphViewer visorGrafo = new JungGraphViewer();
		Vector<Nodo> grafoCitys = grafoManager.getGrafoCiudades();
		visorGrafo.showGrafoVector(grafoCitys);
	}
	
	// Ver grafo directamente por vector.
	public void graficarGrafoPorVector(Vector<Nodo> grafoCitys) {
		JungGraphViewer visorGrafo = new JungGraphViewer();
		visorGrafo.showGrafoVector(grafoCitys);
	}
	
	public void grafoExpansionMinima() {
		grafoManager.grafoExpansionMinima();
		graficarGrafoPorVector(grafoManager.getGrafoCiudades());
	}
	
	public void graficarGrafoDisconexo() {
		graficarGrafoPorVector(grafoManager.hacerGrafoDisconexo());
	}
}
