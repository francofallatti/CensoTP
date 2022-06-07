package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;

public class Censo {
	private RadioCensal _radioCensal;
	private ArrayList<Censista> _censistasDisponibles;

	public Censo() {
		leerArchivoCensistasJSON();
		_radioCensal = new RadioCensal();
	}

	public Censo(Integer manzanasACensar) {
		verifException(manzanasACensar);
		_radioCensal = new RadioCensal(manzanasACensar);
		_censistasDisponibles = new ArrayList<Censista>();
	}

	

	public void agregarCensista(String nombre) {
		if (nombre == null || nombre == "") {
			throw new IllegalArgumentException("El censista necesita un nombre");
		}
		Censista c = new Censista(nombre);
		_censistasDisponibles.add(c);
	}

	public Map<Censista, ArrayList<Integer>> censar() {
		Map<Censista, ArrayList<Integer>> _censistasAsignados = new HashMap<Censista, ArrayList<Integer>>();
		while (!_radioCensal.todosVisitados() && _censistasDisponibles.size() != 0) {
			Integer censistaAleatorio = new Random().nextInt(_censistasDisponibles.size());
			Censista censistaElegido = _censistasDisponibles.get(censistaAleatorio);
			_censistasAsignados.put(censistaElegido, _radioCensal.recorridoParaCensista());
			_censistasDisponibles.remove(censistaElegido);
		}
		return _censistasAsignados;
	}

	private void leerArchivoCensistasJSON() {
		_censistasDisponibles = new ArrayList<Censista>();
		for (Object jsonObject : LeerArchivo.leerArchivoJSON("Censistas.json")) {
			agregarCensistas((JSONObject) jsonObject);
		}
	}

	private void agregarCensistas(JSONObject jsonObject) {
		String nombre = (String) jsonObject.get("nombre");
		String imagen = (String) jsonObject.get("imagen");
		Censista censista = new Censista(nombre, "/FotosCensistas/" + imagen);
		_censistasDisponibles.add(censista);
	}
	
	public Censista getCensista(int index) {
		return _censistasDisponibles.get(index);
	}
	
	public RadioCensal get_radioCensal() {
		return _radioCensal;
	}

	public Tupla<Double, Double> getCoordenadas(Integer vertice) {
		return _radioCensal.get_Coordenada(vertice);
	}

	public ArrayList<Integer> manzanasACensar() {
		return _radioCensal.manzanasACensar();
	}
	
	public Integer cantManzanasSinCensar() {
		return _radioCensal.manzanasSinCensar();
	}

	public ArrayList<Censista> get_censistasDisponibles() {
		return _censistasDisponibles;
	}
	
	public Integer cantCensistasLibres() {
		return _censistasDisponibles.size();
	}
	
	private void verifException(Integer manzanasACensar) {
		if(manzanasACensar==null) {
			throw new IllegalArgumentException("Se espera un Integer != null");
		}
		
	}
}
