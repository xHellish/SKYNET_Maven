package data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Parser de un json de acuerdo a su ruta
public class JsonGrafoParser {

    private String path;

    public JsonGrafoParser() {
    	
    }

    public JsonNode parseJsonFromFile(String filePath) {
        try {
            String jsonContent = readFile(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(jsonContent);
        } catch (IOException e) {
            System.err.println("Error al leer o parsear el archivo JSON: " + e.getMessage());
            return null;
        }
    }

    private String readFile(String filePath) throws IOException {
        if (filePath != null && !filePath.isEmpty()) {
            Path _path = Paths.get(filePath);
            return new String(Files.readAllBytes(_path));
        } else {
            throw new IllegalArgumentException("La ruta del archivo no ha sido especificada.");
        }
    }

    public void setPath(String newPath) {
        path = newPath;
    }
}
