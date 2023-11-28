package graphic;

import java.util.Vector;

import grafoLogic.JungGraphViewer;
import modulo.GrafoManager;
import modulo.Nodo;

public class EntreDosCiudadesController {
	
	EntreDosCiudadesView vista;
	GrafoManager grafoManager;
	JungGraphViewer visorGrfos;
	
	public EntreDosCiudadesController(EntreDosCiudadesView vista, GrafoManager grafoManager) {
		this.vista = vista;
		this.grafoManager = grafoManager;
		visorGrfos = new JungGraphViewer();
		initActionListeners();
	}
	
	// Listeners
	
	public void initActionListeners() {
		vista.getRutaMasCortaButton().addActionListener(e -> rutaMasCortaHandler());
		vista.getTodosLosCaminosButton().addActionListener(e -> verTodosLosCaminosHandler());
		vista.getCaminoMasPoderosoButton().addActionListener(e -> verCaminoMasPoderosoHandler());
	}
	
	// Métodos
	
	private void rutaMasCortaHandler(){
		Vector<Nodo> ciudadesRutaCorta = grafoManager.caminoMasCorto(vista.getCiudadInicio().toString(), vista.getCiudadLlegada().toString());
		JungGraphViewer visorGrafo = new JungGraphViewer();
		visorGrafo.showShortestPathGraph(ciudadesRutaCorta);
		
	}
	
	private void verTodosLosCaminosHandler() {
		Vector<Vector<Nodo>> listaVectores= grafoManager.findAllPaths(vista.getCiudadInicio().toString(), vista.getCiudadLlegada().toString());
		
		System.out.println("Estoy en la funcion " + vista.getCiudadInicio().toString() + " " + vista.getCiudadLlegada().toString());
		
		for (Vector<Nodo> grafo : listaVectores) {
			
			int ponderadoGrafo = grafoManager.valorPonderado(grafo);
			
			visorGrfos.showShortestPathGraph(grafo);
		}
	}
	
	private void verCaminoMasPoderosoHandler() {
		Vector<Nodo> caminoMasPoderoso = grafoManager.caminoMasPoderoso(vista.getCiudadInicio().toString(), vista.getCiudadLlegada().toString());
		visorGrfos.showShortestPathGraph(caminoMasPoderoso);
	}
}
