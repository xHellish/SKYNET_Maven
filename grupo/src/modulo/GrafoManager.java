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
	
	// ----------------- Cálculos ----------------- //
	//Dadas dos ciudades
	//seleccionadas, determine el
	//camino más corto por distancia
	//entre ambas. 

	// -> Grafo disconexo.
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
	
	// -> Expansión mínima.
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
	    copiaOriginal = copiarVector(grafoCiudades);

	    for (Nodo nodo : copiaOriginal) {
	    	
	    	Vector<Arista> aristasIteracion = nodo.getAristas();
	    	
	    	for (Arista aristaNodo : aristasIteracion) {
	    		if (existeArista(aristaNodo, temporal)) {
	    			nodo.eliminarArista(aristaNodo);
                }
	    	} 
	    }

	    JungGraphViewer visorGrafo = new JungGraphViewer();
	    visorGrafo.showGrafoVector(copiaOriginal);
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
	
	public void grafoDirigido(){
		temporal=resetTmp();
		DirectedGraph.crearGrafoDirigido(temporal);
	}
	// -> Entre dos Nodos 
	
	public Vector<Nodo> entreDosNodos(String Origen, String Destino){
		temporal = resetTmp();
		Vector<Nodo> VectorCaminoNodos=new Vector<Nodo>();
		Nodo CiudadInicio=null;
		Arista camino=null;
		for(Nodo ciudad:temporal){
			if(ciudad.nombre.equals(Origen)){
				CiudadInicio=ciudad;
				break;
			}
		}
		Nodo CiudadFinal=null;
		for(Nodo ciudad:temporal){
			if(ciudad.nombre.equals(Destino)){
				CiudadFinal=ciudad;
				break;
			}
		}
		int cont=0;
		for(Arista arista:CiudadInicio.aristasColindantes){
			if(arista.nombreLlegada.equals(Destino)){
				camino=arista;
				CiudadInicio.aristasColindantes.remove(cont);
				break;
			}
			cont++;
		}
		
		Vector<String> RutaMasCorta = sacarRutaMasCorta(CiudadInicio,CiudadFinal,new Vector<String>());
		
		
		
		
		for(Nodo ciudad:grafoCiudades){
			if(RutaMasCorta.contains(ciudad.nombre)){VectorCaminoNodos.add(ciudad);}
		}

		return VectorCaminoNodos;
		
	}
	
	public Vector<String> sacarRutaMasCorta(Nodo CiudadInicio, Nodo CiudadFinal, Vector<String> Ruta){
		if(CiudadInicio.nombre.equals(CiudadFinal.nombre)){return Ruta;}

		Ruta.add(CiudadInicio.getNombre());
		Arista RutaMasCorta=null;
		
		
		
		for(Arista camino:CiudadInicio.getAristas()){
			if(RutaMasCorta == null){
				RutaMasCorta=camino;
				
				System.out.println("Lleguée 1");
			}else if(Ruta.size()==1){
				
				System.out.println("Lleguée 2");
				
				if(camino.distancia<RutaMasCorta.distancia){RutaMasCorta=camino;}
				
				System.out.println("Lleguée 3");
			}else{
				
				System.out.println("Lleguée 4");
				
				for(int i=0;i<Ruta.size();i++){
					if(!Ruta.contains(camino.nombreLlegada)){
						if(camino.distancia<RutaMasCorta.distancia){RutaMasCorta=camino;}
					}
				}
			}
		}
		
		

		for(Nodo ciudad:this.grafoCiudades){
			if(ciudad.nombre.equals(RutaMasCorta.nombreLlegada)){
				CiudadInicio=ciudad;
				break;
			}
		}
		
		System.out.println("Ciclo");
		
		return sacarRutaMasCorta(CiudadInicio, CiudadFinal,Ruta);
	}
	
	// -> Camino al mas poderoso
	
	
}
