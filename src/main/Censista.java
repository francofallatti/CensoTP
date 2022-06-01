package main;

public class Censista {
	private String _nombre;
	private String _imagen;//generadas con https://www.thispersondoesnotexist.com/
	//private int _manzanasVisitadas;//Cada censista puede visitar una, dos o tres manzanas completas
	
	public Censista(String nombre,String imagen) {
		_nombre=nombre;
		_imagen=imagen;
	}
	public String getNombre() {
		return _nombre;
	}
	public String get_imagen() {
		return _imagen;
	}
	
}
