package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.Censo;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;

public class IngresoManualInterface {

	private JFrame frame;
	private Censo censo;
	private DefaultListModel<String> m;
	private JTextField nombreCensista;
	private JLabel lblNewLabel_2;
	JSpinner cantManzanas;

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
	public IngresoManualInterface(boolean b) {
		m = new DefaultListModel<String>();
		initialize(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize(boolean b) {
		frame = new JFrame();
		frame.setVisible(b);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel text = new JLabel("\u00BFCuantas manzanas van a ser censadas?\r\n");
		text.setBounds(10, 40, 243, 14);
		frame.getContentPane().add(text);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 116, 308, 100);
		frame.getContentPane().add(scrollPane);

		JList<String> list = new JList<String>();
		scrollPane.setColumnHeaderView(list);
		list.setBorder(null);
		list.setForeground(Color.BLACK);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setModel(m);

		JLabel lblNewLabel = new JLabel("Ingrese nombre del censista:");
		lblNewLabel.setBounds(10, 92, 176, 14);
		frame.getContentPane().add(lblNewLabel);

		nombreCensista = new JTextField();
		nombreCensista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Integer key = (int) e.getKeyChar();
				boolean mayusculas = key >= 65 && key <= 90;
				boolean minusculas = key >= 97 && key <= 122;

				if (!(mayusculas || minusculas)) {
					e.consume();
				}
			}
		});
		nombreCensista.setBounds(196, 89, 143, 20);
		frame.getContentPane().add(nombreCensista);
		nombreCensista.setColumns(10);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				censo.agregarCensista(nombreCensista.getText());
				m.addElement(nombreCensista.getText());
				nombreCensista.setText("");
			}
		});
		btnCargar.setBounds(348, 88, 76, 23);
		frame.getContentPane().add(btnCargar);

		JLabel lblNewLabel_1 = new JLabel("Ingreso de datos");
		lblNewLabel_1.setBounds(162, 11, 100, 14);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(141, 65, 153, 14);
		frame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);

		cantManzanas = new JSpinner();
		cantManzanas.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		cantManzanas.setBounds(263, 37, 30, 20);
		frame.getContentPane().add(cantManzanas);

		JButton btnNewButton_1 = new JButton("Cargar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				censo = new Censo((Integer) cantManzanas.getValue());
				lblNewLabel_2.setText((Integer) cantManzanas.getValue() + " manzanas a censar");
				lblNewLabel_2.setVisible(true);
			}
		});

		btnNewButton_1.setBounds(348, 36, 76, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton = new JButton("Continuar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Integer) cantManzanas.getValue() == 0) {
					JOptionPane.showMessageDialog(frame, "Debe haber al menos una manzana a censar!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} if(lblNewLabel_2.isVisible() == false) {
					JOptionPane.showMessageDialog(frame, "Debe cargar las manzanas a censar!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					IngresoManualManzanas im = new IngresoManualManzanas(censo, true);
					frame.setVisible(false);
				}

			}
		});

		btnNewButton.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);

	}
}
