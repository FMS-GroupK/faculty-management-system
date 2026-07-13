package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentProfileDetails extends JFrame implements ActionListener {

    private String studentId;
    private String studentName;

    private JButton profileDetailsButton, timeTableButton, coursesEnrolledButton, saveButton, logoutButton;
    private JTextField nameField, studentIdField, degreeField, emailField, mobileNumberField;

    public StudentProfileDetails(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;

        setTitle("Student - Profile Details");
        setSize(1000, 600);
        setLocation(380, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container container = getContentPane();
        container.setBackground(Color.white);
        container.setLayout(null);

        //-------------------------------------Left Side Panel-------------------------------------------

        //Left side panel
        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setBounds(0, 0, 300, 600);
        leftSidePanel.setBackground(Color.darkGray);
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.Y_AXIS));
        container.add(leftSidePanel);

        //Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + studentName);
        welcomeLabel.setFont(new Font("Cambria Math", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setMaximumSize(new Dimension(300, 40));
        leftSidePanel.add(Box.createVerticalStrut(40));
        leftSidePanel.add(welcomeLabel);

        //Profile details button
        profileDetailsButton = new JButton("Profile Details");
        styleSidebarButton(profileDetailsButton, true);
        leftSidePanel.add(Box.createVerticalStrut(200));
        leftSidePanel.add(profileDetailsButton);

        //Timetable button
        timeTableButton = new JButton("Time Table");
        styleSidebarButton(timeTableButton, false);
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(timeTableButton);

        //Course enrolled button
        coursesEnrolledButton = new JButton("Courses Enrolled");
        styleSidebarButton(coursesEnrolledButton, false);
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(coursesEnrolledButton);

        //Log out button
        logoutButton = new JButton("Logout");
        styleSidebarButton(logoutButton, false);
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(logoutButton);

        //------------------------------------------Right Side Content----------------------------------------

        //Title
        JLabel titleLabel = new JLabel("Profile Details");
        titleLabel.setBounds(550, 40, 300, 40);
        titleLabel.setFont(new Font("Cambria Math", Font.BOLD, 30));
        titleLabel.setForeground(Color.black);
        container.add(titleLabel);

        //Name
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(380, 120, 150, 25);
        nameLabel.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        container.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(560, 120, 350, 30);
        container.add(nameField);

        //Student
        JLabel studentIdLabel = new JLabel("Student ID");
        studentIdLabel.setBounds(380, 180, 150, 25);
        studentIdLabel.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        container.add(studentIdLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(560, 180, 350, 30);
        studentIdField.setEditable(false);
        container.add(studentIdField);

        //Degree
        JLabel degreeLabel = new JLabel("Degree");
        degreeLabel.setBounds(380, 240, 150, 25);
        degreeLabel.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        container.add(degreeLabel);

        degreeField = new JTextField();
        degreeField.setBounds(560, 240, 350, 30);
        container.add(degreeField);

        //Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(380, 300, 150, 25);
        emailLabel.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        container.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(560, 300, 350, 30);
        container.add(emailField);

        //Mobile number
        JLabel mobileNumberLabel = new JLabel("Mobile Number");
        mobileNumberLabel.setBounds(380, 360, 150, 25);
        mobileNumberLabel.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        container.add(mobileNumberLabel);

        mobileNumberField = new JTextField();
        mobileNumberField.setBounds(560, 360, 350, 30);
        container.add(mobileNumberField);

        //New style for text fields' borders
        JTextField[] fields = { nameField, studentIdField, degreeField, emailField, mobileNumberField };
        for (JTextField field : fields) {
            field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            field.setForeground(Color.darkGray);
            field.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        }

        //Save button
        saveButton = new JButton("Save Changes");
        saveButton.setBounds(560, 435, 200, 35);
        saveButton.setFont(new Font("Cambria Math", Font.BOLD, 16));
        saveButton.setBackground(Color.darkGray);
        saveButton.setForeground(Color.white);
        container.add(saveButton);

        //Action listeners of all buttons
        profileDetailsButton.addActionListener(this);
        timeTableButton.addActionListener(this);
        coursesEnrolledButton.addActionListener(this);
        logoutButton.addActionListener(this);
        saveButton.addActionListener(this);

        loadProfileFromDatabase();
    }

    //Style for all buttons
    private void styleSidebarButton(JButton button, boolean active) {
        button.setFont(new Font("Cambria Math", Font.BOLD, 16));
        button.setBackground(active ? Color.lightGray : Color.white);
        button.setForeground(Color.darkGray);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 30));
    }


    //get info from database to display
    private void loadProfileFromDatabase() {
        String sql = "SELECT student_id, name, degree, email, mobile_number FROM students WHERE student_id = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                studentIdField.setText(rs.getString("student_id"));
                nameField.setText(rs.getString("name"));
                degreeField.setText(rs.getString("degree"));
                emailField.setText(rs.getString("email"));
                mobileNumberField.setText(rs.getString("mobile_number"));
            } else {
                JOptionPane.showMessageDialog(this, "No profile found for student ID: " + studentId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load profile: " + ex.getMessage());
        }
    }

    private void saveProfileToDatabase() {
        String sql = "UPDATE students SET name = ?, degree = ?, email = ?, mobile_number = ? WHERE student_id = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nameField.getText());
            ps.setString(2, degreeField.getText());
            ps.setString(3, emailField.getText());
            ps.setString(4, mobileNumberField.getText());
            ps.setString(5, studentId);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                studentName = nameField.getText(); // keep welcome label in sync on other screens
                JOptionPane.showMessageDialog(this, "Profile updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed - student ID not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save profile: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveProfileToDatabase();
        } else if (e.getSource() == timeTableButton) {
            dispose();
            new StudentTimeTable(studentId, studentName).setVisible(true);
        } else if (e.getSource() == coursesEnrolledButton) {
            dispose();
            new StudentCoursesEnrolled(studentId, studentName).setVisible(true);
        } else if (e.getSource() == logoutButton) {
            dispose();
            new database.view.AuthenticationFrame().setVisible(true);
        }
    }
}