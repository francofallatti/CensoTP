package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Censo {
	private RadioCensal _radioCensal;
	private ArrayList<Censista> _censistasDisponibles;

	public Censo() {
		agregarCensistasJSON();
		agregarCoordenadasJSON();
		AgregarAristasJSON();
		
	}
	
	public Censo(Integer manzanasACensar) {
		_radioCensal = new RadioCensal(manzanasACensar);
		_censistasDisponibles = new ArrayList<Censista>();
	}
	
	public void agregarCensista(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException("El censista necesita un nombre");
		}
		Censista c = new Censista(nombre);
		_censistasDisponibles.add(c);
	}

	public Map<Censista, ArrayList<Integer>> censar() {
		return asigarCensistas();
	}

	private Map<Censista, ArrayList<Integer>> asigarCensistas() {
		Map<Censista, ArrayList<Integer>> _censistasAsignados = new HashMap<Censista, ArrayList<Integer>>();
		while (!_radioCensal.todosVisitados() && _censistasDisponibles.size() != 0) {
			Integer censistaAleatorio = new Random().nextInt(_censistasDisponibles.size());
			Censista censistaElegido = _censistasDisponibles.get(censistaAleatorio);
			_censistasAsignados.put(censistaElegido, _radioCensal.recorridoParaCensista());
			_censistasDisponibles.remove(censistaElegido);
		}
		return _censistasAsignados;
	}
	



	private void agregarCensistasJSON() {
		_censistasDisponibles = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("Censistas.json")) {
			Object obj = jsonParser.parse(reader);
			JSONArray CoordenadasList = (JSONArray) obj;
			for (Object jsonObject : CoordenadasList) {
				agregarCensistas((JSONObject) jsonObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void agregarCensistas(JSONObject jsonObject) {
		String nombre = (String) jsonObject.get("nombre");
		String imagen = (String) jsonObject.get("imagen");
		Censista censista = new Censista(nombre, "/FotosCensistas/" + imagen);
		_censistasDisponibles.add(censista);
	}

	private void agregarCoordenadasJSON() {
		_coodenadas = new HashMap<Integer, Tupla<Double, Double>>();
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("Coordenadas.json")) {
			Object obj = jsonParser.parse(reader);
			JSONArray CoordenadasList = (JSONArray) obj;
			_radioCensal = new Grafo(CoordenadasList.size());
			for (Object jsonObject : CoordenadasList) {
				agregarCoordenadas((JSONObject) jsonObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void agregarCoordenadas(JSONObject jsonObject) {
		JSONObject info = (JSONObject) jsonObject.get("Vertice");
		Long numero = (Long) info.get("numero");
		double latitud = (Double) info.get("Latitud");
		double longitud = (Double) info.get("Logitud");
		Tupla<Double, Double> coord = new Tupla<Double, Double>(latitud, longitud);
		_coodenadas.put(numero.intValue(), coord);
	}

	private void AgregarAristasJSON() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("Grafo.json")) {
			Object obj = jsonParser.parse(reader);
			JSONArray grafoList = (JSONArray) obj;
			for (Object jsonObject : grafoList) {
				GrafoInfo((JSONObject) jsonObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void GrafoInfo(JSONObject jsonObject) {
		Long vert1 = (Long) jsonObject.get("vert1");
		Long vert2 = (Long) jsonObject.get("vert2");
		_radioCensal.agregarArista(vert1.intValue(), vert2.intValue());
	}
	
	public ArrayList<Censista> get_censistasDisponibles() {
		return _censistasDisponibles;
	}
	
	public Integer cantManzanasSinCensar() {
		return _radioCensal.manzanasSinCensar();
	}
	
	public RadioCensal get_radioCensal() {
		return _radioCensal;
	}

	public ArrayList<Integer> manzanasACensar(){
		return _radioCensal.manzanasACensar();
	}
	
	public Tupla<Double, Double> getCoordenadas(Integer vertice){
		return _radioCensal.get_Coordenada(vertice);
	}
}
