package database;

import com.faculty.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCoursesEnrolled extends JFrame implements ActionListener {

    private String studentId;
    private String studentName;

    private JButton profileDetailsButton, timeTableButton, coursesEnrolledButton, logoutButton;

    public StudentCoursesEnrolled(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;

        setTitle("Student - Courses Enrolled");
        UITheme.applyStandardBounds(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container container = getContentPane();
        container.setBackground(UITheme.WHITE);
        container.setLayout(null);

        //-------------------------------------Left Side Panel-------------------------------------------

        //Left side panel
        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setBounds(0, 0, 300, UITheme.FRAME_HEIGHT);
        leftSidePanel.setBackground(UITheme.PRIMARY);
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.Y_AXIS));
        container.add(leftSidePanel);

        //University of Kelaniya logo
        JLabel logoLabel = new JLabel(UITheme.loadLogo(70, 70));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(logoLabel);

        //Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + studentName);
        welcomeLabel.setFont(UITheme.FONT_HEADING);
        welcomeLabel.setForeground(UITheme.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setMaximumSize(new Dimension(300, 40));
        leftSidePanel.add(Box.createVerticalStrut(15));
        leftSidePanel.add(welcomeLabel);

        //Profile details button
        profileDetailsButton = new JButton("Profile Details");
        styleSidebarButton(profileDetailsButton, false);
        leftSidePanel.add(Box.createVerticalStrut(150));
        leftSidePanel.add(profileDetailsButton);

        //Timetable button
        timeTableButton = new JButton("Time Table");
        styleSidebarButton(timeTableButton, false);
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(timeTableButton);

        //Course enrolled button
        coursesEnrolledButton = new JButton("Courses Enrolled");
        styleSidebarButton(coursesEnrolledButton, true);
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(coursesEnrolledButton);

        //Logout button
        logoutButton = new JButton("Logout");
        styleSidebarButton(logoutButton, false);
        leftSidePanel.add(Box.createVerticalStrut(90));
        leftSidePanel.add(logoutButton);

        //------------------------------------------Right Side Content----------------------------------------

        //Title
        JLabel titleLabel = new JLabel("Courses Enrolled");
        titleLabel.setBounds(550, 40, 300, 40);
        titleLabel.setFont(UITheme.FONT_TITLE);
        titleLabel.setForeground(UITheme.PRIMARY);
        container.add(titleLabel);

        String[] columnNames = {"Course Code", "Course Name", "Credits", "Grade"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // view-only table
            }
        };

        //Table
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(UITheme.FONT_LABEL);
        table.getTableHeader().setBackground(UITheme.TABLE_HEADER);
        table.getTableHeader().setForeground(UITheme.WHITE);
        table.getTableHeader().setFont(UITheme.FONT_LABEL_BOLD);

        //Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(350, 100, 600, 300);
        container.add(scrollPane);

        loadCoursesFromDatabase(tableModel);

        //Action listeners to all buttons
        profileDetailsButton.addActionListener(this);
        timeTableButton.addActionListener(this);
        coursesEnrolledButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    //Style for all buttons
    private void styleSidebarButton(JButton button, boolean active) {
        button.setFont(UITheme.FONT_LABEL_BOLD);
        button.setBackground(active ? UITheme.ROW_ALT : UITheme.WHITE);
        button.setForeground(UITheme.PRIMARY);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 30));
    }

    private void loadCoursesFromDatabase(DefaultTableModel tableModel) {
        String sql = "SELECT course_code, course_name, credits, grade FROM course_enrolled";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] row = {
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getInt("credits"),
                        rs.getString("grade")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load courses: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == profileDetailsButton) {
            dispose();
            new StudentProfileDetails(studentId, studentName).setVisible(true);
        } else if (e.getSource() == timeTableButton) {
            dispose();
            new StudentTimeTable(studentId, studentName).setVisible(true);
        } else if (e.getSource() == logoutButton) {
            dispose();
            new database.view.AuthenticationFrame().setVisible(true);
        }
    }
}
