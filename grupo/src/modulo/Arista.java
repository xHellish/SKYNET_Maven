package modulo;

public class Arista {
	
	Nodo ciudadInicio;
	Nodo ciudadLlegada;
	
	int militancia;
	int recursos;
	int distancia;
	
	public Arista(int militancia, int recursos, int distancia) {
		this.militancia = militancia;
		this.recursos = recursos;
		this.distancia = distancia;
	}
	
	// Setters ciudad inicial y final.
	public void setCiudadInicial(Nodo ciudad) {
		ciudadInicio = ciudad;
	}
	
	public void setCiudadLlegada(Nodo ciudad) {
		ciudadLlegada = ciudad;
	}
}
