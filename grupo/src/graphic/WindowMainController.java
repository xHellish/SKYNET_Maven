package graphic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.fasterxml.jackson.databind.JsonNode;


import data.FileChooser;
import data.JsonGrafoParser;
import grafoLogic.JungGraphViewer;
import modulo.GrafoManager;


public class WindowMainController {
	
	GrafoManager grafoManager;
	
    private WindowMainView vista;
    private WindowMainModel modelo;

    public WindowMainController(WindowMainView vista, WindowMainModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        initActionListeners();
    }

    private void initActionListeners() {
        // Botones listener
        vista.getSubirGrafoButton().addActionListener(e -> subirGrafoHandler());

        // Canvas listener
        vista.getCanvasGrafo().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickCanvasGrafoHandler(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    // Handlers

    private void subirGrafoHandler() {
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
					JungGraphViewer ventanaGrafo = new JungGraphViewer(); //ventanaGrafo
					ventanaGrafo.setGrafoNodo(grafoNodo);
					ventanaGrafo.showGrafoJson();
					
					//ventanaGrafo.showGrafoVector(ciudades);
                    
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

    // Canvas
    private void clickCanvasGrafoHandler(int x, int y) {
        System.out.println("Clic en el Canvas en las coordenadas (" + x + ", " + y + ")");
        // Puedes agregar lógica adicional si es necesario
    }

    
}
