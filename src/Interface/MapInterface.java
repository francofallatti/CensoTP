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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MapInterface(boolean b) {
		censo = new Censo();
		initialize(b);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean b) {
		frame = new JFrame();
		frame.setVisible(b);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Trabajo Practico 3: El TP del censo!");

		JMap = new JMapViewer();
		JMap.setZoomControlsVisible(false);

		
		Coordinate coordenada = new Coordinate(-34.521306,-58.704135);
		JMap.setDisplayPosition(coordenada, 14);//seteo el zoom y la pocision en la cual se va a ver el mapa
		
		// agrego un marcador de manzanas a censar
		for (Integer vert : censo.manzanasACensar()) {
			MapMarker marker = new MapMarkerDot(censo.getCoordenadas(vert).getE1(),
					censo.getCoordenadas(vert).getE2());
			marker.getStyle().setBackColor(Color.red);
			marker.getStyle().setColor(Color.orange);
			JMap.addMapMarker(marker);
		}
		
		
		frame.getContentPane().add(JMap);
		
		JButton btnNewButton = new JButton("Censar ▶");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<Censista, ArrayList<Integer>> _censo = censo.censar(); //devuelve: censista -> lista con numero de vertices del recorrido
				for (Censista c : _censo.keySet()) { //por cada censista
					Color color = new Color((int)(Math.random() * 0x1000000));
					for(Integer vertice : _censo.get(c)) { //recorro los vertices que visita
						MapMarker Recorrido = new MapMarkerDot(censo.getCoordenadas(vertice).getE1(),censo.getCoordenadas(vertice).getE2());
						Recorrido.getStyle().setBackColor(color);
						Recorrido.getStyle().setColor(color);
						JMap.addMapMarker(Recorrido);
					}
				}
			}
		});
		btnNewButton.setBounds(10, 227, 91, 23);
		JMap.add(btnNewButton);
	}
}
