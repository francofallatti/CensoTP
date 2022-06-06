package Test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import main.BFS;
import main.Grafo;

public class BFSTest {
	
	@Test (expected = IllegalArgumentException.class)
	public void NullTest() {
		BFS.esConexo(null);
	}
	
	@Test
	public void grafoVacioTest() {
		Grafo g = new Grafo(0);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void grafoNoConexoTest() {
		Grafo g = inicializarGrafo();
		assertFalse(BFS.esConexo(g));
	}
	
	@Test
	public void grafoConexoTest() {
		Grafo g = inicializarGrafo();
		g.agregarArista(3, 4);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesTest() {
		Grafo g = inicializarGrafo();
		Set<Integer> alcanzables = BFS.alcanzables(g,0);
		Assert.iguales(new int[] {0,1,2,3}, alcanzables);
	}
	
	private Grafo inicializarGrafo() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(2, 3);
		
		return g;
	}
}
