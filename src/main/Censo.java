package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Censo {
	private Grafo _radioCensal;
	private ArrayList<Censista> _censistas;
	private static Map<Integer, Tupla<Double, Double>> _coodenadas;
	private boolean[] _manzanasCensadas;

	public Censo() {
		agregarCensistasJSON();
		agregarCoordenadasJSON();
		AgregarAristasJSON();
		_manzanasCensadas = new boolean[_radioCensal.tamano()];
	}
	
	public static Map<Censista, ArrayList<Integer>> censar() {
		Censo censo = new Censo();
		return censo.asigarCensistas();
	}
	
	private Map<Censista, ArrayList<Integer>> asigarCensistas() {
		Map<Censista, ArrayList<Integer>> _censistasAsignados = new HashMap<Censista, ArrayList<Integer>>();
		while(!todosVisitados()) {
			Integer censistaAleatorio = new Random(_censistas.size()).nextInt();
			Censista censistaElegido = _censistas.get(censistaAleatorio);
			_censistasAsignados.put(censistaElegido, recorridoParaCensista());
		}
		return _censistasAsignados;
	}
	
	private boolean todosVisitados() {
		boolean ret = true;
		for(boolean b : _manzanasCensadas) {
			ret = ret && b;
		}
		return ret;
	}
	
	private Integer getVerticeNoVisitado() {
		Integer vertice = -1;
		Integer i = 0;
		while (vertice == -1 && i < _manzanasCensadas.length) {
			if(_manzanasCensadas[i] == false) {
				vertice = i+1;
			} else {
				i++;
			}
		}
		return vertice;
	}
	
	private ArrayList<Integer> recorridoParaCensista() {
		Integer origen = getVerticeNoVisitado();
		_manzanasCensadas[origen] = true;	//marco la manzana de origen
		ArrayList<Integer> recorrido = crearRecorrido(origen, _radioCensal, _manzanasCensadas);
		return recorrido;
	}
	
	private static ArrayList<Integer> crearRecorrido(Integer origen, Grafo radioCensal, boolean[] manzanasCensadas){
		ArrayList<Integer> rec = new ArrayList<Integer>();
		return crearRecorrido(rec, origen, radioCensal, manzanasCensadas);
	}
	
	private static ArrayList<Integer> crearRecorrido(ArrayList<Integer> recorrido, Integer origen, Grafo radioCensal, boolean[] manzanasCensadas){
		if(recorrido.size() == 3) {
			return recorrido;
		}
		if(todosVisitados(manzanasCensadas, radioCensal.getVecinos(origen))) {
			if(todosVisitados(manzanasCensadas, radioCensal.getVecinos(recorrido.get(0)))) {
				return recorrido;
			} else {
				return crearRecorrido(recorrido, recorrido.get(0), radioCensal, manzanasCensadas);
			}
		} else {
			Integer manzanaActual = getManzanaVecinaNoCensada(radioCensal, origen, manzanasCensadas, radioCensal.getVecinos(origen));
			recorrido.add(manzanaActual);
			manzanasCensadas[manzanaActual-1] = true;
			return crearRecorrido(recorrido, manzanaActual, radioCensal, manzanasCensadas);
		}
	}
	
	private static Integer getManzanaVecinaNoCensada(Grafo g, Integer origen, boolean[] manzanasCensadas, Set<Integer> vecinos) {
		Integer vertice = -1;
		for(Integer vecino : vecinos) {
			if(manzanasCensadas[vecino-1] == false) {
				vertice = vecino;
				break;
			}
		}
		return vertice;
	}
	
	private static boolean todosVisitados(boolean[] manzanasCensadas, Set<Integer> vertices) {
		boolean ret = true;
		for(Integer vertice : vertices) {
			ret = ret && manzanasCensadas[vertice-1];
		}
		return ret;
	}
	
	public void agregarCensistasJSON() {
		_censistas = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("Censistas.json")){
			Object obj = jsonParser.parse(reader);
			JSONArray CoordenadasList = (JSONArray) obj;
			for(Object jsonObject : CoordenadasList ) {
				agregarCensistas((JSONObject) jsonObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	private void agregarCensistas(JSONObject jsonObject) {
		String nombre= (String) jsonObject.get("nombre");
		String imagen= (String) jsonObject.get("imagen");
		Censista censista = new Censista(nombre, "/FotosCensistas/"+imagen);
		_censistas.add(censista);
	}
	
	public void agregarCoordenadasJSON() {
		_coodenadas = new HashMap<>();
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("Coordenadas.json")){
			Object obj = jsonParser.parse(reader);
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
		_coodenadas.put(numero.intValue(), coord);
	}
	
	public void AgregarAristasJSON() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("Grafo.json")){
			Object obj = jsonParser.parse(reader);
			JSONArray grafoList = (JSONArray) obj;
			for(Object jsonObject : grafoList) {
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
	
	public static Set<Integer> get_SetCoodenadas() {
		return _coodenadas.keySet();
	}

	public static Map<Integer, Tupla<Double, Double>> get_coodenadas() {
		return _coodenadas;
	}
	
	
}
