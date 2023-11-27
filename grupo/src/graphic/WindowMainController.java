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
        vista.getVerGrafoDisconexoButton().addActionListener(e -> verGrafoDisconexoHandler());
    }

    // Handlers
    private void subirGrafoHandler() {
    	modelo.getGrafoRuta();
    } 
    
    private void verActualGrafoHandler() {
    	modelo.grafoActualModificado();
    }
    
    private void verGrafoExpansionMinima() {
    	modelo.grafoExpansionMinima();
    }
    
    private void verGrafoDisconexoHandler() {
    	modelo.graficarGrafoDisconexo();
    	
    }
}
