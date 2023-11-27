package modulo;

public class Arista {
	
	String nombreLlegada;
	String nombreInicio;
	
	int militancia;
	int recursos;
	int distancia;
	
	public Arista(String nombreInicio, String nombreLlegada, int militancia, int recursos, int distancia) {
		this.militancia = militancia;
		this.recursos = recursos;
		this.distancia = distancia;
		this.nombreLlegada = nombreLlegada;
		this.nombreInicio = nombreInicio;
	}
	
	// Getters & setters
	
	public String getNombreInicio() {
		return nombreLlegada;
	}
	
	public String getNombreLlegada() {
		return nombreLlegada;
	}

	public int getMilitancia() {
		return militancia;
	}

	public int getRecursos() {
		return recursos;
	}

	public int getDistancia() {
		return distancia;
	}
	
	
	
	
}
