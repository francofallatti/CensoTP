package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RadioCensal {
	private Grafo _radioCensal;
	private Map<Integer, Tupla<Double, Double>> _coodenadas;
	private boolean[] _manzanasCensadas;

	public RadioCensal() {
		agregarCoordenadasJSON();
		agregarAristasJSON();
		_manzanasCensadas = new boolean[_radioCensal.tamano()];
	}

	public RadioCensal(Integer manzanasACensar) {
		_radioCensal = new Grafo(manzanasACensar);
		_manzanasCensadas = new boolean[manzanasACensar];
	}

	public void agregarCoordenadasJSON() {
		_coodenadas = new HashMap<Integer, Tupla<Double, Double>>();
		JSONArray coordenadasList = LeerArchivo.leerArchivoJSON("Coordenadas.json");
		_radioCensal = new Grafo(coordenadasList.size());
		for (Object jsonObject : coordenadasList) {
			agregarCoordenadas((JSONObject) jsonObject);
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

	public void agregarAristasJSON() {
		JSONArray grafoList = LeerArchivo.leerArchivoJSON("Grafo.json");
		for (Object jsonObject : grafoList) {
			GrafoInfo((JSONObject) jsonObject);
		}
	}

	private void GrafoInfo(JSONObject jsonObject) {
		Long vert1 = (Long) jsonObject.get("vert1");
		Long vert2 = (Long) jsonObject.get("vert2");
		_radioCensal.agregarArista(vert1.intValue(), vert2.intValue());
	}

	public ArrayList<Integer> recorridoParaCensista() { // devuelve las manzanas que censa x censista
		Integer origen = getVerticeNoVisitado();
		_manzanasCensadas[origen] = true; // marco la manzana de origen
		ArrayList<Integer> recorrido = new ArrayList<Integer>();
		recorrido.add(origen);
		recorrido = crearRecorrido(recorrido, origen, _radioCensal, _manzanasCensadas);
		return recorrido;
	}

	public static ArrayList<Integer> crearRecorrido(ArrayList<Integer> recorrido, Integer origen, Grafo radioCensal,
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

	public boolean todosVisitados() {
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

	private static boolean todosVisitados(boolean[] manzanasCensadas, Set<Integer> vertices) {
		boolean ret = true;
		for (Integer vertice : vertices) {
			ret = ret && manzanasCensadas[vertice];
		}
		return ret;
	}

	public void manzanasContiguas(Integer m1, Integer m2) {
		_radioCensal.agregarArista(m1, m2);
	}

	public ArrayList<Integer> manzanasACensar() {
		ArrayList<Integer> manzanas = new ArrayList<Integer>();
		for (int i = 0; i < _radioCensal.tamano(); i++) {
			manzanas.add(i);
		}
		return manzanas;
	}

	public Integer manzanasSinCensar() {
		Integer ret = 0;
		for (boolean b : _manzanasCensadas) {
			if (b == false) {
				ret++;
			}
		}
		return ret;
	}

	public Integer cantManzanasACensar() {
		return manzanasACensar().size();
	}

	public Tupla<Double, Double> get_Coordenada(Integer vertice) {
		return _coodenadas.get(vertice);
	}
}
