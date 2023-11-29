package graphic;

import java.util.Vector;

import javax.swing.JOptionPane;

import data.VectorToJson;
import modulo.Nodo;

public class WindowMainController {
	
    private WindowMainView vista;
    private WindowMainModel modelo;

    public WindowMainController(WindowMainView vista, WindowMainModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
        initActionListeners();
    }

    private void initActionListeners() {
        // Listeners buttons.
        vista.getSubirGrafoButton().addActionListener(e -> subirGrafoHandler());
        vista.getVerActualButton().addActionListener(e -> verActualGrafoHandler());
        vista.getHacerGrafoDisconexoButton().addActionListener(e -> hacerGrafoDisconexoHandler());
        vista.getSaveButton().addActionListener(e -> guardarGrafoModificadoHandler());
        vista.getHacerArbolExpansionMinimaButton().addActionListener(e -> hacerGrafoExpansionMinima());
        vista.getEliminarArbolExpansionButton().addActionListener(e -> eliminarAlbolExpansionMinimaHandler());
        vista.getEntreDosCiudadesButton().addActionListener(e -> entreDosCiudadesHandler());
        vista.getVerGrafoDirigidoButton().addActionListener(e -> verGrafoDirigidoHandler());
        vista.getRedDeUnSoloRecorridoButton().addActionListener(e -> redDeUnSoloRecorrido());
        vista.getNodoMasVisitadoButton().addActionListener(e -> nodoMasVisitadoHandler());
        vista.getCaminosHaciaMasPoderosoButton().addActionListener(e -> caminosHaciaMasPoderosoHandler());
        vista.getMostrarGrafoDisconexoButton().addActionListener(e -> verGrafoDisconexoHandler());
    }

    // Handlers
    private void subirGrafoHandler() {
    	modelo.getGrafoRuta();
    	
    } 
    
    public void verGrafoDisconexoHandler() {
    	modelo.verGrafoDisconexo();
    	//modelo.grafoManager.eliminarCaminosAPoderoso();
    	
    }
    
    private void verActualGrafoHandler() {
    	modelo.grafoActualModificado();
    }
    
    private void hacerGrafoExpansionMinima() {
    	modelo.hacerGrafoExpansionMinima();
    }
    
    private void hacerGrafoDisconexoHandler() {
    	modelo.hacerGrafoDisconexo();
    }
    
    private void eliminarAlbolExpansionMinimaHandler() {
    	modelo.eliminarArbolExpansionMinima();
    }
    
    private void guardarGrafoModificadoHandler() {
        try{
    	Vector<Nodo> grafo = modelo.grafoManager.getGrafoTemporal();
    	String rutaActual = modelo.pathGrafo;
    	
    	int response = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
    	
    	if (response == JOptionPane.YES_OPTION) {
    		VectorToJson vectorToJson = new VectorToJson(grafo, rutaActual);
            System.out.println("SE HA MODIFICADO EL GRAFO ORIGINAL");
        }
        }catch(Exception e){
            System.out.println("No se puede hacer el arbol de expansion minima");
        }
    	
    }
    
    private void verGrafoDirigidoHandler() {
        try{
    	    modelo.grafoManager.grafoDirigido();
        }catch (Exception e){
            System.out.println("No se puede hacer dirigido");
        }
    }
    
    public void redDeUnSoloRecorrido() {
        try{
    	    modelo.grafoManager.redUnSoloRecorrido();
        }catch (Exception e){
            System.out.println("No hay un solo recorrido");
        }
    }
    
    private void entreDosCiudadesHandler() {
        try{
            EntreDosCiudadesView ventanaEntreDosCiudades = new EntreDosCiudadesView();
            EntreDosCiudadesController entreDosCiudadesController = new EntreDosCiudadesController(ventanaEntreDosCiudades, modelo.grafoManager);
            
            ventanaEntreDosCiudades.agregarNombresComboBoxes(modelo.grafoManager.getGrafoCiudades());
            
            ventanaEntreDosCiudades.setVisible(true);
        }catch (Exception e){
            System.out.println("No se puede mostrar el camino.");
        }
    }
    
    private void nodoMasVisitadoHandler() {
        try{
    	    modelo.nodosMasVisitados();
        }catch (Exception e){
            System.out.println("No se encuentra el nodo mas visitado.");
        }
    }
    
    private void caminosHaciaMasPoderosoHandler() {
        
    	modelo.caminosMasPoderosos();
        
    }
}
