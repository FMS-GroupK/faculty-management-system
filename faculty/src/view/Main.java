package view;

import view.AuthenticationFrame;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        try {
            AuthenticationFrame authenticationFrame;
            authenticationFrame = new AuthenticationFrame();
            authenticationFrame.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to start the application.\n" + exception.getMessage(), "Application Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}