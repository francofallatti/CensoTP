package Interface;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import main.Censo;
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
		
		
		//agrego un marcador
		/*
		MapMarker marker= new MapMarkerDot("Aqui", coordenada);
		marker.getStyle().setBackColor(Color.red);
		marker.getStyle().setColor(Color.orange);
		JMap.addMapMarker(marker);*/
		Coordinate coordenada = new Coordinate(-34.521, -58.719);
		JMap.setDisplayPosition(coordenada, 12);
		MapMarker marker= new MapMarkerDot(coordenada);
		marker.getStyle().setBackColor(Color.red);
		marker.getStyle().setColor(Color.orange);
		JMap.addMapMarker(marker);
		System.out.println("hola1");
		for(Integer vert: Censo.get_SetCoodenadas()) {
			System.out.println("hola");
			System.out.println(vert);
			MapMarker marker2= new MapMarkerDot(Censo.get_coodenadas().get(vert).getE1(),Censo.get_coodenadas().get(vert).getE2());
			marker2.getStyle().setBackColor(Color.red);
			marker2.getStyle().setColor(Color.orange);
			JMap.addMapMarker(marker2);
		}
		System.out.println("hola2");
		frame.getContentPane().add(JMap);
	}

}
