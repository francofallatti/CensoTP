package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Censo {
	private Grafo _radioCensal;
	private ArrayList<Censista> _censitas;
	private static Map<Long, Tupla<Double, Double>> _coodenadas;
	
	

	public Censo() {
		agregarCensista();
		_coodenadas = new HashMap<>();
		agregarCoordenadasJSON();
		AgregarAristasJSON();
	}
	
	private void agregarCensista() {
		_censitas = new ArrayList<Censista>();
		try (FileReader fr = new FileReader("censistas.txt");
				BufferedReader br = new BufferedReader(fr)) {
			String txt,imagen,nombre = null;
			while ((txt = br.readLine()) != null) {
				if(txt.charAt(0)!='/') {
					nombre=txt;
				}else {
					imagen=txt;
					Censista censista = new Censista(nombre, imagen);
					_censitas.add(censista);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Censista> getCensistasArrayList() {
		return _censitas;
	}
	public ArrayList<String> getCensistasArrayListToString() {
		ArrayList<String> s= new ArrayList<String>();
		for(Censista censista: _censitas) {
			s.add(censista.getNombre());
			s.add(censista.get_imagen());
		}
		return s;
	}
	
	public void agregarCoordenadasJSON() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader resder = new FileReader("Coordenadas.json")){
			Object obj = jsonParser.parse(resder);
			JSONArray CoordenadasList = (JSONArray) obj;
			_radioCensal = new Grafo(CoordenadasList.size()+1);
			for(Object jsonObject : CoordenadasList ) {
				agregarCoordenadas((JSONObject) jsonObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	private void agregarCoordenadas(JSONObject jsonObject) {
		JSONObject info = (JSONObject) jsonObject.get("Vertice");
		Long numero= (Long) info.get("numero");
		double latitud= (Double) info.get("Latitud");
		double longitud= (Double) info.get("Logitud");
		Tupla<Double, Double> coord = new Tupla<Double, Double>(latitud, longitud);
		_coodenadas.put(numero, coord);
	}
	
	public void AgregarAristasJSON() {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader resder = new FileReader("Grafo.json")){
			Object obj = jsonParser.parse(resder);
			JSONArray grafoList = (JSONArray) obj;
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
		Long vert2= (Long) jsonObject.get("vert2");
		_radioCensal.agregarArista(vert1.intValue(), vert2.intValue());
	}
	
	
	public static Set<Long> get_SetCoodenadas() {
		return _coodenadas.keySet();
	}

	public static Map<Long, Tupla<Double, Double>> get_coodenadas() {
		return _coodenadas;
	}
	
	
}
