package data;

import java.util.Vector;

import modulo.Nodo;

public class VectorToJson {
    
    Vector<Nodo> grafoGuardar;
    String rutaEditar;
    
    public VectorToJson(Vector<Nodo> grafoGuardar, String rutaEditar) {
        this.grafoGuardar = grafoGuardar;
        this.rutaEditar = rutaEditar;
        //editarJson();
    }
    /*
    public void editarJson() {
        JSONObject grafoJson = new JSONObject();
        JSONArray nodosArray = new JSONArray();
        
        for (Nodo nodo : grafoGuardar) {
            JSONObject nodoJson = new JSONObject();
            nodoJson.put("nombre", nodo.getNombre());
            // Suponiendo que Nodo tiene un método getAristas() que devuelve un Vector<Arista>
            JSONArray aristasArray = new JSONArray();
            for (Arista arista : nodo.getAristas()) {
                JSONObject aristaJson = new JSONObject();
                aristaJson.put("destino", arista.getNombreLlegada());
                aristaJson.put("distancia", arista.getDistancia());
                aristasArray.put(aristaJson);
            }
            nodoJson.put("aristas", aristasArray);
            nodosArray.put(nodoJson);
        }
        
        grafoJson.put("nodos", nodosArray);
        
        // Escribir el objeto JSON en el archivo
        try {
            Files.write(Paths.get(rutaEditar), grafoJson.toString(4).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    */
}
