package Test;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.junit.Test;

import main.LeerArchivo;

public class LeerArchivoTest {
	@Test
	public void lenghtTest() {
		JSONArray censistaList =LeerArchivo.leerArchivoJSON("Censistas.json");
		assertEquals(15, censistaList.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullException() {
		LeerArchivo.leerArchivoJSON(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void vacioTest() {
		LeerArchivo.leerArchivoJSON("");
	}
}
