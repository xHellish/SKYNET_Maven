package skynet;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;



public class GraficoDeGrafo {

	public static void main(String[] args) {
		
		Graph<String, String> graph = new SparseMultigraph<>();
		graph.addVertex("Hola 1");
		graph.addVertex("Hola 2");
		graph.addEdge("hola 1 -> 2", "Hola 1", "Hola 2");
		
		VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout<>(graph));
		vv.setPreferredSize(new Dimension(600, 600));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.setGraphMouse(new DefaultModalGraphMouse<>());
		
		JFrame frame = new JFrame("Grafo pedorro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
		
	}  
}

