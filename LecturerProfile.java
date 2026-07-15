package database;

import com.faculty.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class LecturerProfile extends JFrame {

    private JTextField fullNameField;
    private JTextField departmentField;
    private JTextField teachingCourseField;
    private JTextField emailField;
    private JTextField mobileField;

    private JButton editButton;

    private boolean editMode = false;

    private final LecturerDAO lecturerDAO = new LecturerDAO();

    private String originalEmail;
    private String lecturerName;

    public LecturerProfile(String lecturerEmail, String lecturerName) {

        this.originalEmail = lecturerEmail;
        this.lecturerName = lecturerName;

        setTitle("Lecturer Profile");
        UITheme.applyStandardBounds(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //sidebar
        SidebarPanel sidebar =
                new SidebarPanel(this, "profile", originalEmail, this.lecturerName);

        contentPane.add(sidebar, BorderLayout.WEST);

        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(UITheme.WHITE);
        rightPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Profile Details");
        titleLabel.setFont(UITheme.FONT_TITLE);
        titleLabel.setForeground(UITheme.PRIMARY);
        titleLabel.setBounds(50, 30, 300, 40);
        rightPanel.add(titleLabel);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBackground(UITheme.WHITE);
        detailsPanel.setBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        );
        detailsPanel.setBounds(50, 100, 650, 380);

        fullNameField = addDetailField(
                detailsPanel,
                "Full Name",
                40
        );

        departmentField = addDetailField(
                detailsPanel,
                "Department",
                100
        );

        teachingCourseField = addDetailField(
                detailsPanel,
                "Teaching Course",
                160
        );

        emailField = addDetailField(
                detailsPanel,
                "Email",
                220
        );

        mobileField = addDetailField(
                detailsPanel,
                "Mobile Number",
                280
        );

        rightPanel.add(detailsPanel);

        editButton = new JButton("Edit Profile");
        editButton.setFont(UITheme.FONT_LABEL_BOLD);
        editButton.setBackground(UITheme.PRIMARY);
        editButton.setForeground(UITheme.WHITE);
        editButton.setFocusPainted(false);
        editButton.setBounds(280, 520, 180, 40);
        rightPanel.add(editButton);

        editButton.addActionListener(e -> {

            if (!editMode) {
                enableEditing();
            } else {
                saveProfileDetails();
            }
        });

        contentPane.add(rightPanel, BorderLayout.CENTER);

        // Database එකෙන් details load කරනවා
        loadProfileDetails();

        setVisible(true);
    }

    private JTextField addDetailField(
            JPanel panel,
            String labelText,
            int y
    ) {

        JLabel label = new JLabel(labelText);
        label.setFont(UITheme.FONT_LABEL_BOLD);
        label.setForeground(UITheme.PRIMARY);
        label.setBounds(40, y, 180, 30);
        panel.add(label);

        JTextField textField = new JTextField();
        UITheme.styleTextField(textField);
        textField.setBounds(250, y, 300, 32);

        // Initially fields cannot be edited
        textField.setEditable(false);
        textField.setBackground(UITheme.WHITE);

        panel.add(textField);

        return textField;
    }

    private void loadProfileDetails() {

        Lecturer lecturer =
                lecturerDAO.getLecturerByEmail(originalEmail);

        if (lecturer == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Lecturer details were not found.",
                    "Not Found",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        fullNameField.setText(
                lecturer.getFullName()
        );

        departmentField.setText(
                lecturer.getDepartment()
        );

        teachingCourseField.setText(
                lecturer.getCoursesTeaching()
        );

        emailField.setText(
                lecturer.getEmail()
        );

        mobileField.setText(
                lecturer.getMobileNumber()
        );
    }

    private void enableEditing() {

        editMode = true;

        fullNameField.setEditable(true);
        departmentField.setEditable(true);
        teachingCourseField.setEditable(true);
        emailField.setEditable(true);
        mobileField.setEditable(true);

        editButton.setText("Save Changes");

        fullNameField.requestFocus();
    }

    private void saveProfileDetails() {

        String fullName =
                fullNameField.getText().trim();

        String department =
                departmentField.getText().trim();

        String teachingCourse =
                teachingCourseField.getText().trim();

        String email =
                emailField.getText().trim();

        String mobile =
                mobileField.getText().trim();

        // Empty fields check
        if (
                fullName.isEmpty()
                        || department.isEmpty()
                        || teachingCourse.isEmpty()
                        || email.isEmpty()
                        || mobile.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all profile details.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Simple email validation
        if (!email.contains("@") || !email.contains(".")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Lecturer lecturer = new Lecturer(
                fullName,
                department,
                teachingCourse,
                email,
                mobile
        );

        boolean updated = lecturerDAO.updateLecturer(
                lecturer,
                originalEmail
        );

        if (updated) {
            originalEmail = email;
            lecturerName = fullName;

            JOptionPane.showMessageDialog(
                    this,
                    "Profile updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            disableEditing();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Profile could not be updated.",
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void disableEditing() {

        editMode = false;

        fullNameField.setEditable(false);
        departmentField.setEditable(false);
        teachingCourseField.setEditable(false);
        emailField.setEditable(false);
        mobileField.setEditable(false);

        editButton.setText("Edit Profile");
    }

//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(() ->
//                new LecturerProfile("kumar@kln.ac.lk", "Kumar Sangakkara")
//        );
//    }
}
