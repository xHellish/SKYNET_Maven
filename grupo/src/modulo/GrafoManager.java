package modulo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Vector;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;

import grafoLogic.JungGraphViewer;

public class GrafoManager {
	
	// Ciudades / Grafo
	Vector<Nodo> grafoCiudades = new Vector<Nodo>();
	Vector<Nodo> temporal = new Vector<Nodo>();
	JsonNode jsonObject;
	
	public GrafoManager(JsonNode jsonObject) {
		
		for(JsonNode ciudad:jsonObject.get("Ciudades")){
			Nodo tmp= new Nodo(
			ciudad.get("Vertex").asText(),
			ciudad.get("Soldiers").asInt(),
			ciudad.get("Missiles").asInt(),
			ciudad.get("TechLevel").asInt());
			
			for(JsonNode edge:ciudad.get("Edges")) {
				Arista tmpArista= new Arista(
				ciudad.get("Vertex").asText(),
				edge.get("ToVertex").asText(),
				edge.get("Military").asInt(),
				edge.get("Goods").asInt(),
				edge.get("Distance").asInt());
				tmp.agregarArista(tmpArista);
			}
			
			this.grafoCiudades.add(tmp);
		}
	}
	
	// Getters & setters
	public Vector<Nodo> getGrafoCiudades(){
		return grafoCiudades;
	}
	
	public void setGrafoCiudades(Vector<Nodo> grafoNuevo) {
		grafoCiudades = grafoNuevo;
	}

	public Vector<Nodo> getGrafoTemporal(){
		return temporal;
	}
	
	public void setGrafoTemporal(Vector<Nodo> grafoNuevo) {
		temporal = grafoNuevo;
	}
	
	// Métodos Aux
	public Vector<Nodo> resetTmp(){
		Vector<Nodo> temporal2 = new Vector<Nodo>();
		
		for (Nodo nodoOriginal : grafoCiudades) {
            Nodo nodoCopia = new Nodo(nodoOriginal.getNombre(), nodoOriginal.getSoldados(), nodoOriginal.getMisiles(), nodoOriginal.getNivelTecnologico());

            for (Arista aristaOriginal : nodoOriginal.getAristas()) {
                Arista aristaCopia = new Arista(aristaOriginal.getNombreInicio(), aristaOriginal.getNombreLlegada(), aristaOriginal.getMilitancia(), aristaOriginal.getRecursos(), aristaOriginal.getDistancia());
                nodoCopia.agregarArista(aristaCopia);
            }
            temporal2.add(nodoCopia);
        }
		return temporal2;
	}
	
	public Vector<Nodo> copiarVector(Vector<Nodo> original) {
	    Vector<Nodo> copia = new Vector<>();

	    for (Nodo nodoOriginal : original) {
	        Nodo nodoCopia = new Nodo(nodoOriginal.getNombre(), nodoOriginal.getSoldados(), nodoOriginal.getMisiles(), nodoOriginal.getNivelTecnologico());

	        for (Arista aristaOriginal : nodoOriginal.getAristas()) {
	            Arista aristaCopia = new Arista(aristaOriginal.getNombreInicio(), aristaOriginal.getNombreLlegada(), aristaOriginal.getMilitancia(), aristaOriginal.getRecursos(), aristaOriginal.getDistancia());
	            nodoCopia.agregarArista(aristaCopia);
	        }
	        copia.add(nodoCopia);
	    }
	    return copia;
	}
	public Vector<Nodo> eliminarCamino(Vector<Vector<Nodo>> paths, int indice){
		temporal=resetTmp();
		Vector<Nodo>camino=paths.elementAt(indice);
		eliminarAristasDelCamino(camino);
		return temporal;		
	}
	public void eliminarAristasDelCamino(Vector<Nodo> camino) {
		for (int i = 0; i < camino.size() - 1; i++) {
			Nodo nodoActual = camino.get(i);
			Nodo nodoSiguiente = camino.get(i + 1);

			Arista aristaAEliminar = nodoActual.getAristas().stream()
					.filter(arista -> arista.getNombreLlegada().equals(nodoSiguiente.getNombre()))
					.findFirst()
					.orElse(null);

			if (aristaAEliminar != null) {
				nodoActual.eliminarArista(aristaAEliminar);
			}
		}

		for (int i = 0; i < temporal.size(); i++) {
			Nodo nodoTemporal = temporal.get(i);
			for (Nodo nodoCamino : camino) {
				if (nodoTemporal.getNombre().equals(nodoCamino.getNombre())) {
					nodoTemporal.setAristas(nodoCamino.getAristas());
					break;
				}
			}
		}
	}
	// ----------------- Cálculos ----------------- //
	
