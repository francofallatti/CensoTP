package Interface;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;

import main.Censista;
import main.Censo;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class CensoInterface {

	private JFrame frame;
	private Censo censo;
	private JTable table;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
	 * @wbp.parser.entryPoint
	 */
	public CensoInterface(Censo c, boolean b) {
		censo = c;
		initialize(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean b) {
		frame = new JFrame();
		frame.setVisible(b);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Map<Censista, ArrayList<Integer>> resultado = censo.censar();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 50, 389, 137);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		modelo = new DefaultTableModel();
		table.setModel(modelo);
		modelo.addColumn("Censista");
		modelo.addColumn("Manzanas a censar");
		for(Censista c : resultado.keySet()) {
			modelo.addRow(new String[] {c.getNombre(), resultado.get(c).toString()});
		}
		
		JLabel lblNewLabel = new JLabel("CENSO 2022");
		lblNewLabel.setBounds(185, 11, 83, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quedaron " + censo.cantManzanasSinCensar() +" manzanas sin censar");
		lblNewLabel_1.setBounds(140, 210, 179, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
