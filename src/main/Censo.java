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
	private Grafo _radioCensal;
	private ArrayList<Censista> _censistasDisponibles;
	private Map<Integer, Tupla<Double, Double>> _coodenadas;
	private boolean[] _manzanasCensadas;

	public Censo() {
		agregarCensistasJSON();
		agregarCoordenadasJSON();
		AgregarAristasJSON();
		_manzanasCensadas = new boolean[_radioCensal.tamano()];
	}
	
	public Censo(Integer manzanasACensar) {
		_radioCensal = new Grafo(manzanasACensar);
		_censistasDisponibles = new ArrayList<Censista>();
	}
	
	public ArrayList<Integer> manzanasACensar(){
		ArrayList<Integer> manzanas = new ArrayList<Integer>();
		for(int i = 0; i < _radioCensal.tamano(); i++){
			manzanas.add(i);
		}
		return manzanas;
	}
	

	public void manzanasContiguas(Integer m1, Integer m2) {
		_radioCensal.agregarArista(m1, m2);
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
		while (!todosVisitados()) {
			Integer censistaAleatorio = new Random().nextInt(_censistasDisponibles.size());
			Censista censistaElegido = _censistasDisponibles.get(censistaAleatorio);
			//System.out.println(censistaElegido.getNombre());
			_censistasAsignados.put(censistaElegido, recorridoParaCensista());
			_censistasDisponibles.remove(censistaElegido);
		}
		return _censistasAsignados;
	}

	private boolean todosVisitados() {
		boolean ret = true;
		for (boolean b : _manzanasCensadas) {
			ret = ret && b;
		}
		return ret;
	}

	private Integer getVerticeNoVisitado() {
		Integer vertice = -1;
		Integer i = 0;
		while (i < _manzanasCensadas.length) {
			if (_manzanasCensadas[i] == false) {
				vertice = i;
				i = _manzanasCensadas.length;
			} else {
				i++;
			}
		}
		return vertice;
	}

	private ArrayList<Integer> recorridoParaCensista() { // devuelve las manzanas que censa x censista
		Integer origen = getVerticeNoVisitado();
		_manzanasCensadas[origen] = true; // marco la manzana de origen
		ArrayList<Integer> recorrido = new ArrayList<Integer>();
		recorrido.add(origen);
		recorrido = crearRecorrido(recorrido, origen, _radioCensal, _manzanasCensadas);
		return recorrido;
	}
	
	private static ArrayList<Integer> crearRecorrido(ArrayList<Integer> recorrido, Integer origen, Grafo radioCensal,
			boolean[] manzanasCensadas) {
		if (recorrido.size() == 3) {
			return recorrido;
		}
		if (todosVisitados(manzanasCensadas, radioCensal.getVecinos(origen))) {
			if (todosVisitados(manzanasCensadas, radioCensal.getVecinos(recorrido.get(0)))) {
				return recorrido;
			} else {
				return crearRecorrido(recorrido, recorrido.get(0), radioCensal, manzanasCensadas);
			}
		} else {
			Integer manzanaActual = getManzanaVecinaNoCensada(manzanasCensadas, radioCensal.getVecinos(origen));
			recorrido.add(manzanaActual);
			manzanasCensadas[manzanaActual] = true;
			return crearRecorrido(recorrido, manzanaActual, radioCensal, manzanasCensadas);
		}
	}

	private static Integer getManzanaVecinaNoCensada(boolean[] manzanasCensadas, Set<Integer> vecinos) {
		Integer vertice = -1;
		for (Integer vecino : vecinos) {
			if (manzanasCensadas[vecino] == false) {
				vertice = vecino;
				break;
			}
		}
		return vertice;
	}

	private static boolean todosVisitados(boolean[] manzanasCensadas, Set<Integer> vertices) {
		boolean ret = true;
		for (Integer vertice : vertices) {
			ret = ret && manzanasCensadas[vertice];
		}
		return ret;
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

	public Set<Integer> get_SetCoodenadas() {
		return _coodenadas.keySet();
	}

	public Map<Integer, Tupla<Double, Double>> get_coodenadas() {
		return _coodenadas;
	}
	
	public Tupla<Double, Double> get_Coordenada(Integer c){
		return _coodenadas.get(c);
	}
	
	public ArrayList<Censista> get_censistasDisponibles() {
		return _censistasDisponibles;
	}
}