	// -> 1. Grafo disconexo.
	public Vector<Nodo> hacerGrafoDisconexo(){
		temporal = resetTmp();
		
		Nodo PrimerNodo=temporal.get(0);
		for(Arista Edge:PrimerNodo.aristasColindantes){
			for(int i=0; i<temporal.size();i++){
				if(temporal.get(i).nombre.equals(Edge.getNombreLlegada())){
					temporal.remove(i);
				}
			}
		}
		temporal.get(0).aristasColindantes.clear();

		return temporal;
	}
	
	// -> 2. Expansión mínima.
	public void grafoExpansionMinima() {
	    temporal = resetTmp();
	    
	    Set<String> nodosVisitados = new HashSet<>();
	    Vector<Nodo> nodosExpansionMinima = new Vector<>();

	    for (Nodo nodoActual : grafoCiudades) {
	        if (!nodosVisitados.contains(nodoActual.getNombre())) {
	            nodosVisitados.add(nodoActual.getNombre());
	            nodosExpansionMinima.add(nodoActual);

	            for (Arista arista : nodoActual.getAristas()) {
	                if (!nodosVisitados.contains(arista.getNombreLlegada())) {
	                    nodosVisitados.add(arista.getNombreLlegada());
	                    // Agrega un nuevo Nodo con el nombre de llegada de la arista
	                    nodosExpansionMinima.add(new Nodo(arista.getNombreLlegada(), 0, 0, 0));
	                    // Si quieres agregar la arista al árbol de expansión mínima también, puedes hacerlo aquí.
	                }
	            }
	        }
	    }
	    temporal = nodosExpansionMinima;
	    
	    
	}
	
	Vector<Nodo> copiaOriginal;
	
	public void eliminarTrasiegoBienes() {
	    copiaOriginal = copiarVector(temporal);
	    
	    temporal = resetTmp();

	    eliminarAristasDelCamino(copiaOriginal);

	    JungGraphViewer visorGrafo = new JungGraphViewer();
	    visorGrafo.showShortestPathGraph(temporal);
	}
	
