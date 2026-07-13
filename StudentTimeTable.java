package database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentTimeTable extends JFrame implements ActionListener {

    private String studentId;
    private String studentName;

    private JButton profileDetailsButton, timeTableButton, coursesEnrolledButton, logoutButton;

    public StudentTimeTable(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;

        setTitle("Student - Time Table");
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

        //Welcome Message
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
        styleSidebarButton(profileDetailsButton, false);
        leftSidePanel.add(Box.createVerticalStrut(200));
        leftSidePanel.add(profileDetailsButton);

        //Timetable button
        timeTableButton = new JButton("Time Table");
        styleSidebarButton(timeTableButton, true); // this is the active screen
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(timeTableButton);

        //Course enrolled button
        coursesEnrolledButton = new JButton("Courses Enrolled");
        styleSidebarButton(coursesEnrolledButton, false);
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(coursesEnrolledButton);

        //Logout button
        logoutButton = new JButton("Logout");
        styleSidebarButton(logoutButton, false);
        leftSidePanel.add(Box.createVerticalStrut(30));
        leftSidePanel.add(logoutButton);

        //------------------------------------------Right Side Content----------------------------------------

        //Title
        JLabel titleLabel = new JLabel("Time Table");
        titleLabel.setBounds(550, 40, 300, 40);
        titleLabel.setFont(new Font("Cambria Math", Font.BOLD, 30));
        titleLabel.setForeground(Color.black);
        container.add(titleLabel);

        String[] columnNames = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Cambria Math", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Cambria Math", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(350, 100, 600, 300);
        container.add(scrollPane);

        loadTimeTableFromDatabase(tableModel);

        //Action listener for all buttons
        profileDetailsButton.addActionListener(this);
        timeTableButton.addActionListener(this);
        coursesEnrolledButton.addActionListener(this);
        logoutButton.addActionListener(this);
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

    private void loadTimeTableFromDatabase(DefaultTableModel tableModel) {
        String sql = "SELECT time_slot, monday, tuesday, wednesday, thursday, friday FROM timetable ORDER BY time_slot";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] row = {
                        rs.getString("time_slot"),
                        rs.getString("monday"),
                        rs.getString("tuesday"),
                        rs.getString("wednesday"),
                        rs.getString("thursday"),
                        rs.getString("friday")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load timetable: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == profileDetailsButton) {
            dispose();
            new StudentProfileDetails(studentId, studentName).setVisible(true);
        } else if (e.getSource() == coursesEnrolledButton) {
            dispose();
            new StudentCoursesEnrolled(studentId, studentName).setVisible(true);
        } else if (e.getSource() == logoutButton) {
            dispose();
            new database.view.AuthenticationFrame().setVisible(true);
        }
    }
}