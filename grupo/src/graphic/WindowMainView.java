package graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;


public class WindowMainView extends JFrame {

    // Panels
    private JPanel contentPane;

    // Buttons
    private JButton subirGrafoButton;
    private JButton verActualButton;
    private JButton verGrafoDisconexoButton;

    public WindowMainView() {
        // Resto del código de inicialización específico de WindowMainView
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 598, 278);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(255, 229, 229)); 
        contentPane.setLayout(null);

        // Botón Subir Grafo
        subirGrafoButton = new JButton("Subir Grafo");
        subirGrafoButton.setBounds(10, 192, 193, 36);
        subirGrafoButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        subirGrafoButton.setBackground(new Color(230, 57, 70)); 
        subirGrafoButton.setForeground(Color.BLACK);
        subirGrafoButton.setFocusPainted(false);
        contentPane.add(subirGrafoButton);

        // Botón Ver Grafo Modificado
        verActualButton = new JButton("Ver Grafo Modificado");
        verActualButton.setBounds(213, 192, 274, 36);
        verActualButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        verActualButton.setBackground(new Color(230, 57, 70)); 
        verActualButton.setForeground(Color.BLACK);
        verActualButton.setFocusPainted(false);
        contentPane.add(verActualButton);
        
        verGrafoDisconexoButton = new JButton("Ver Grafo Disconexo");
        verGrafoDisconexoButton.setForeground(Color.BLACK);
        verGrafoDisconexoButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        verGrafoDisconexoButton.setFocusPainted(false);
        verGrafoDisconexoButton.setBackground(new Color(230, 57, 70));
        verGrafoDisconexoButton.setBounds(10, 11, 254, 36);
        contentPane.add(verGrafoDisconexoButton);
    }

    public JButton getSubirGrafoButton() {
        return subirGrafoButton;
    }

    public JButton getVerActualButton() {
        return verActualButton;
    }

    public JPanel getPanelMain() {
        return contentPane;
    }
    
    public JButton getVerGrafoDisconexoButton() {
    	return verGrafoDisconexoButton;
    }
}
