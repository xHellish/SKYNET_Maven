package graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modulo.Nodo;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class EntreDosCiudadesView extends JFrame {

	private JPanel contentPane;
	JComboBox comboBoxCiudad1;
	JComboBox comboBoxCiudad2;
	private JButton rutaMasCortaButton;
	
	public EntreDosCiudadesView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ciudad1Label = new JLabel("Ciudad Inicio");
		ciudad1Label.setBackground(new Color(205, 92, 92));
		ciudad1Label.setForeground(new Color(0, 0, 0));
		ciudad1Label.setFont(new Font("Tarzan", Font.PLAIN, 13));
		ciudad1Label.setBounds(10, 11, 104, 27);
		contentPane.add(ciudad1Label);
		
		JLabel ciudad2Label = new JLabel("Ciudad Destino");
		ciudad2Label.setForeground(Color.BLACK);
		ciudad2Label.setFont(new Font("Tarzan", Font.PLAIN, 13));
		ciudad2Label.setBackground(new Color(205, 92, 92));
		ciudad2Label.setBounds(124, 11, 104, 27);
		contentPane.add(ciudad2Label);
		
		comboBoxCiudad1 = new JComboBox();
		comboBoxCiudad1.setFont(new Font("Tarzan", Font.BOLD, 11));
		comboBoxCiudad1.setBounds(10, 41, 104, 27);
		contentPane.add(comboBoxCiudad1);
		
		comboBoxCiudad2 = new JComboBox();
		comboBoxCiudad2.setFont(new Font("Tarzan", Font.BOLD, 11));
		comboBoxCiudad2.setBounds(124, 41, 104, 27);
		contentPane.add(comboBoxCiudad2);
		
		rutaMasCortaButton = new JButton("Ruta Más Corta");
		rutaMasCortaButton.setBounds(10, 101, 147, 23);
		contentPane.add(rutaMasCortaButton);
	}
	
	public void agregarNombresComboBoxes(Vector<Nodo> grafoCiudades) {
		for (Nodo nodo : grafoCiudades) {
			comboBoxCiudad1.addItem(nodo.getNombre());
			comboBoxCiudad2.addItem(nodo.getNombre());
		}
	}
	
	public JButton getRutaMasCortaButton() {
		return rutaMasCortaButton;
	}
	
	public String getCiudadInicio() {
		return comboBoxCiudad1.getSelectedItem().toString();
	}
	
	public String getCiudadLlegada() {
		return comboBoxCiudad2.getSelectedItem().toString();
	}
}
