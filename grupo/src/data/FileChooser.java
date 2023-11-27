package data;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChooser {
	JFrame frame;
	String path;
	
	public FileChooser() {
		frame = new JFrame("File Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            this.path = selectedFile.getAbsolutePath();
            
        } else {
            System.out.println("Operación cancelada");
            path = "null";
        }

        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        
	}
	
	public String getPathGrafo() {
		return path;
	}
}

