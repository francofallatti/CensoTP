package Test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import main.Censo;

public class HeuristicaTest {
	
	private Censo censo;
	
	@Before
	public void SetUp() {
		censo = new Censo();
	}
	
	@Test
	public void AgregarCensistasTest() {
		censo.agregarCensista("aaron");
		assertEquals("aaron",censo.get_censistasDisponibles().get(0).getNombre());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void AgregarCensistasNullTest() {
		censo.agregarCensista(null);
	}
	
	@Test
	public void CensarTest() {
		assertEquals(7, censo.censar().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void manzanasContiguasExceptionTest() {
		censo.manzanasContiguas(2, 2);
	}
	
	@Test
	public void CoodenasasLenghtTest() {
		assertEquals(lectura().size(), censo.get_SetCoodenadas().size());
	}
	
	public JSONArray lectura() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader resder = new FileReader("Coordenadas.json")){
			Object obj = jsonParser.parse(resder);
			JSONArray CoordenadasList = (JSONArray) obj;
			System.out.println("Json a mostrar");
			System.out.println(CoordenadasList);
			return CoordenadasList;
			/*
			for(Object jsonObject : CoordenadasList ) {
				motrarInfo((JSONObject) jsonObject);
			}*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
