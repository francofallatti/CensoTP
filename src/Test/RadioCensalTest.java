package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.RadioCensal;

public class RadioCensalTest {
	
	private RadioCensal radioCensal;
	private RadioCensal radioCensal2;
	@Before
	public void SetUp() {
		radioCensal = new RadioCensal();
		radioCensal2 = new RadioCensal(33);
	}
	
	@Test
	public void manzanasSinCensarTest() {
		assertFalse(radioCensal.todosVisitados());
	}
	
	@Test
	public void manzanasSinCensarTest1() {
		assertTrue(radioCensal.cantManzanasACensar() == radioCensal.manzanasSinCensar());
	}
	
	@Test
	public void ingresoDeManzanasTest() {
		assertTrue(radioCensal2.cantManzanasACensar() == 33);
	}
	
}
