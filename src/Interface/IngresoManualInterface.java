package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.Censo;

import javax.management.loading.PrivateClassLoader;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class IngresoManualInterface {

	private JFrame frame;
	private JTextField textField;
	private Censo censo;
	private JComboBox list;
	private DefaultListModel<String> m;

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
	public IngresoManualInterface(boolean b) {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese nombre de censista:");
		lblNewLabel.setBounds(31, 42, 150, 14);
		frame.getContentPane().add(lblNewLabel);
		
		list = new JComboBox();
		list.setBounds(392, 229, -352, -105);
		frame.getContentPane().add(list);
		m = new DefaultListModel<String>();
		list.setVisible(true);
		
		textField = new JTextField();
		textField.setBounds(191, 39, 183, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Cargar al sistema");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				censo.agregarCensista(textField.getText());
				m.addElement(textField.getText());
				System.out.println(m.firstElement());
			}
		});
		btnNewButton.setBounds(222, 70, 115, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}
}
