package modulo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInputGraph {
    public static int number=0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Grafo de seleccion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextField numberInputField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = numberInputField.getText();
                try {
                    number = Integer.parseInt(inputText);
                    //funcion de eliminar
                    System.out.println("Numero ingresado: " + number);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingresa un numero valido.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(numberInputField);
        panel.add(submitButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
