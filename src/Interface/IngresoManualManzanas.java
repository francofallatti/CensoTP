package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

import main.Censo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngresoManualManzanas {

	private JFrame frame;
	private Censo censo;
	DefaultComboBoxModel<Integer> manzanas1;
	DefaultComboBoxModel<Integer> manzanas2;

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
	 * 
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
		manzanas1 = new DefaultComboBoxModel<Integer>();
		comboBox.setModel(manzanas1);
		manzanas1.addAll(censo.manzanasACensar());

		JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setBounds(264, 105, 47, 22);
		frame.getContentPane().add(comboBox_1);
		manzanas2 = new DefaultComboBoxModel<Integer>();
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

		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manzanas1.getSelectedItem().equals(manzanas2.getSelectedItem())) {
					JOptionPane.showMessageDialog(frame, "Las manzanas contiguas deben ser distintas entre sí", "Error",
							JOptionPane.ERROR_MESSAGE);
				} if (manzanas1.getSelectedItem() == null || manzanas2.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(frame, "Debe cargar continuidad entre 2 (DOS) manzanas", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					censo.manzanasContiguas((Integer) manzanas1.getSelectedItem(),
							(Integer) manzanas2.getSelectedItem());
					JOptionPane.showMessageDialog(frame,
							"La continuidad entra las manzanas " + manzanas1.getSelectedItem() + " y "
									+ manzanas2.getSelectedItem() + " ha sido cargada correctamente!",
							"", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(166, 151, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Comenzar censo!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CensoInterface ci = new CensoInterface(censo, true);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(264, 227, 148, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
