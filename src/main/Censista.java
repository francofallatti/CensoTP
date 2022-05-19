package main;

public class Censista {
	private String _nombre;
	private String _imagen;//generadas con https://www.thispersondoesnotexist.com/
	private int _manzanasVisitadas;//Cada censista puede visitar una, dos o tres manzanas completas
	private boolean _finalizoRecorrido;
	
	public Censista(String nombre,String imagen) {
		_nombre=nombre;
		_imagen=imagen;
		_manzanasVisitadas=0;
		_finalizoRecorrido=false;
		
	}
	public int getManzanasVisitadas() {
		return _manzanasVisitadas;
	}
	public void setManzanasVisitadas(int manzanasVisitadas) {
		this._manzanasVisitadas += manzanasVisitadas;
	}
	public String getNombre() {
		return _nombre;
	}
	public boolean getFinRecorrido() {
		return _finalizoRecorrido;
	}
	public String get_imagen() {
		return _imagen;
	}
	
}
