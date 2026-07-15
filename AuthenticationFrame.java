package database.view;

import com.faculty.util.UITheme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DBconnection;
import database.LecturerDAO;
import database.Lecturer;
import database.StudentProfileDetails;
/*
 This AuthenticationFrame class contains:
 1. Left-side faculty information panel
 2. Sign In panel
 3. Sign Up panel
 4. Button event handling
*/
public class AuthenticationFrame extends JFrame implements ActionListener {
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
        UITheme.applyStandardBounds(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        createLeftPanel();
        createRightPanel();
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        // Show the SignIn form first
        showSignInPanel();
    }

    //Creates Left side Panel
    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(360, UITheme.FRAME_HEIGHT));
        leftPanel.setBackground(UITheme.PRIMARY);
        leftPanel.setLayout(null);

        // University of Kelaniya logo
        ImageIcon icon = UITheme.loadLogo(90, 90);
        if (icon != null) {
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds(135, 40, 90, 90);
            leftPanel.add(imageLabel);
        }

        // Main Title - Faculty Management System
        JLabel titleLabel = new JLabel("<html><center>Faculty Management<br>System</center></html>");
        titleLabel.setFont(UITheme.FONT_TITLE);
        titleLabel.setForeground(UITheme.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(30,150,300,80);
        leftPanel.add(titleLabel);
        // Faculty name
        JLabel facultyLabel = new JLabel("Faculty of Computing & Technology");
        facultyLabel.setFont(UITheme.FONT_LABEL_BOLD);
        facultyLabel.setForeground(UITheme.WHITE);
        facultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        facultyLabel.setBounds(20, 390, 320, 30);
        leftPanel.add(facultyLabel);
        // Bottom message
        JLabel messageLabel = new JLabel("Manage your academic journey");
        messageLabel.setFont(UITheme.FONT_LABEL);
        messageLabel.setForeground(UITheme.WHITE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBounds(20,425, 320, 25);
        leftPanel.add(messageLabel);
    }
    //Create Right side panel
    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setBackground(UITheme.WHITE);
        rightPanel.setLayout(null);
        // Sign In tab
        signInTabButton = new JButton("Sign In");
        signInTabButton.setFont(UITheme.FONT_HEADING);
        signInTabButton.setBorderPainted(false);
        signInTabButton.setFocusPainted(false);
        signInTabButton.setContentAreaFilled(false);
        signInTabButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInTabButton.setBounds(45, 10, 190, 40);
        signInTabButton.addActionListener(this);
        rightPanel.add(signInTabButton);
        // Sign Up tab
        signUpTabButton = new JButton("Sign Up");
        signUpTabButton.setFont(UITheme.FONT_HEADING);
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
    //SignIn panel
    private void createSignInPanel() {
        signInPanel = new JPanel();
        signInPanel.setBackground(UITheme.WHITE);
        signInPanel.setLayout(null);
        signInPanel.setBounds(0, 60, 540, 450);
        // Username label
        JLabel usernameLabel = createMaroonLabel("Username");
        usernameLabel.setBounds(30, 25, 150, 25);
        signInPanel.add(usernameLabel);
        // Username text field
        signInUsernameField = createTextField();
        signInUsernameField.setBounds(30, 55, 455, 38);
        signInPanel.add(signInUsernameField);
        // Password label
        JLabel passwordLabel = createMaroonLabel("Password");
        passwordLabel.setBounds(30, 110, 150, 25);
        signInPanel.add(passwordLabel);
        // Password field
        signInPasswordField = createPasswordField();
        signInPasswordField.setBounds(30, 140, 455, 38);
        signInPanel.add(signInPasswordField);
        // Role label
        JLabel roleLabel = createMaroonLabel("Role");
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
        signUpPanel.setBackground(UITheme.WHITE);
        signUpPanel.setLayout(null);
        signUpPanel.setBounds(0, 60, 540, 450);
        // Username label
        JLabel usernameLabel = createMaroonLabel("Username");
        usernameLabel.setBounds(30, 5, 150, 25);
        signUpPanel.add(usernameLabel);
        // Username field
        signUpUsernameField = createTextField();
        signUpUsernameField.setBounds(30, 35, 455, 36);
        signUpPanel.add(signUpUsernameField);
        // Password label
        JLabel passwordLabel = createMaroonLabel("Password");
        passwordLabel.setBounds(30, 80, 150, 25);
        signUpPanel.add(passwordLabel);
        // Password field
        signUpPasswordField = createPasswordField();
        signUpPasswordField.setBounds(30, 110, 455, 36);
        signUpPanel.add(signUpPasswordField);
        // Confirm password label
        JLabel confirmPasswordLabel = createMaroonLabel("Confirm Password");
        confirmPasswordLabel.setBounds(30, 155, 180, 25);
        signUpPanel.add(confirmPasswordLabel);
        // Confirm password field
        confirmPasswordField = createPasswordField();
        confirmPasswordField.setBounds(30, 185, 455, 36);
        signUpPanel.add(confirmPasswordField);
        // Role label
        JLabel roleLabel = createMaroonLabel("Role");
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
    private JLabel createMaroonLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UITheme.FONT_LABEL_BOLD);
        label.setForeground(UITheme.PRIMARY);
        return label;
    }
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(UITheme.FONT_LABEL);
        textField.setBorder(new LineBorder(UITheme.PRIMARY, 2, true));
        return textField;
    }
    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(UITheme.FONT_LABEL);
        passwordField.setBorder(new LineBorder(UITheme.PRIMARY, 2, true));
        return passwordField;
    }
    private JRadioButton createRoleRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(UITheme.FONT_LABEL_BOLD);
        radioButton.setForeground(UITheme.PRIMARY);
        radioButton.setBackground(UITheme.WHITE);
        radioButton.setFocusPainted(false);
        return radioButton;
    }
    private JButton createMainButton(String text){
        JButton button = new JButton(text);
        button.setFont(UITheme.FONT_HEADING);
        button.setBackground(UITheme.PRIMARY);
        button.setForeground(UITheme.WHITE);
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

    //Set visible - SignIn, SignUp panels
    private void showSignInPanel() {
        signInPanel.setVisible(true);
        signUpPanel.setVisible(false);
        signInTabButton.setForeground(UITheme.PRIMARY);
        signUpTabButton.setForeground(Color.LIGHT_GRAY);
        signInLine.setBackground(UITheme.PRIMARY);
        signUpLine.setBackground(UITheme.WHITE);
    }
    private void showSignUpPanel() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(true);
        signInTabButton.setForeground(Color.LIGHT_GRAY);
        signUpTabButton.setForeground(UITheme.PRIMARY);
        signInLine.setBackground(UITheme.WHITE);
        signUpLine.setBackground(UITheme.PRIMARY);
    }

    //Validate Signin, Signup forms
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

        // Check the credentials against the users table.
        String checkUserSql = "SELECT username, role FROM users WHERE username = ? AND password = ? AND role = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(checkUserSql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Invalid username, password, or role.", "Sign In Failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (role.equals("STUDENT")) {
                openStudentDashboard(username);
            } else if (role.equals("LECTURER")) {
                openLecturerDashboard(username);
            } else if (role.equals("ADMIN")) {
                dispose();
                new com.faculty.view.AdminDashboardView().setVisible(true);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error during sign in: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Looks up the logged-in student's record by name and opens their dashboard.
    private void openStudentDashboard(String username) {
        String studentSql = "SELECT student_id, name FROM students WHERE name = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(studentSql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String studentId = rs.getString("student_id");
                    String studentName = rs.getString("name");
                    dispose();
                    StudentProfileDetails studentProfile = new StudentProfileDetails(studentId, studentName);
                    studentProfile.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Signed in, but no matching student record was found for \"" + username + "\".\n" +
                                    "Ask your database teammate to add this student to the students table.",
                            "No Profile Found", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error while loading profile: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Looks up the logged-in lecturer's record by name and opens their dashboard
    private void openLecturerDashboard(String username) {
        LecturerDAO lecturerDAO = new LecturerDAO();
        Lecturer lecturer = lecturerDAO.getLecturerByFullName(username);

        if (lecturer == null) {
            JOptionPane.showMessageDialog(this,
                    "Signed in, but no matching lecturer record was found for \"" + username + "\".\n" +
                            "Ask your database teammate to add this lecturer to the lecturers table.",
                    "No Profile Found", JOptionPane.WARNING_MESSAGE);
            return;
        }

        dispose();
        new database.LecturerDashboard(lecturer.getEmail(), lecturer.getFullName());
    }

    //Sign up process
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

        if (role.equals("LECTURER")) {
            Lecturer matchingLecturer = new LecturerDAO().getLecturerByFullName(username);
            if (matchingLecturer == null) {
                JOptionPane.showMessageDialog(this,
                        "No lecturer record was found for \"" + username + "\".\n" +
                                "Lecturers must sign up using the exact full name stored in the " +
                                "lecturers table (e.g. Kumar Sanga) as their username.\n" +
                                "Ask your database teammate to add you to the lecturers table first if you're not in it yet.",
                        "Sign Up Failed", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        String insertSql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertSql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Account created successfully.\n" + "Username: " + username + "\nRole: " + role, "Sign Up", JOptionPane.INFORMATION_MESSAGE);
            signInUsernameField.setText(username);
            signInPasswordField.setText("");
            clearSignUpFields();

            showSignInPanel();

        } catch (SQLException ex) {
            ex.printStackTrace();
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(this, "That username is already taken. Please choose another.", "Sign Up Failed", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Could not create account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private String getSignInRole() {
        if (signInAdminRadio.isSelected()) {
            return "ADMIN";
        } else if (signInStudentRadio.isSelected()) {
            return "STUDENT";
        } else {
            return "LECTURER";
        }
    }
    private String getSignUpRole() {
        if (signUpAdminRadio.isSelected()) {
            return "ADMIN";
        } else if (signUpStudentRadio.isSelected()) {
            return "STUDENT";
        } else {
            return "LECTURER";
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
