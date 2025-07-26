package vista;
import javax.swing.*;



public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new vista.PanelVeterinarios().setVisible(true));
    }
}
