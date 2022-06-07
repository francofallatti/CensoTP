package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

import main.Censo;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class IngresoManualManzanas {

	private JFrame frame;
	private Censo censo;

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
	public IngresoManualManzanas(Censo c, boolean b) {
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
		
		JLabel lblNewLabel = new JLabel("Definir mapa");
		lblNewLabel.setBounds(179, 11, 87, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setBounds(264, 48, 47, 22);
		frame.getContentPane().add(comboBox);
		DefaultComboBoxModel<Integer> manzanas1 = new DefaultComboBoxModel<Integer>();
		comboBox.setModel(manzanas1);
		manzanas1.addAll(censo.manzanasACensar());
		
		JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setBounds(264, 105, 47, 22);
		frame.getContentPane().add(comboBox_1);
		DefaultComboBoxModel<Integer> manzanas2 = new DefaultComboBoxModel<Integer>();
		comboBox_1.setModel(manzanas2);
		manzanas2.addAll(censo.manzanasACensar());
		
		JLabel lblManzanaN = new JLabel("Manzana n\u00BA:");
		lblManzanaN.setBounds(110, 52, 87, 14);
		frame.getContentPane().add(lblManzanaN);
		
		JLabel lblManzanaN_1 = new JLabel("Manzana n\u00BA:");
		lblManzanaN_1.setBounds(110, 109, 87, 14);
		frame.getContentPane().add(lblManzanaN_1);
		
		JLabel lblEsContiguaCon = new JLabel("es contigua con:");
		lblEsContiguaCon.setBounds(179, 80, 111, 14);
		frame.getContentPane().add(lblEsContiguaCon);
	}
}
