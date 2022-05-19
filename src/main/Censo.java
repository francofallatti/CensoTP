package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Censo {
	private Grafo _radioCensal;
	private ArrayList<Censista> _censitas;
	
	public Censo() {
		agregarCensista();
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
}
