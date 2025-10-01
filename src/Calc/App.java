package Calc;

import javax.swing.SwingUtilities;



public class App {
 public static void main(String[] args) {
     
     // Only one instance is created and shown (Singleton)
        SwingUtilities.invokeLater(() -> Calculator.getInstance().setVisible(true));
    }
}