	public boolean existeArista(Arista arista, Vector<Nodo> grafo) {
		for (Nodo nodo : grafo) {
			for (Arista aristaNodo : nodo.getAristas()) {
				if (arista.equals(aristaNodo)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// -> 5. Red de un solo recorrido
	
	public void redUnSoloRecorrido() {
		grafoExpansionMinima();
		
		Vector<Nodo> grafoCopia1 = copiarVector(grafoCiudades);
		Vector<Nodo> grafoCopia2 = copiarVector(temporal);
		
		if (grafoCopia1.equals(grafoCopia2)) {
			System.out.println("Se puede reventar TODO (es posible recorrer sin repetir)");
			
		} else {
			System.out.println("Se repiten aristas");
		}
	}
	
	// -> 3. Grafo dirigido
	public void grafoDirigido(){
		temporal=resetTmp();
		DirectedGraph.crearGrafoDirigido(temporal);
	}
	
	
	// -> 4.Camino al mas poderoso
	public Nodo masPoderoso(){
		temporal=resetTmp();
		Nodo poderoso=null;
		for(Nodo nodo: temporal){
			int suma=nodo.getMisiles() + nodo.getSoldados();
			if(poderoso==null){
				poderoso=nodo;
			}else if(suma>(poderoso.getMisiles() + poderoso.getSoldados())){
				poderoso=nodo;
			}
		}
		return poderoso;
	}

	public Vector<Vector<Nodo>> caminosAMasPoderoso(){
		temporal=resetTmp();
		Nodo poderoso=masPoderoso();
		Vector<Vector<Nodo>> caminos = new Vector<>();
		for(Nodo nodo:temporal){
			if(nodo!=poderoso){
				Vector<Nodo>actual=caminoMasCorto(nodo.getNombre(),poderoso.getNombre());
				caminos.add(new Vector<>(actual));
			}
		}
		return caminos;
	}
	public void eliminarCaminosAPoderoso(){
		Vector<Vector<Nodo>> caminos=caminosAMasPoderoso();
		for(Vector<Nodo>camino:caminos){
			eliminarAristasDelCamino(camino);
		}
	}

	//-> 6. Red de un solo recorrido
	public void nodoMasVisitado(){
		grafoExpansionMinima();
		int aristasTotales=0;
		Vector<Nodo> masVisitados=new Vector<Nodo>();

		for(Nodo ciudad:temporal){
			if(ciudad.aristasColindantes.size()>=aristasTotales){
				aristasTotales=ciudad.aristasColindantes.size();
				masVisitados.add(ciudad);
			}
		}
		
		for(Nodo nodo: masVisitados){
			for(Nodo ciudad:temporal){
				if(ciudad.equals(nodo)){
					temporal.remove(ciudad);
					break;
				}
			}
		}
	}
	
	// -> 7. Camino mas corto
	public int distanciaCamino(Vector<Nodo> camino){
		int cont=0;
		for(int i=0; i<camino.size() - 1; i++){ 
			Nodo nodoActual = camino.get(i);
			
				Nodo nodoSiguiente = camino.get(i + 1);
				Arista aristaS = nodoActual.getAristas().stream()
						.filter(arista -> arista.getNombreLlegada().equals(nodoSiguiente.getNombre()))
						.findFirst()
						.orElse(null);
				if (aristaS != null) {
					cont+=aristaS.getDistancia();
				}
			
		}
		return cont;		
	}
	
	public Vector<Nodo> caminoMasCorto(String inicio, String fin ){
		temporal=resetTmp();
		Vector<Nodo> caminoMasCorto=null;
		Vector<Vector<Nodo>> rutas = findAllPaths(inicio,fin);
		
		for(Vector<Nodo> ruta: rutas){
			if(caminoMasCorto==null){caminoMasCorto=ruta;}
			else if(distanciaCamino(caminoMasCorto)>distanciaCamino(ruta)){
				caminoMasCorto=ruta;
			}
		}
		
		return caminoMasCorto;
	}
	
	
	// -> 8. Camino mas poderoso
	public int fuerzaMilitar(Vector<Nodo> camino){
		int cont=0;
		for(int i=0; i<camino.size()-1; i++){ 
			Nodo nodoActual = camino.get(i);						
			Nodo nodoSiguiente = camino.get(i + 1);						
			Arista aristaS = nodoActual.getAristas().stream()
					.filter(arista -> arista.getNombreLlegada().equals(nodoSiguiente.getNombre()))
					.findFirst()
					.orElse(null);
			if (aristaS != null) {
				cont+=aristaS.getMilitancia();
			}
		}
		return cont;		
	}
	public Vector<Nodo> caminoMasPoderoso(String origen, String destino){
		temporal=resetTmp();
		Vector<Nodo>masFuerte=null;
		Vector<Vector<Nodo>> caminos=findAllPaths(origen,destino);
		for(Vector<Nodo> vector:caminos){
			if(masFuerte==null){
				masFuerte=vector;
			}else if(fuerzaMilitar(masFuerte)<fuerzaMilitar(vector)){
				masFuerte=vector;
			}
		}
		return masFuerte;
	} 
	
	// -> 9. Todos los caminos
	public Vector<Vector<Nodo>> findAllPaths(String inicio, String fin) {
		Vector<Vector<Nodo>> todosLosCaminos = new Vector<>();
		Set<String> visitados = new HashSet<>();
		Nodo nodoInicio = buscarNodo(inicio);

		if (nodoInicio != null) {
			Vector<Nodo> caminoActual = new Vector<>();
			dfs(nodoInicio, visitados, caminoActual, todosLosCaminos, fin);
		}

		return todosLosCaminos;
	}

    private void dfs(Nodo actual, Set<String> visitados, Vector<Nodo> caminoActual, Vector<Vector<Nodo>> todosLosCaminos, String destino) {
		if (visitados.contains(actual.getNombre())) {
			return; 
		}

		visitados.add(actual.getNombre()); 
		caminoActual.add(actual);

		if (actual.getNombre().equals(destino)) {
			todosLosCaminos.add(new Vector<>(caminoActual)); 
		} else {
			for (Arista arista : actual.getAristas()) {
				Nodo vecino = buscarNodo(arista.getNombreLlegada());
				if (vecino != null) {
					dfs(vecino, visitados, caminoActual, todosLosCaminos, destino);
				}
			}
		}

		visitados.remove(actual.getNombre()); 
		caminoActual.remove(caminoActual.size() - 1); 
	}
    private Nodo buscarNodo(String nombre) {
		temporal=resetTmp();
        for (Nodo nodo : temporal) {
            if (nodo.getNombre().equals(nombre)) {
                return nodo;
            }
        }
        return null;
    }
	public int valorPonderado(Vector<Nodo> camino){
		int cont=0;
		for(int i=0; i<camino.size()-1; i++){ 
			Nodo nodoActual = camino.get(i);
			Nodo nodoSiguiente = camino.get(i + 1);
			Arista aristaS = nodoActual.getAristas().stream()
					.filter(arista -> arista.getNombreLlegada().equals(nodoSiguiente.getNombre()))
					.findFirst()
					.orElse(null);
			if (aristaS != null) {
				cont+=aristaS.getMilitancia();
				cont+=aristaS.getRecursos();
				cont+=aristaS.getDistancia();
			}
		}
		return cont;
		
	}
	
	
	// -> 10. Recorrido más eficiente para destrucción
	public Nodo masTecnologico(){
		temporal=resetTmp();
		Nodo poderoso=null;
		for(Nodo nodo: temporal){
			int suma=nodo.getNivelTecnologico();
			if(poderoso==null){
				poderoso=nodo;
			}else if(suma> poderoso.getNivelTecnologico()){
				poderoso=nodo;
			}
		}
		
		return poderoso;
	}
	public Nodo menosTecnologico(){
		temporal=resetTmp();
		Nodo poderoso=null;
		for(Nodo nodo: temporal){
			int suma=nodo.getNivelTecnologico();
			if(poderoso==null){
				poderoso=nodo;
			}else if(suma < poderoso.getNivelTecnologico()){
				poderoso=nodo;
			}
		}
		return poderoso;
	}
	
	
	public Vector<Nodo> recorridoMasEficienteDestruccion(){
		temporal=resetTmp();
		Nodo origen=masTecnologico();
		Nodo destino=menosTecnologico();
		Vector<Nodo>masFuerte=null;
		Vector<Vector<Nodo>> caminos=findAllPaths(origen.getNombre(),destino.getNombre());
		for(Vector<Nodo> vector:caminos){
			if(masFuerte==null){
				masFuerte=vector;
			}else if(fuerzaMilitar(masFuerte)<fuerzaMilitar(vector)){
				masFuerte=vector;
			}
		}
		return masFuerte;
	} 
}