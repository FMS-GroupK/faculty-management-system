package database;

import com.faculty.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class LecturerTeachingCourses extends JFrame {

    public LecturerTeachingCourses(String lecturerEmail, String lecturerName) {

        setTitle("Lecturer Teaching Courses");
        UITheme.applyStandardBounds(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        SidebarPanel sidebar =
                new SidebarPanel(this, "courses", lecturerEmail, lecturerName);

        contentPane.add(sidebar, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(UITheme.WHITE);
        rightPanel.setLayout(null);

        JLabel titleLabel =
                new JLabel("My Teaching Courses");

        titleLabel.setFont(UITheme.FONT_TITLE);
        titleLabel.setForeground(UITheme.PRIMARY);
        titleLabel.setBounds(50, 30, 400, 40);
        rightPanel.add(titleLabel);

        JTextField searchField = new JTextField();
        searchField.setFont(UITheme.FONT_LABEL);
        searchField.setBounds(520, 35, 200, 35);
        rightPanel.add(searchField);

        String[] columns = {
                "Course Code",
                "Course Name",
                "Department",
                "Credits",
                "Students"
        };

        Object[][] data = {
                {
                        "ETEC 2062",
                        "Object Oriented Programming",
                        "Software Engineering",
                        2,
                        24
                },
                {
                        "CSCI 21042",
                        "Data Structures",
                        "Computer Science",
                        2,
                        22
                },
                {
                        "ETEC 23032",
                        "Database Systems",
                        "Software Engineering",
                        2,
                        18
                },
                {
                        "ETEC 32032",
                        "Computer Networks",
                        "Software Engineering",
                        2,
                        20
                }
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(45);
        table.setFont(UITheme.FONT_LABEL);

        table.getTableHeader().setBackground(UITheme.TABLE_HEADER);
        table.getTableHeader().setForeground(UITheme.WHITE);
        table.getTableHeader().setFont(UITheme.FONT_LABEL_BOLD);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 110, 680, 280);
        rightPanel.add(scrollPane);

        JLabel totalLabel = new JLabel(
                "Total Courses: 4",
                SwingConstants.CENTER
        );

        totalLabel.setFont(UITheme.FONT_HEADING);
        totalLabel.setForeground(UITheme.PRIMARY);
        totalLabel.setBounds(250, 420, 280, 35);
        rightPanel.add(totalLabel);

        contentPane.add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
