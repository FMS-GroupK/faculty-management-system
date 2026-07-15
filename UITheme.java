package com.faculty.util;

import javax.swing.*;
import java.awt.*;

//For all styles

public class UITheme {

    //Colors (for fonts, background, foreground, table headers, boarders)
    public static final Color PRIMARY = new Color(77, 21, 22);   // maroon
    public static final Color WHITE = Color.WHITE;
    public static final Color SIDEBAR_BG = PRIMARY;
    public static final Color CONTENT_BG = WHITE;
    public static final Color TABLE_HEADER = PRIMARY;
    public static final Color ROW_ALT = new Color(245, 232, 232);

    //Fonts (for titles, details, texts, labels)
    public static final Font FONT_TITLE = new Font("Franklin Gothic Demi", Font.BOLD, 26);
    public static final Font FONT_HEADING = new Font("Franklin Gothic Demi", Font.BOLD, 18);
    public static final Font FONT_LABEL = new Font("Franklin Gothic Book", Font.PLAIN, 15);
    public static final Font FONT_LABEL_BOLD = new Font("Franklin Gothic Book", Font.BOLD, 15);

    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 650;

    public static void applyStandardBounds(JFrame frame) {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
    }

    //Image
    public static ImageIcon loadLogo(int width, int height) {
        return loadIcon("/images/Kelaniya.png", width, height);
    }

    public static ImageIcon loadIcon(String absolutePath, int width, int height) {
        java.net.URL url = UITheme.class.getResource(absolutePath);
        if (url == null) {
            System.out.println("Image not found on classpath: " + absolutePath);
            return null;
        }
        Image scaled = new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    //Button
    public static JButton buildButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setFont(FONT_LABEL);
        return button;
    }

    //text field style
    public static void styleTextField(JTextField field) {
        field.setFont(FONT_LABEL);
        field.setForeground(PRIMARY);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setBackground(new Color(240, 240, 240));
    }
}
