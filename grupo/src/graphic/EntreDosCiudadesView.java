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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class EntreDosCiudadesView extends JFrame {

	private JPanel contentPane;
	JComboBox comboBoxCiudad1;
	JComboBox comboBoxCiudad2;
	private JButton rutaMasCortaButton;
	private JButton todosLosCaminosButton;
	private JButton caminoMasPoderosoButton;
	private JButton btnBorrarCamino;
	
	private JTextField textFieldIndex;
	
	public EntreDosCiudadesView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
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
		rutaMasCortaButton.setBackground(new Color(205, 92, 92));
		rutaMasCortaButton.setFont(new Font("Tarzan", Font.PLAIN, 13));
		rutaMasCortaButton.setBounds(10, 79, 218, 35);
		contentPane.add(rutaMasCortaButton);
		
		todosLosCaminosButton = new JButton("Ver todos los caminos");
		todosLosCaminosButton.setBackground(new Color(205, 92, 92));
		todosLosCaminosButton.setFont(new Font("Tarzan", Font.PLAIN, 13));
		todosLosCaminosButton.setBounds(10, 171, 218, 35);
		contentPane.add(todosLosCaminosButton);
		
		caminoMasPoderosoButton = new JButton("Camino Más Poderoso");
		caminoMasPoderosoButton.setFont(new Font("Tarzan", Font.PLAIN, 13));
		caminoMasPoderosoButton.setBackground(new Color(205, 92, 92));
		caminoMasPoderosoButton.setBounds(10, 125, 218, 35);
		contentPane.add(caminoMasPoderosoButton);
		
		textFieldIndex = new JTextField();
		textFieldIndex.setBounds(10, 217, 86, 20);
		contentPane.add(textFieldIndex);
		textFieldIndex.setColumns(10);
		
		btnBorrarCamino = new JButton("Borrar Camino");
		btnBorrarCamino.setFont(new Font("Tarzan", Font.PLAIN, 8));
		btnBorrarCamino.setBackground(new Color(205, 92, 92));
		btnBorrarCamino.setBounds(106, 217, 122, 20);
		contentPane.add(btnBorrarCamino);
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
	
	public JButton getTodosLosCaminosButton() {
		return todosLosCaminosButton;
	}
	
	public JButton getCaminoMasPoderosoButton() {
		return caminoMasPoderosoButton;
	}
	
	public String getCiudadInicio() {
		return comboBoxCiudad1.getSelectedItem().toString();
	}
	
	public String getCiudadLlegada() {
		return comboBoxCiudad2.getSelectedItem().toString();
	}
	
	public int getIndexBorrar() {
		return Integer.parseInt(textFieldIndex.getText());
	}
	
	public JButton getBtnBorrarCamino() {
		return btnBorrarCamino;
	}
}
