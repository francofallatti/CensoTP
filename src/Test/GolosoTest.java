package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.Censo;

public class GolosoTest {

	private Censo censo;
	private Censo censo2;
	@Before
	public void SetUp() {
		censo = new Censo();
		censo2 = new Censo(55);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Constructor2Test() {
		new Censo(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void AgregarCensistasNullTest() {
		censo.agregarCensista(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void AgregarCensistasSinNombreTest() {
		censo.agregarCensista("");
	}

	@Test
	public void agregarCensistasTest() {
		censo.agregarCensista("aaron");
		assertEquals("aaron", censo.getCensista(0).getNombre());
	}

	@Test
	public void censistasDisponiblesTest0() {
		censo = new Censo();
		assertTrue(censo.get_censistasDisponibles().size() > 0);
	}

	@Test
	public void censistasDisponiblesTest1() {
		assertTrue(censo2.get_censistasDisponibles().size() == 0);
	}

	@Test
	public void censistasDisponiblesTest2() {
		censo2.agregarCensista("Claudio");
		assertTrue(censo2.get_censistasDisponibles().size() == 1);
	}

	@Test
	public void censistasDisponiblesTest3() {
		Censo censo4 = new Censo();
		censo.censar();
		assertTrue(censo.get_censistasDisponibles().size() < censo4.get_censistasDisponibles().size());
	}

	@Test
	public void cantManzanasACensarTest() {
		censo.censar();
		assertTrue(censo.manzanasACensar().size() > 0);
	}

	@Test
	public void cantManzanasSinCensarTest() {
		censo.censar();
		assertTrue(censo.cantManzanasSinCensar() == 0);
	}

}
