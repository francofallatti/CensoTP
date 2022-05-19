package Interface;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

public class MapInterface {

	private JFrame frame;
	private JMapViewer JMap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Trabajo Practico 3: El TP del censo!");
		
		JMap = new JMapViewer();
		JMap.setZoomControlsVisible(false);
		Coordinate coordenada = new Coordinate(-34.521, -58.719);
		JMap.setDisplayPosition(coordenada, 12);
		
		//agrego un marcador
		MapMarker marker= new MapMarkerDot("Aqui", coordenada);
		marker.getStyle().setBackColor(Color.red);
		marker.getStyle().setColor(Color.orange);
		JMap.addMapMarker(marker);
		
		
		frame.getContentPane().add(JMap);
	}

}
