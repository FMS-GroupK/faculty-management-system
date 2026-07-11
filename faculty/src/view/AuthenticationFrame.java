package database.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
/*
 This AuthenticationFrame class contains:
 1. Left-side faculty information panel
 2. Sign In panel
 3. Sign Up panel
 4. Button event handling
*/
public class AuthenticationFrame extends JFrame implements ActionListener {
    // Frame size
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    // Colours
    private final Color purple = new Color(132, 61, 245);
    private final Color lightGray = new Color(190, 190, 190);
    // Main panels
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel signInPanel;
    private JPanel signUpPanel;
    // Lines below the Sign In and Sign Up tabs
    private JPanel signInLine;
    private JPanel signUpLine;
    // Top tab buttons
    private JButton signInTabButton;
    private JButton signUpTabButton;
    // Sign In components
    private JTextField signInUsernameField;
    private JPasswordField signInPasswordField;
    private JRadioButton signInAdminRadio;
    private JRadioButton signInStudentRadio;
    private JRadioButton signInLecturerRadio;
    private JButton signInButton;
    // Sign Up components
    private JTextField signUpUsernameField;
    private JPasswordField signUpPasswordField;
    private JPasswordField confirmPasswordField;
    private JRadioButton signUpAdminRadio;
    private JRadioButton signUpStudentRadio;
    private JRadioButton signUpLecturerRadio;
    private JButton signUpButton;
    //Constructor
    public AuthenticationFrame() {
        // Set JFrame properties
        setTitle("Faculty Management System");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        createLeftPanel();
        createRightPanel();
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        // Show the Sign In form first
        showSignInPanel();
    }

