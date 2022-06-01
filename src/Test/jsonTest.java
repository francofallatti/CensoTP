package Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import main.Tupla;


public class jsonTest {
	private static Map<Long, Tupla<Double, Double>> _coodenadas;
	@Test
	public void SetUp() {
		_coodenadas = new HashMap<>();
		JSONParser jsonParser = new JSONParser();
		try (FileReader resder = new FileReader("Coordenadas.json")){
			Object obj = jsonParser.parse(resder);
			JSONArray CoordenadasList = (JSONArray) obj;
			System.out.println("Json a mostrar");
			System.out.println(CoordenadasList);
			for(Object jsonObject : CoordenadasList ) {
				motrarInfo((JSONObject) jsonObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	private void motrarInfo(JSONObject jsonObject) {
		JSONObject info = (JSONObject) jsonObject.get("Vertice");
		System.out.println("Dentro de vertice:" + info);
		Long numero= (Long) info.get("numero");
		System.out.println("Numero de vertice: "+numero);
		double latitud= (Double) info.get("Latitud");
		System.out.println("Latitud: " +latitud);
		double longitud= (Double) info.get("Logitud");
		System.out.println("Logitud: " +longitud);	
		Tupla<Double, Double> coord = new Tupla<Double, Double>(latitud, longitud);
		_coodenadas.put(numero, coord);
		System.out.println(_coodenadas + " map");
	}
	/**
	@Test
	public void GrafoSetUp() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader resder = new FileReader("Grafo.json")){
			Object obj = jsonParser.parse(resder);
			JSONArray grafoList = (JSONArray) obj;
			System.out.println("Json a mostrar");
			System.out.println(grafoList);
			for(Object jsonObject : grafoList ) {
				GrafoInfo((JSONObject) jsonObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	private void GrafoInfo(JSONObject jsonObject) {
		Long vert1= (Long) jsonObject.get("vert1");
		System.out.println("vertice i : "+vert1);
		Long vert2= (Long) jsonObject.get("vert2");
		System.out.println("vertice j : "+vert2);
	}
	*/

}
