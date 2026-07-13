package database;

import database.view.AuthenticationFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                AuthenticationFrame authenticationFrame = new AuthenticationFrame();
                authenticationFrame.setVisible(true);
            } catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Unable to start the application.\n" + exception.getMessage(),
                        "Application Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}