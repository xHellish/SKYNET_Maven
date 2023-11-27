package grafoLogic;

import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import modulo.Nodo;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import javax.swing.*;

import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.util.Vector;

public class JungGraphViewer {
	
	JsonNode grafoNodo;
	
    public JungGraphViewer() {
    	
    }
    
    public void setGrafoNodo(JsonNode grafoNodo) {
    	this.grafoNodo = grafoNodo;
    	
    }
    
    private Graph<String, String> convertirJsonAGrafo(JsonNode grafoNodo) {
    	
    	UndirectedSparseGraph<String, String> grafoJung = new UndirectedSparseGraph<>();
    	
        // Agregar vértices al grafo
        for (JsonNode ciudad : grafoNodo.get("ciudades")) {
            String nombreCiudad = ciudad.get("nombre").asText();
            grafoJung.addVertex(nombreCiudad);
        }

        // Agregar aristas al grafo
        for (JsonNode conexion : grafoNodo.get("conexiones")) {
            String ciudadOrigen = conexion.get("ciudadOrigen").asText();
            String ciudadDestino = conexion.get("ciudadDestino").asText();
            String camino = conexion.get("camino").asText();

            // Utiliza una combinación de ciudades y camino como nombre único para la arista
            String idArista = ciudadOrigen + "_" + ciudadDestino + "_" + camino;

            grafoJung.addEdge(idArista, ciudadOrigen, ciudadDestino);
        }
        
        return grafoJung;
    }

    public void showGraph(Graph<String, String> graph) {
        
        VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout<>(graph));
		vv.setPreferredSize(new Dimension(1024, 720));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.setGraphMouse(new DefaultModalGraphMouse<>());

        // Crear la ventana
        JFrame frame = new JFrame("JUNG Graph Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

	public void showGrafoJson() {
		showGraph(convertirJsonAGrafo(grafoNodo));
	}
	
	public void showGrafoVector(Vector<Nodo> grafoCitys) {
		
	}
}


