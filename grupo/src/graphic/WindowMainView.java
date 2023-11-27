package graphic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class WindowMainView extends JFrame {

    // Panels
    private JPanel contentPane;

    // Buttons
    private JButton subirGrafoButton;
    private JButton verActualButton;
    private JButton hacerGrafoDisconexoButton;
    private JButton saveButton;
    private JButton entreDosCiudadesButton;
    private JButton hacerArbolExpansionMinimaButton;
    private JButton eliminarArbolExpansionButton;
    private JButton verGrafoDirigidoButton;

    public WindowMainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 662, 278);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(255, 229, 229));
        contentPane.setLayout(null);

        // Botón Subir Grafo
        subirGrafoButton = new JButton("Subir Grafo");
        subirGrafoButton.setBounds(10, 192, 258, 36);
        subirGrafoButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        subirGrafoButton.setBackground(new Color(230, 57, 70));
        subirGrafoButton.setForeground(Color.BLACK);
        subirGrafoButton.setFocusPainted(false);
        contentPane.add(subirGrafoButton);

        // Botón Ver Grafo Modificado
        verActualButton = new JButton("Ver Grafo Modificado");
        verActualButton.setBounds(278, 192, 258, 36);
        verActualButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        verActualButton.setBackground(new Color(230, 57, 70));
        verActualButton.setForeground(Color.BLACK);
        verActualButton.setFocusPainted(false);
        contentPane.add(verActualButton);

        // Botón Hacer Grafo Disconexo
        hacerGrafoDisconexoButton = new JButton("Hacer Grafo Disconexo");
        hacerGrafoDisconexoButton.setForeground(Color.BLACK);
        hacerGrafoDisconexoButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        hacerGrafoDisconexoButton.setFocusPainted(false);
        hacerGrafoDisconexoButton.setBackground(new Color(230, 57, 70));
        hacerGrafoDisconexoButton.setBounds(10, 11, 258, 36);
        contentPane.add(hacerGrafoDisconexoButton);

        // Botón Guardar
        saveButton = new JButton("");
        saveButton.setForeground(Color.BLACK);
        saveButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        saveButton.setFocusPainted(false);
        saveButton.setBackground(new Color(230, 57, 70));
        saveButton.setBounds(546, 169, 91, 59);
        
        saveButton.setOpaque(false);
        saveButton.setContentAreaFilled(false);
        
        // Carga la imagen
        ImageIcon saveIcon = new ImageIcon("C:\\Users\\Hellish\\Desktop\\skynetMaven\\grupo\\data\\saveIcon.png");
        ImageIcon scaledIcon = new ImageIcon(saveIcon.getImage().getScaledInstance(94, 80, Image.SCALE_SMOOTH));
        saveButton.setIcon(scaledIcon);
        contentPane.add(saveButton);
        
        entreDosCiudadesButton = new JButton("Entre dos ciudades...\r\n");
        entreDosCiudadesButton.setForeground(Color.BLACK);
        entreDosCiudadesButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        entreDosCiudadesButton.setFocusPainted(false);
        entreDosCiudadesButton.setBackground(new Color(230, 57, 70));
        entreDosCiudadesButton.setBounds(278, 11, 258, 36);
        contentPane.add(entreDosCiudadesButton);
        
        hacerArbolExpansionMinimaButton = new JButton("Hacer Árbol Expansión");
        hacerArbolExpansionMinimaButton.setForeground(Color.BLACK);
        hacerArbolExpansionMinimaButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        hacerArbolExpansionMinimaButton.setFocusPainted(false);
        hacerArbolExpansionMinimaButton.setBackground(new Color(230, 57, 70));
        hacerArbolExpansionMinimaButton.setBounds(10, 58, 258, 36);
        contentPane.add(hacerArbolExpansionMinimaButton);
        
        eliminarArbolExpansionButton = new JButton("Eliminar Trasiego de Bienes");
        eliminarArbolExpansionButton.setForeground(Color.BLACK);
        eliminarArbolExpansionButton.setFont(new Font("Tarzan", Font.BOLD, 12));
        eliminarArbolExpansionButton.setFocusPainted(false);
        eliminarArbolExpansionButton.setBackground(new Color(230, 57, 70));
        eliminarArbolExpansionButton.setBounds(10, 95, 258, 26);
        contentPane.add(eliminarArbolExpansionButton);
        
        verGrafoDirigidoButton = new JButton("Ver Grafo Dirigido");
        verGrafoDirigidoButton.setForeground(Color.BLACK);
        verGrafoDirigidoButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        verGrafoDirigidoButton.setFocusPainted(false);
        verGrafoDirigidoButton.setBackground(new Color(230, 57, 70));
        verGrafoDirigidoButton.setBounds(278, 58, 258, 36);
        contentPane.add(verGrafoDirigidoButton);
        

    }
    
    // Getters
    
    // Buttons
    
    public JButton getSubirGrafoButton() {
        return subirGrafoButton;
    }

    public JButton getVerActualButton() {
        return verActualButton;
    }
    
    public JButton getHacerGrafoDisconexoButton() {
        return hacerGrafoDisconexoButton;
    }

	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JButton getEntreDosCiudadesButton() {
		return entreDosCiudadesButton;
	}
	
	public JButton getHacerArbolExpansionMinimaButton() {
		return hacerArbolExpansionMinimaButton;
	}
	
	public JButton getEliminarArbolExpansionButton() {
		return eliminarArbolExpansionButton;
	}
	
	public JButton getVerGrafoDirigidoButton() {
		return verGrafoDirigidoButton;
	}
	
	// Panel

    

	public JPanel getPanelMain() {
        return contentPane;
    }
}
