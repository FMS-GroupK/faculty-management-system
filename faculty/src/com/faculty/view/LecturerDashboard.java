package com.faculty.view;
import javax.swing.*;
import java.awt.*;

public class LecturerDashboard extends JFrame {

    public LecturerDashboard() {

        setTitle("Lecturer Dashboard");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Same left panel
        SidebarPanel sidebar =
                new SidebarPanel(this, "dashboard");

        contentPane.add(sidebar, BorderLayout.WEST);

        // Dashboard right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(50, 30, 300, 40);
        rightPanel.add(titleLabel);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        welcomePanel.setBackground(new Color(242, 235, 255));
        welcomePanel.setBounds(50, 100, 650, 130);

        JLabel welcomeBack = new JLabel("Welcome back,");
        welcomeBack.setFont(new Font("Arial", Font.PLAIN, 17));
        welcomeBack.setBounds(25, 20, 200, 25);
        welcomePanel.add(welcomeBack);

        JLabel nameLabel = new JLabel("Kumar Sangakkara");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBounds(25, 50, 300, 35);
        welcomePanel.add(nameLabel);

        JLabel messageLabel =
                new JLabel("Manage your teaching activities here.");

        messageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        messageLabel.setBounds(25, 90, 350, 25);
        welcomePanel.add(messageLabel);

        rightPanel.add(welcomePanel);

        JPanel courseCard =
                createCard("Teaching Courses", "4");

        courseCard.setBounds(50, 270, 190, 130);
        rightPanel.add(courseCard);

        JPanel studentCard =
                createCard("Students", "68");

        studentCard.setBounds(270, 270, 190, 130);
        rightPanel.add(studentCard);

        JPanel classCard =
                createCard("Today Classes", "2");

        classCard.setBounds(490, 270, 190, 130);
        rightPanel.add(classCard);

        JLabel scheduleLabel = new JLabel("Today Schedule");
        scheduleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scheduleLabel.setForeground(new Color(105, 55, 210));
        scheduleLabel.setBounds(50, 430, 250, 30);
        rightPanel.add(scheduleLabel);

        String[] columns = {
                "Time",
                "Course Code",
                "Course Name",
                "Room"
        };

        Object[][] data = {
                {
                        "08:00 AM - 10:00 AM",
                        "ETEC 2062",
                        "Object Oriented Programming",
                        "Lab 2"
                },
                {
                        "10:15 AM - 12:15 PM",
                        "CSCI 21042",
                        "Data Structures",
                        "Room 305"
                }
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(35);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 475, 650, 100);
        rightPanel.add(scrollPane);

        contentPane.add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createCard(String title, String count) {

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        );

        JLabel titleLabel = new JLabel(
                title,
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(10, 25, 170, 30);
        card.add(titleLabel);

        JLabel countLabel = new JLabel(
                count,
                SwingConstants.CENTER
        );

        countLabel.setFont(new Font("Arial", Font.BOLD, 28));
        countLabel.setBounds(10, 65, 170, 40);
        card.add(countLabel);

        return card;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new LecturerDashboard()
        );
    }
}