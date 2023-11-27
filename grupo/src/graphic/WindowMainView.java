package graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import grafoLogic.CanvasGrafo;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;

public class WindowMainView extends JFrame {

    // Panels
    private JPanel contentPane;
    Panel panelCanvas;

    // Canvas
    CanvasGrafo canvasGrafo;

    // Buttons
    JButton subirGrafoButton;

    public WindowMainView() {

        // Resto del código de inicialización específico de WindowMainView
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panelCanvas = new Panel();
        panelCanvas.setBackground(SystemColor.inactiveCaption);
        panelCanvas.setBounds(10, 10, 764, 503);
        contentPane.add(panelCanvas);
        panelCanvas.setLayout(null);

        subirGrafoButton = new JButton("Subir Grafo");
        subirGrafoButton.setFont(new Font("Tarzan", Font.BOLD, 15));
        subirGrafoButton.setBounds(10, 519, 152, 31);
        contentPane.add(subirGrafoButton);

        canvasGrafo = new CanvasGrafo();
        canvasGrafo.setBackground(SystemColor.activeCaption);
        canvasGrafo.setBounds(10, 10, 744, 483);
        panelCanvas.add(canvasGrafo);
    }

    public JButton getSubirGrafoButton() {
        return subirGrafoButton;
    }

    public CanvasGrafo getCanvasGrafo() {
        return canvasGrafo;
    }

    public JPanel getPanelMain() {
        return contentPane;
    }
}
