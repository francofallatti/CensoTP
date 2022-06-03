package main;

public class Censista {
	private String _nombre;
	private String _imagen;// generadas con https://www.thispersondoesnotexist.com/

	public Censista(String nombre, String imagen) {
		_nombre = nombre;
		_imagen = imagen;
	}

	public Censista(String nombre) {
		_nombre = nombre;
	}

	public String getNombre() {
		return _nombre;
	}

	public String get_imagen() {
		return _imagen;
	}

}