    //Creates Left side Panel
    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(360, FRAME_HEIGHT));
        leftPanel.setBackground(purple);
        leftPanel.setLayout(null);
        // ICON
        JLabel iconLabel = new JLabel("🎓");
        iconLabel.setFont(new Font("Segoe UI Emoji",Font.PLAIN,80));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBounds(90, 45, 180, 100);
        leftPanel.add(iconLabel);
        // Main Title - Faculty MAnagement System
        JLabel titleLabel = new JLabel("Faculty Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(30,150,300,80);
        leftPanel.add(titleLabel);
        // Faculty name
        JLabel facultyLabel = new JLabel("Faculty of Computing & Technology");
        facultyLabel.setFont(new Font("Arial", Font.BOLD, 15));
        facultyLabel.setForeground(Color.WHITE);
        facultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        facultyLabel.setBounds(20, 390, 320, 30);
        leftPanel.add(facultyLabel);
        // Bottom message
        JLabel messageLabel = new JLabel("Manage your academic journey");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBounds(20,425, 320, 25);
        leftPanel.add(messageLabel);
    }
    //Create Right side panel
    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);
        // Sign In tab
        signInTabButton = new JButton("Sign In");
        signInTabButton.setFont(new Font("Arial", Font.BOLD, 19));
        signInTabButton.setBorderPainted(false);
        signInTabButton.setFocusPainted(false);
        signInTabButton.setContentAreaFilled(false);
        signInTabButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInTabButton.setBounds(45, 10, 190, 40);
        signInTabButton.addActionListener(this);
        rightPanel.add(signInTabButton);
        // Sign Up tab
        signUpTabButton = new JButton("Sign Up");
        signUpTabButton.setFont(new Font("Arial", Font.BOLD, 19));
        signUpTabButton.setBorderPainted(false);
        signUpTabButton.setFocusPainted(false);
        signUpTabButton.setContentAreaFilled(false);
        signUpTabButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpTabButton.setBounds(285, 10, 190, 40);
        signUpTabButton.addActionListener(this);
        rightPanel.add(signUpTabButton);
        // Line below Sign In tab
        signInLine = new JPanel();
        signInLine.setBounds(55, 50, 170, 2);
        rightPanel.add(signInLine);
        // Line below Sign Up tab
        signUpLine = new JPanel();
        signUpLine.setBounds(295, 50, 170, 2);
        rightPanel.add(signUpLine);
        // Create Signin,Signup panels
        createSignInPanel();
        createSignUpPanel();
        rightPanel.add(signInPanel);
        rightPanel.add(signUpPanel);
    }
    //signin panel
    private void createSignInPanel() {
        signInPanel = new JPanel();
        signInPanel.setBackground(Color.WHITE);
        signInPanel.setLayout(null);
        signInPanel.setBounds(0, 60, 540, 450);
        // Username label
        JLabel usernameLabel = createPurpleLabel("Username");
        usernameLabel.setBounds(30, 25, 150, 25);
        signInPanel.add(usernameLabel);
        // Username text field
        signInUsernameField = createTextField();
        signInUsernameField.setBounds(30, 55, 455, 38);
        signInPanel.add(signInUsernameField);
        // Password label
        JLabel passwordLabel = createPurpleLabel("Password");
        passwordLabel.setBounds(30, 110, 150, 25);
        signInPanel.add(passwordLabel);
        // Password field
        signInPasswordField = createPasswordField();
        signInPasswordField.setBounds(30, 140, 455, 38);
        signInPanel.add(signInPasswordField);
        // Role label
        JLabel roleLabel = createPurpleLabel("Role");
        roleLabel.setBounds(30, 195, 100, 25);
        signInPanel.add(roleLabel);
        //Create role radio buttons
        signInAdminRadio = createRoleRadioButton("Admin");
        signInAdminRadio.setBounds(30, 225, 110, 30);
        signInStudentRadio = createRoleRadioButton("Student");
        signInStudentRadio.setBounds(175, 225,110,30);
        signInLecturerRadio = createRoleRadioButton("Lecturer");
        signInLecturerRadio.setBounds(320, 225, 120, 30);

        ButtonGroup signInRoleGroup = new ButtonGroup();
        signInRoleGroup.add(signInAdminRadio);
        signInRoleGroup.add(signInStudentRadio);
        signInRoleGroup.add(signInLecturerRadio);
        // Admin is selected by default
        signInAdminRadio.setSelected(true);
        signInPanel.add(signInAdminRadio);
        signInPanel.add(signInStudentRadio);
        signInPanel.add(signInLecturerRadio);
        // Sign In button
        signInButton = createMainButton("Sign In");
        signInButton.setBounds(30, 290, 455, 42);
        signInButton.addActionListener(this);
        signInPanel.add(signInButton);
    }

    //Signup panel
    private void createSignUpPanel() {
        signUpPanel = new JPanel();
        signUpPanel.setBackground(Color.WHITE);
        signUpPanel.setLayout(null);
        signUpPanel.setBounds(0, 60, 540, 450);
        // Username label
        JLabel usernameLabel = createPurpleLabel("Username");
        usernameLabel.setBounds(30, 5, 150, 25);
        signUpPanel.add(usernameLabel);
        // Username field
        signUpUsernameField = createTextField();
        signUpUsernameField.setBounds(30, 35, 455, 36);
        signUpPanel.add(signUpUsernameField);
        // Password label
        JLabel passwordLabel = createPurpleLabel("Password");
        passwordLabel.setBounds(30, 80, 150, 25);
        signUpPanel.add(passwordLabel);
        // Password field
        signUpPasswordField = createPasswordField();
        signUpPasswordField.setBounds(30, 110, 455, 36);
        signUpPanel.add(signUpPasswordField);
        // Confirm password label
        JLabel confirmPasswordLabel = createPurpleLabel("Confirm Password");
        confirmPasswordLabel.setBounds(30, 155, 180, 25);
        signUpPanel.add(confirmPasswordLabel);
        // Confirm password field
        confirmPasswordField = createPasswordField();
        confirmPasswordField.setBounds(30, 185, 455, 36);
        signUpPanel.add(confirmPasswordField);
        // Role label
        JLabel roleLabel = createPurpleLabel("Role");
        roleLabel.setBounds(30, 230, 100, 25);
        signUpPanel.add(roleLabel);
        // Role radio buttons

// Create Admin radio button object
        signUpAdminRadio = createRoleRadioButton("Admin");
        signUpAdminRadio.setBounds(30, 260, 110, 30);
// Create Student radio button object
        signUpStudentRadio = createRoleRadioButton("Student");
        signUpStudentRadio.setBounds(175, 260, 110, 30);
// Create Lecturer radio button object
        signUpLecturerRadio = createRoleRadioButton("Lecturer");
        signUpLecturerRadio.setBounds(320, 260, 120, 30);
// Only one role can be selected
        ButtonGroup signUpRoleGroup = new ButtonGroup();
        signUpRoleGroup.add(signUpAdminRadio);
        signUpRoleGroup.add(signUpStudentRadio);
        signUpRoleGroup.add(signUpLecturerRadio);
// Student selected by default
        signUpStudentRadio.setSelected(true);
// Add radio buttons to Sign Up panel
        signUpPanel.add(signUpAdminRadio);
        signUpPanel.add(signUpStudentRadio);
        signUpPanel.add(signUpLecturerRadio);
        // Sign Up button
        signUpButton = createMainButton("Sign Up");
        signUpButton.setBounds(30, 315, 455, 42);
        signUpButton.addActionListener(this);
        signUpPanel.add(signUpButton);
    }
    private JLabel createPurpleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(purple);
        return label;
    }
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(new LineBorder(purple, 2, true));
        return textField;
    }
    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(new LineBorder(purple, 2, true));
        return passwordField;
    }
    private JRadioButton createRoleRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Arial", Font.BOLD, 12));
        radioButton.setForeground(purple);
        radioButton.setBackground(Color.WHITE);
        radioButton.setFocusPainted(false);
        return radioButton;
    }
    private JButton createMainButton(String text){
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(purple);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    //Handles All events
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == signInTabButton) {
            showSignInPanel();
        } else if (source == signUpTabButton) {
            showSignUpPanel();
        } else if (source == signInButton) {
            processSignIn();
        } else if (source == signUpButton) {
            processSignUp();
        }
    }

    //Set visible - Signin, signup panels
    private void showSignInPanel() {
        signInPanel.setVisible(true);
        signUpPanel.setVisible(false);
        signInTabButton.setForeground(purple);
        signUpTabButton.setForeground(lightGray);
        signInLine.setBackground(purple);
        signUpLine.setBackground(Color.WHITE);
    }
    private void showSignUpPanel() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(true);
        signInTabButton.setForeground(lightGray);
        signUpTabButton.setForeground(purple);
        signInLine.setBackground(Color.WHITE);
        signUpLine.setBackground(purple);
    }

    //Validate Signin,Signup forms
    private void processSignIn() {
        String username = signInUsernameField.getText().trim();
        String password = new String(signInPasswordField.getPassword());
        String role = getSignInRole();
        // Check username
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the username.", "Input Error", JOptionPane.WARNING_MESSAGE);
            signInUsernameField.requestFocus();
            return;
        }
        // Check password
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            signInPasswordField.requestFocus();
            return;
        }
        JOptionPane.showMessageDialog(this, "Sign In details accepted.\n" + "Username: " + username + "\nRole: " + role, "Sign In", JOptionPane.INFORMATION_MESSAGE);
    }
    private void processSignUp() {
        String username = signUpUsernameField.getText().trim();
        String password = new String(signUpPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = getSignUpRole();
        // Check username
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username.", "Input Error", JOptionPane.WARNING_MESSAGE);
            signUpUsernameField.requestFocus();
            return;
        }
        // Check password
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            signUpPasswordField.requestFocus();
            return;
        }
        // Check password length
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must have at least " + "6 characters.", "Input Error", JOptionPane.WARNING_MESSAGE);
            signUpPasswordField.requestFocus();
            return;
        }
        // Check whether both passwords are equal
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Password and Confirm Password " + "do not match.", "Input Error", JOptionPane.WARNING_MESSAGE);
            confirmPasswordField.setText("");
            confirmPasswordField.requestFocus();
            return;
        }
        //Temporary successful registration message
        JOptionPane.showMessageDialog(this, "Account created successfully.\n" + "Username: " + username + "\nRole: " + role, "Sign Up", JOptionPane.INFORMATION_MESSAGE);
        signInUsernameField.setText(username);
        signInPasswordField.setText("");
        clearSignUpFields();
        // Return to the Sign In form
        showSignInPanel();
    }
    private String getSignInRole() {
        if (signInAdminRadio.isSelected()) {
            return "Admin";
        } else if (signInStudentRadio.isSelected()) {
            return "Student";
        } else {
            return "Lecturer";
        }
    }
    private String getSignUpRole() {
        if (signUpAdminRadio.isSelected()) {
            return "Admin";
        } else if (signUpStudentRadio.isSelected()) {
            return "Student";
        } else {
            return "Lecturer";
        }
    }
    //clear Signup field
    private void clearSignUpFields() {

        signUpUsernameField.setText("");
        signUpPasswordField.setText("");
        confirmPasswordField.setText("");

        signUpStudentRadio.setSelected(true);
    }
}