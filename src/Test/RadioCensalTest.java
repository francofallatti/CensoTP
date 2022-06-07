package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.RadioCensal;

public class RadioCensalTest {
	
	private RadioCensal radioCensal;
	@Before
	public void SetUp() {
		radioCensal = new RadioCensal();
	}
	
	@Test
	public void CensarNoVisistadosTest() {
		radioCensal = new RadioCensal();
		assertTrue(radioCensal.todosVisitados());
	}
	

}
