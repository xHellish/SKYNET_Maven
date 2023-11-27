package graphic;

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
    	
    }
    
    private void verGrafoDirigidoHandler() {
    	modelo.grafoManager.grafoDirigido();
    }
    
    private void entreDosCiudadesHandler() {
    	EntreDosCiudadesView ventanaEntreDosCiudades = new EntreDosCiudadesView();
    	EntreDosCiudadesController entreDosCiudadesController = new EntreDosCiudadesController(ventanaEntreDosCiudades, modelo.grafoManager);
    	ventanaEntreDosCiudades.agregarNombresComboBoxes(modelo.grafoManager.getGrafoCiudades());
    	ventanaEntreDosCiudades.setVisible(true);
    }
    
    
}
