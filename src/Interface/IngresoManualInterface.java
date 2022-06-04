package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.Censo;

import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngresoManualInterface {

	private JFrame frame;
	private JTextField textField;
	private Censo censo;
	private JList list;

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
		
		list = new JList<String>();
		list.setBounds(392, 229, -352, -74);
		frame.getContentPane().add(list);
		
		textField = new JTextField();
		textField.setBounds(191, 39, 183, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cargar al sistema");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				censo.agregarCensista(textField.getText());
			}
		});
		btnNewButton.setBounds(222, 70, 115, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}
}
