package grafoLogic;

import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import modulo.Arista;
import modulo.Nodo;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import javax.swing.*;

import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class JungGraphViewer {
    
	//GrafoActual tipo JsonObject
	JsonNode grafoNodoActual;
	
    public JungGraphViewer() {
    	
    }
    
    public void setGrafoNodo(JsonNode grafoNodo) {
    	grafoNodoActual = grafoNodo;
    	
    }
    
    private Graph<String, String> convertirJsonAGrafo(JsonNode grafoNodo) {
        UndirectedSparseGraph<String, String> grafoJung = new UndirectedSparseGraph<>();
        
        int numeroID = 0;
        
        // Agregar vértices y aristas al grafo
        for (JsonNode ciudad : grafoNodo.get("Ciudades")) {
            String nombreCiudad = ciudad.get("Vertex").asText();
            grafoJung.addVertex(nombreCiudad);

            for (JsonNode conexion : ciudad.get("Edges")) {
                String ciudadDestino = conexion.get("ToVertex").asText();
                //String idArista = nombreCiudad + "_&_" + ciudadDestino;
                
                
                grafoJung.addEdge(String.valueOf(numeroID++), nombreCiudad, ciudadDestino);
            }
        }

        return grafoJung;
    }


    public void showGraph(Graph<String, String> graph, String nombreVentana) {
        
        VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout<>(graph));
		vv.setPreferredSize(new Dimension(690, 630));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		//vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.setGraphMouse(new DefaultModalGraphMouse<>());

        // Crear la ventana
        JFrame frame = new JFrame(nombreVentana);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

	public void showGrafoJson() {
		showGraph(convertirJsonAGrafo(grafoNodoActual), "GRAFO ORIGINAL");
	}
	
	public void showGrafoVector(Vector<Nodo> grafoCitys) {
		
		int numeroID = 0;
		UndirectedSparseGraph<String, String> grafoJung = new UndirectedSparseGraph<>();
		
		for (Nodo ciudad : grafoCitys) {
			grafoJung.addVertex(ciudad.getNombre());
			
			for (Arista arista : ciudad.getAristas()) {
				grafoJung.addEdge(String.valueOf(numeroID++), ciudad.getNombre(), arista.getNombreInicio());
			}
		}
		
		showGraph(grafoJung, "GRAFO MODIFICADO SKYNET");
	}
}


