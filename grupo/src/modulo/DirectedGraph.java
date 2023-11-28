package modulo;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.DirectionalEdgeArrowTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class DirectedGraph {

    public static void crearGrafoDirigido(Vector<Nodo> grafo) {
        DirectedSparseGraph<String, String> g = new DirectedSparseGraph<>();
        int cont = 0;

        // Agregar todos los nodos al grafo
        for (Nodo nodo : grafo) {
            g.addVertex(nodo.getNombre());
        }

        // Agregar las aristas y asegurar que cada nodo tenga al menos una entrada y salida
        for (Nodo nodo : grafo) {
            for (Arista arista : nodo.getAristas()) {
                String edgeId = "E" + cont++;
                if (!g.containsEdge(edgeId)) {
                    g.addEdge(edgeId, nodo.getNombre(), arista.getNombreLlegada());
                }
            }
        }

        // Configuración de la visualización
        FRLayout<String, String> layout = new FRLayout<>(g);
    layout.setSize(new Dimension(650, 650));
    VisualizationViewer<String, String> vv = new VisualizationViewer<>(layout);
    vv.setPreferredSize(new Dimension(700, 700));

    vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
    vv.getRenderContext().setEdgeArrowTransformer(new DirectionalEdgeArrowTransformer<>(17, 12, 5)); // Agregar esta línea

    DefaultModalGraphMouse<String, String> gm = new DefaultModalGraphMouse<>();
    vv.setGraphMouse(gm);

    JFrame frame = new JFrame("Grafo Dirigido");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().add(vv);
    frame.pack();
    frame.setVisible(true);
    }

}

/*
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.DirectionalEdgeArrowTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.algorithms.layout.FRLayout;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.util.*;

public class DirectedGraph {

    public static void crearGrafoDirigido(Vector<Nodo> grafo) {
        DirectedSparseGraph<String, String> g = new DirectedSparseGraph<>();
        int cont=0;
        for(Nodo nodo:grafo){
            g.addVertex(nodo.getNombre());
            for(Arista arista:nodo.aristasColindantes){
                if(g.findEdge(arista.getNombreLlegada(), nodo.getNombre())==null){
                    g.addEdge(String.valueOf(cont), nodo.getNombre(), arista.getNombreLlegada());
                    cont++;
                }
            }
        }

        FRLayout<String, String> layout = new FRLayout<>(g);
        layout.setSize(new Dimension(650, 650));
        VisualizationViewer<String, String> vv = new VisualizationViewer<>(layout);
        vv.setPreferredSize(new Dimension(700, 700));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        vv.getRenderContext().setEdgeArrowTransformer(new DirectionalEdgeArrowTransformer<>(17, 12, 5));

        DefaultModalGraphMouse<String, String> gm = new DefaultModalGraphMouse<>();
        vv.setGraphMouse(gm);

        JFrame frame = new JFrame("Grafo Dirigido");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
}
*/