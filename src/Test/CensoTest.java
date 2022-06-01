package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.Censista;
import main.Censo;

public class CensoTest {

	private Censo censo;

	@Before
	public void SetUp() {
		censo = new Censo();
	}
	
	@Test
	public void AgregarCensistasTest() {	
		ArrayList<String> s= new ArrayList<String>();
		s.add("ana");
		s.add("/FotosCensistas/ana.jpg");
		s.add("juan");
		s.add("/FotosCensistas/juan.jpg");
		s.add("mirtha");
		s.add("/FotosCensistas/mirtha.jpg");
		s.add("pedro");
		s.add("/FotosCensistas/pedro.jpg");
		s.add("raul");
		s.add("/FotosCensistas/raul.jpg");
		
		//assertEquals(s,censo.getCensistasArrayListToString());
	}
	
	@Test
	public void getVisitadasTest() {
		//ArrayList<Censista> censistas = censo.getCensistasArrayList();
		//assertEquals(0,censistas.get(0).getManzanasVisitadas() );
	}

	@Test
	public void getVisitadas2Test() {
		//ArrayList<Censista> censistas = censo.getCensistasArrayList();
		//censistas.get(0).setManzanasVisitadas(2);
		//assertEquals(2,censistas.get(0).getManzanasVisitadas() );
	}
	@Test
	public void getVisitadasNotEqualsTest() {
		//ArrayList<Censista> censistas = censo.getCensistasArrayList();
		//censistas.get(0).setManzanasVisitadas(2);
		//assertNotEquals(4,censistas.get(0).getManzanasVisitadas() );
	}
	
	@Test
	public void getFinRecorridoTest() {
		//ArrayList<Censista> censistas = censo.getCensistasArrayList();
		//assertEquals(false,censistas.get(0).getFinRecorrido());
	}

}
