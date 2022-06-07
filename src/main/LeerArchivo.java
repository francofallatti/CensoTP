package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LeerArchivo {
	public static JSONArray leerArchivoJSON(String archivo) {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(archivo)) {
			Object obj = jsonParser.parse(reader);
			JSONArray censistasList = (JSONArray) obj;
			return censistasList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
