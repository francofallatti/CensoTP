package main;

import java.util.HashSet;
import java.util.Set;

public class Grafo {
	/* grafo, cuyos vertices representan las manzanas y cuyas aristas unen
manzanas contiguas en el mapa*/
	
	//matriz de adyacencia
		private boolean[][] A;

		public Grafo(int vertices) {
			A = new boolean[vertices][vertices];
		}

		public void agregarArista(int i, int j) {
			verifVertice(i);
			verifVertice(j);
			verifDistintos(i, j);

			A[i][j] = true;
			A[j][i] = true;
		}

		public boolean existeArista(int i, int j) {
			verifVertice(i);
			verifVertice(j);
			verifDistintos(i, j);

			return A[i][j];
		}

		public int tamano() {
			return A.length;
		}

		public Set<Integer> getVecinos(int i) {
			verifVertice(i);

			Set<Integer> ret = new HashSet<Integer>();
			for (int j = 0; j < this.tamano(); ++j)
				if (i != j) {
					if (this.existeArista(i, j))
						ret.add(j);
				}

			return ret;
		}

		private void verifVertice(int i) {
			if (i < 0)
				throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

			if (i >= A.length)
				throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
		}

		// Verifica que i y j sean distintos
		private void verifDistintos(int i, int j) {
			if (i == j)
				throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
		}
}
