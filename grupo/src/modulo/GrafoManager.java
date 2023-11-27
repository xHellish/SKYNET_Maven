package modulo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;

import com.fasterxml.jackson.databind.JsonNode;

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

	
	// ----------------- Cálculos ----------------- //
	//Dadas dos ciudades
	//seleccionadas, determine el
	//camino más corto por distancia
	//entre ambas. 

	// -> Grafo disconexo.
	public Vector<Nodo> hacerGrafoDisconexo(){
		temporal = grafoCiudades;
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
	    // Mapeo de nombres de ciudades a identificadores numéricos
	    Map<String, Integer> idCiudades = new HashMap<>();
	    int idCounter = 0;

	    for (Nodo nodo : grafoCiudades) {
	        idCiudades.put(nodo.getNombre(), idCounter++);
	    }

	    // Inicializa una cola de prioridad para ordenar las aristas
	    PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>((a1, a2) -> Integer.compare(a1.distancia, a2.distancia));

	    // Agrega todas las aristas al conjunto de candidatos
	    for (Nodo nodo : grafoCiudades) {
	        colaPrioridad.addAll(nodo.getAristas());
	    }

	    // Conjunto de conjuntos disjuntos para verificar ciclos
	    UnionFind conjuntosDisjuntos = new UnionFind(grafoCiudades.size());

	    // Árbol de expansión mínima
	    Vector<Arista> arbolExpansionMinima = new Vector<>();

	    // Proceso de Kruskal
	    while (!colaPrioridad.isEmpty() && arbolExpansionMinima.size() < grafoCiudades.size() - 1) {
	        Arista aristaActual = colaPrioridad.poll();

	        // Obtiene los identificadores numéricos de los nodos
	        int idInicio = idCiudades.get(aristaActual.getNombreInicio());
	        int idLlegada = idCiudades.get(aristaActual.getNombreLlegada());

	        // Verifica si agregar la arista actual forma un ciclo
	        if (conjuntosDisjuntos.union(idInicio, idLlegada)) {
	            // Agrega la arista al árbol de expansión mínima
	            arbolExpansionMinima.add(aristaActual);
	        }
	    }

	    // Imprime o realiza cualquier acción con el árbol de expansión mínima
	    System.out.println("Árbol de expansión mínima:");
	    for (Arista arista : arbolExpansionMinima) {
	        System.out.println("De " + arista.getNombreInicio() + " a " + arista.getNombreLlegada() + ", Distancia: " + arista.distancia);
	    }
	}
	
	private static class UnionFind {
        int[] padre;

        public UnionFind(int n) {
            padre = new int[n];
            for (int i = 0; i < n; i++) {
                padre[i] = i;
            }
        }

        public int encontrar(int x) {
            if (padre[x] == x) {
                return x;
            }
            return padre[x] = encontrar(padre[x]);
        }

        public boolean union(int x, int y) {
            int raizX = encontrar(x);
            int raizY = encontrar(y);

            if (raizX != raizY) {
                padre[raizX] = raizY;
                return true;
            }
            return false;
        }
    }
	
	// -> Entre dos Nodos
	
	public Arista EntreDoNodos(String Origen, String Destino){
		temporal = grafoCiudades;								
		Nodo CiudadInicio=null;
		Arista camino=null;
		for(Nodo ciudad:temporal){
			if(ciudad.nombre==Origen){
				CiudadInicio=ciudad;
				break;
			}
		}
		Nodo CiudadFinal=null;
		for(Nodo ciudad:temporal){
			if(ciudad.nombre==Destino){
				CiudadFinal=ciudad;
				break;
			}
		}
		int cont=0;
		for(Arista arista:CiudadInicio.aristasColindantes){
			if(arista.nombreLlegada==Destino){
				camino=arista;
				CiudadInicio.aristasColindantes.remove(cont);
				break;
			}
			cont++;
		}
		return camino;
		
	}
	/*
	 public Vector<String> sacarRutaMasCorta(Nodo CiudadInicio, Nodo CiudadFinal, Vector<String> Ruta){
		if(CiudadInicio.nombre==CiudadFinal.nombre){return Ruta;}

		for(Arista ciudad:CiudadInicio){
			
		}
		
		}
	  */
}
