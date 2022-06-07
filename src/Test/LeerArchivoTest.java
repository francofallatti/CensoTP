package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.json.simple.JSONArray;
import org.junit.Test;

import main.LeerArchivo;

public class LeerArchivoTest {
	@Test
	public void lenghtTest() {
		JSONArray CensistaList =LeerArchivo.leerArchivoJSON("Censistas.json");
		assertEquals(15, CensistaList.size());
	}

	@Test(expected = NullPointerException.class)
	public void nullException() {
		assertNull(LeerArchivo.leerArchivoJSON(null));
	}
	
	@Test
	public void vacioTest() {
		assertNull(LeerArchivo.leerArchivoJSON(""));
	}
}
