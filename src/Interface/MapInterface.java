package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import main.Censista;
import main.Censo;
import main.Tupla;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapInterface {

	private JFrame frame;
	private JMapViewer JMap;
	private Censo censo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MapInterface window = new MapInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MapInterface() {
		censo = new Censo();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Trabajo Practico 3: El TP del censo!");

		JMap = new JMapViewer();
		JMap.setZoomControlsVisible(false);

		// agrego un marcador
		/*
		 * MapMarker marker= new MapMarkerDot("Aqui", coordenada);
		 * marker.getStyle().setBackColor(Color.red);
		 * marker.getStyle().setColor(Color.orange); JMap.addMapMarker(marker);
		 */
		Coordinate coordenada = new Coordinate(-34.521306,-58.704135);
		JMap.setDisplayPosition(coordenada, 14);//seteo el zoom y la pocision en la cual se va a ver el mapa
		
		for (Integer vert : Censo.get_SetCoodenadas()) {
			System.out.println(vert);
			MapMarker marker = new MapMarkerDot(Censo.get_coodenadas().get(vert).getE1(),
					Censo.get_coodenadas().get(vert).getE2());
			marker.getStyle().setBackColor(Color.red);
			marker.getStyle().setColor(Color.orange);
			JMap.addMapMarker(marker);
		}
		
		Color color2 = new Color((int)(Math.random() * 0x1000000));
		MapMarker Recorri2 = new MapMarkerDot(Censo.get_Coordenada(3).getE1(),Censo.get_Coordenada(3).getE2());
		Recorri2.getStyle().setBackColor(color2);
		Recorri2.getStyle().setColor(color2);
		JMap.addMapMarker(Recorri2);
		
		Map<Censista, ArrayList<Integer>> _censo = censo.censar();
		for (Censista c : _censo.keySet()) {
			//test
			Color color = new Color((int)(Math.random() * 0x1000000));
			for(Integer coor : _censo.get(c)) {
				System.out.println("vertice: " + c.getNombre() +" rec " + coor);
				
				MapMarker Recorrido = new MapMarkerDot(Censo.get_Coordenada(coor).getE1(),Censo.get_Coordenada(coor).getE2());
				Recorrido.getStyle().setBackColor(color);
				Recorrido.getStyle().setColor(color);
				JMap.addMapMarker(Recorrido);
			}
			System.out.println("vertice:" + c.getNombre() + " rec" + _censo.get(c));
			//System.out.println("rec" + _censo.get(c));
		}
		frame.getContentPane().add(JMap);
		
		JButton btnNewButton = new JButton("▶");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 227, 48, 23);
		JMap.add(btnNewButton);
	}
}
