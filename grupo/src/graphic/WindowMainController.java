package graphic;

import java.util.Vector;

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
    }

    // Handlers
    private void subirGrafoHandler() {
    	modelo.getGrafoRuta();
    	
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
    	Vector<Nodo> grafo = modelo.grafoManager.getGrafoTemporal();
    	String rutaActual = modelo.pathGrafo;
    	
    	VectorToJson vectorToJson = new VectorToJson(grafo, rutaActual);
    }
    
    private void verGrafoDirigidoHandler() {
    	modelo.grafoManager.grafoDirigido();
    }
    
    public void redDeUnSoloRecorrido() {
    	modelo.grafoManager.redUnSoloRecorrido();
    }
    
    private void entreDosCiudadesHandler() {
    	EntreDosCiudadesView ventanaEntreDosCiudades = new EntreDosCiudadesView();
    	EntreDosCiudadesController entreDosCiudadesController = new EntreDosCiudadesController(ventanaEntreDosCiudades, modelo.grafoManager);
    	
    	ventanaEntreDosCiudades.agregarNombresComboBoxes(modelo.grafoManager.getGrafoCiudades());
    	
    	ventanaEntreDosCiudades.setVisible(true);
    }
    
    private void nodoMasVisitadoHandler() {
    	modelo.nodosMasVisitados();
    }
    
    private void caminosHaciaMasPoderosoHandler() {
    	modelo.caminosMasPoderosos();
    }
}
