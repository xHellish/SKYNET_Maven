package skynet;

import graphic.*;
public class Main {

	public static void main(String[] args) {
		
		// Inicialización de la ventana, el modelo y el controller.
		WindowMainView vista = new WindowMainView();
		WindowMainModel modelo = new WindowMainModel();
		WindowMainController controller = new WindowMainController(vista, modelo);
		vista.setVisible(true);
		vista.setResizable(false);
		
	}
}
