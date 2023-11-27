package grafoLogic;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.xml.transform.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;

public class CanvasGrafo extends JPanel {

    private Graph<String, String> grafo;
    private VisualizationViewer<String, String> vv;  // Agrega esta línea

    public void setGrafo(Graph<String, String> grafo) {
        this.grafo = grafo;
        repaint();
    }

    public void dibujarGrafo(Graph<String, String> grafo) {
        setGrafo(grafo);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (grafo != null) {
            if (vv == null) {
                // Configura el VisualizationViewer solo si no está configurado previamente
                CircleLayout<String, String> layout = new CircleLayout<>(grafo);
                vv = new VisualizationViewer<>(layout);

                // Configuración adicional del VisualizationViewer (etiquetas, etc.)
                vv.getRenderContext().setVertexLabelTransformer(Object::toString);
                vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

                setLayout(new BorderLayout());
                add(vv, BorderLayout.CENTER);

                // Agrega un ComponentAdapter para ajustar el tamaño del grafo al cambiar el tamaño de la ventana
                addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        // Ajusta el tamaño del grafo al tamaño de la ventana
                        vv.setSize(getSize());
                        vv.setPreferredSize(getSize());
                        vv.repaint();
                    }
                });
            } else {
                // Actualiza el grafo si ya existe el VisualizationViewer
                vv.getRenderContext().getPickedVertexState().clear();
                vv.getRenderContext().getPickedEdgeState().clear();
                vv.setGraphLayout(new CircleLayout<>(grafo));
                vv.repaint();
            }
        }
    }


}



