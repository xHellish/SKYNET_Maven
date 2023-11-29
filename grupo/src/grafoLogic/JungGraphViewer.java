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
import modulo.GrafoManager;
import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class JungGraphViewer {
    
	//GrafoActual tipo JsonObject
	JsonNode grafoNodoActual;
	int ponderadoActual=0;
	
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
			//String ciudadID=nombreCiudad+","+String.valueOf(ciudad.get("Soldiers"))+","+String.valueOf(ciudad.get("Missiles"))+","+String.valueOf(ciudad.get("TechLevel"));
            
			grafoJung.addVertex(nombreCiudad);
			
			System.out.println(".......................................: ");
			System.out.println("Ciudad: " + nombreCiudad);
			System.out.println("");
			System.out.println("Soldados: " + String.valueOf(ciudad.get("Soldiers")));
			System.out.println("Misiles: " + String.valueOf(ciudad.get("Missiles")));
			System.out.println("Tech Level: " + String.valueOf(ciudad.get("TechLevel")));
			System.out.println("");
			//System.out.println("---------------------------------------: ");
			
            for (JsonNode conexion : ciudad.get("Edges")) {
                String ciudadDestino = conexion.get("ToVertex").asText();
                String idArista = nombreCiudad +"&"+ ciudadDestino + " " + conexion.get("Military").asText() + "," + conexion.get("Goods") + "," + conexion.get("Distance");
                //String ciudadDestinoID=ciudadDestino+","+String.valueOf(ciudad.get("Soldiers"))+","+String.valueOf(ciudad.get("Missiles"))+","+String.valueOf(ciudad.get("TechLevel"));
                //grafoJung.addEdge(String.valueOf(numeroID++), nombreCiudad, ciudadDestino);
                
                grafoJung.addEdge(idArista, nombreCiudad, ciudadDestino);
            }
        }
        return grafoJung;
    }

    public void setPonderado(int ponderado) {
    	ponderadoActual = ponderado;
    }
    
    public void showGraph(Graph<String, String> graph, String nombreVentana) {
        
        VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout<>(graph));
		vv.setPreferredSize(new Dimension(690, 630));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
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
	    
	    String numeroID = "";
	    
	    UndirectedSparseGraph<String, String> grafoJung = new UndirectedSparseGraph<>();
	    
	    for (Nodo ciudad : grafoCitys) {
	        grafoJung.addVertex(ciudad.getNombre());
	        
	        for (Arista arista : ciudad.getAristas()) {
				numeroID=String.valueOf(arista.getMilitancia())+","+String.valueOf(arista.getRecursos())+","+String.valueOf(arista.getDistancia());
	            // Asegurarse de que no se añade la misma arista dos veces
	            if (!grafoJung.containsEdge(String.valueOf(numeroID))) {
	                grafoJung.addEdge(numeroID, arista.getNombreInicio(), arista.getNombreLlegada());
	            }
	        }
	    }
	    
	    showGraph(grafoJung, "GRAFO MODIFICADO SKYNET");
	}
	
	public void showShortestPathGraph(Vector<Nodo> caminoMasCorto) {
	    UndirectedSparseGraph<String, String> grafoJung = new UndirectedSparseGraph<>();

	    // Asignar un ID único a cada arista
	    int numeroID = 0;

	    // Añadir solo los nodos y aristas que forman parte del camino más corto
	    for (int i = 0; i < caminoMasCorto.size(); i++) {
	        Nodo nodo = caminoMasCorto.get(i);
	        grafoJung.addVertex(nodo.getNombre());

	        if (i < caminoMasCorto.size() - 1) {
	            Nodo siguienteNodo = caminoMasCorto.get(i + 1);
	            grafoJung.addEdge(String.valueOf(numeroID++), nodo.getNombre(), siguienteNodo.getNombre());
	        }
	    }

	    // Visualizar el grafo con JUNG
	    showGraph(grafoJung, "Camino Más Corto desde " + caminoMasCorto.firstElement().getNombre() + " a " + caminoMasCorto.lastElement().getNombre()+"Valor ponderado: "+String.valueOf(ponderadoActual));
	}
}


