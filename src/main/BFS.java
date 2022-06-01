package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFS {

	private static List<Integer> L;
	private static boolean marcados[];
	private static List<List<Integer>> recorrido;

	public static boolean esConexo(Grafo g) {
		if (g == null) {
			throw new IllegalArgumentException("Grafo null");
		}
		if (g.tamano() == 0) {
			return true;
		}
		return alcanzables(g, 0).size() == g.tamano();

	}

	public static Set<Integer> alcanzables(Grafo g, int origen) {
		Set<Integer> ret = new HashSet<Integer>();

		// inicializar
		L = new ArrayList<Integer>();
		L.add(origen);
		marcados = new boolean[g.tamano()];

		while (L.size() > 0) {
			int i = L.get(0);// Seleccionar i∈ L y marcarlo;
			marcados[i] = true;
			ret.add(i);

			// Agregar a L todos los vecinos no marcados de i;
			agregarVecinosPendientes(g, i);

			L.remove(0);// L := L\{i};;
		}

		return ret;
	}

	private static void agregarVecinosPendientes(Grafo g, int i) {
		for (int vertice : g.getVecinos(i)) {
			if (marcados[vertice] == false && L.contains(vertice) == false) {
				L.add(vertice);
			}
		}
	}
}
