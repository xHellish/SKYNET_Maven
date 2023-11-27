package graphic;

import java.util.Vector;

import modulo.GrafoManager;
import modulo.Nodo;

public class EntreDosCiudadesController {
	
	EntreDosCiudadesView vista;
	GrafoManager grafoManager;
	
	public EntreDosCiudadesController(EntreDosCiudadesView vista, GrafoManager grafoManager) {
		this.vista = vista;
		this.grafoManager = grafoManager;
		
		initActionListeners();
	}
	
	// Listeners
	
	public void initActionListeners() {
		vista.getRutaMasCortaButton().addActionListener(e -> rutaMasCortaHandler());
	}
	
	// Métodos
	
	public Vector<Nodo> rutaMasCortaHandler(){
		
		Vector<Nodo> ciudadesRutaCorta = grafoManager.entreDosNodos(vista.getCiudadInicio(), vista.getCiudadLlegada());
		return ciudadesRutaCorta;
	}

}
