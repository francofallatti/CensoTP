package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.Censista;
import main.Censo;
import main.RadioCensal;

public class GolosoTest {
	
	private Censo censo;
	private RadioCensal radioCensal;
		
	@Test (expected = IllegalArgumentException.class)
	public void Constructor2Test() {
		Censo censo2 = new Censo(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void AgregarCensistasNullTest() {
		censo = new Censo();
		censo.agregarCensista(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void AgregarCensistasSinNombreTest() {
		censo = new Censo();
		censo.agregarCensista("");
	}
	
	@Test
	public void AgregarCensistasTest() {
		censo = new Censo();
		censo.agregarCensista("aaron");
		assertEquals("aaron",censo.getCensista(0).getNombre());
	}
	
	@Test
	public void CensarVisitadosTest() {
		censo = new Censo();
		radioCensal = new RadioCensal();
		censo.censar();
		assertFalse(radioCensal.todosVisitados());
	}
	
	
	@Test
	public void CensistasDisponiblesTest() {
		censo = new Censo();
		assertTrue(censo.get_censistasDisponibles().size()>0);
	}
	@Test
	public void CensistasDisponibles0Test() {
		censo = new Censo();
		censo.censar();
		assertTrue(censo.get_censistasDisponibles().size()==0);
	}
	@Test
	public void cantManzanasACensarTest() {
		censo = new Censo();
		radioCensal = new RadioCensal();
		censo.censar();
		assertTrue(radioCensal.cantManzanasACensar()>0);
	}
	
}
