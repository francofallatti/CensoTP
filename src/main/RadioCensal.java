package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class RadioCensal {
	private Grafo _radioCensal;
	private Map<Integer, Tupla<Double, Double>> _coodenadas;
	private ArrayList<Integer> manzanas;
	private boolean[] _manzanasCensadas;
	
	public RadioCensal(Integer manzanasACensar) {
		_radioCensal = new Grafo(manzanasACensar);
		_manzanasCensadas = new boolean[cantManzanasACensar()];
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
	
	public ArrayList<Integer> manzanasACensar(){
		ArrayList<Integer> manzanas = new ArrayList<Integer>();
		for(int i = 0; i < _radioCensal.tamano(); i++){
			manzanas.add(i);
		}
		return manzanas;
	}
	
	public Integer manzanasSinCensar() {
		Integer ret = 0;
		for(boolean b : _manzanasCensadas) {
			if(b == false) {
				ret++;
			}
		}
		return ret;
	}
	
	public Integer cantManzanasACensar() {
		return manzanasACensar().size();
	}
	
	public Tupla<Double, Double> get_Coordenada(Integer vertice){
		return _coodenadas.get(vertice);
	}
}
