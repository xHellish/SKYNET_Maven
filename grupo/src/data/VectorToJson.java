package data;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;


import modulo.Arista;
import modulo.Nodo;

public class VectorToJson {

    Vector<Nodo> grafoGuardar;
    String rutaEditar;

    public VectorToJson(Vector<Nodo> grafoGuardar, String rutaEditar) {
        this.grafoGuardar = grafoGuardar;
        this.rutaEditar = rutaEditar;
        verificarYAgregarVertices();
        editarJson();
    }

    private void verificarYAgregarVertices() {
        Set<String> nombresVertices = new HashSet<>();
        for (Nodo nodo : grafoGuardar) {
            nombresVertices.add(nodo.getNombre());
        }

        // No es necesario añadir nuevos vértices si todos los destinos ya existen en el grafo
        // Eliminamos esta parte del código ya que puede causar duplicados innecesarios
    }

    public void editarJson() {
        JSONObject grafoJson = new JSONObject();
        JSONArray ciudadesArray = new JSONArray();

        for (Nodo nodo : grafoGuardar) {
            JSONObject ciudadJson = new JSONObject();
            ciudadJson.put("Vertex", nodo.getNombre());
            ciudadJson.put("Soldiers", nodo.getSoldados());
            ciudadJson.put("Missiles", nodo.getMisiles());
            ciudadJson.put("TechLevel", nodo.getNivelTecnologico());

            JSONArray aristasArray = new JSONArray();
            for (Arista arista : nodo.getAristas()) {
                JSONObject aristaJson = new JSONObject();
                aristaJson.put("ToVertex", arista.getNombreLlegada()); // Usamos getNombreLlegada en lugar de getDestino
                aristaJson.put("Military", arista.getMilitancia());
                aristaJson.put("Goods", arista.getRecursos());
                aristaJson.put("Distance", arista.getDistancia());
                aristasArray.put(aristaJson);
            }
            ciudadJson.put("Edges", aristasArray);
            ciudadesArray.put(ciudadJson);
        }

        grafoJson.put("Ciudades", ciudadesArray);

        try {
            Files.write(Paths.get(rutaEditar), grafoJson.toString(4).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
