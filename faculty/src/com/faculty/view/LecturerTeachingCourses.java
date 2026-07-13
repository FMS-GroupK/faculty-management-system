package com.faculty.view;
import javax.swing.*;
import java.awt.*;

public class LecturerTeachingCourses extends JFrame {

    public LecturerTeachingCourses() {

        setTitle("Lecturer Teaching Courses");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        SidebarPanel sidebar =
                new SidebarPanel(this, "courses");

        contentPane.add(sidebar, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        JLabel titleLabel =
                new JLabel("My Teaching Courses");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(50, 30, 400, 40);
        rightPanel.add(titleLabel);

        JTextField searchField = new JTextField();
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

        table.getTableHeader().setBackground(
                new Color(105, 55, 210)
        );

        table.getTableHeader().setForeground(Color.WHITE);

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 110, 680, 280);
        rightPanel.add(scrollPane);

        JLabel totalLabel = new JLabel(
                "Total Courses: 4",
                SwingConstants.CENTER
        );

        totalLabel.setFont(new Font("Arial", Font.BOLD, 17));
        totalLabel.setForeground(new Color(105, 55, 210));
        totalLabel.setBounds(250, 420, 280, 35);
        rightPanel.add(totalLabel);

        contentPane.add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
