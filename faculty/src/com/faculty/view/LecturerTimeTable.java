package com.faculty.view;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class LecturerTimeTable extends JFrame {

    public LecturerTimeTable() {

        setTitle("Lecturer Time Table");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        SidebarPanel sidebar =
                new SidebarPanel(this, "timetable");

        contentPane.add(sidebar, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        JLabel titleLabel = new JLabel("My Time Table");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(50, 30, 300, 40);
        rightPanel.add(titleLabel);

        JLabel semesterLabel = new JLabel("Semester:");
        semesterLabel.setBounds(520, 35, 100, 35);
        rightPanel.add(semesterLabel);

        JComboBox<String> semesterComboBox =
                new JComboBox<>(
                        new String[]{
                                "Semester 1",
                                "Semester 2"
                        }
                );

        semesterComboBox.setBounds(610, 35, 130, 35);
        rightPanel.add(semesterComboBox);

        String[] columns = {
                "Time",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
        };

        Object[][] data = {
                {
                        "08:00 AM - 10:00 AM",
                        "ETEC 2062 - Lab 2",
                        "-",
                        "ETEC 2062 - Lab 2",
                        "-",
                        "ETEC 2062 - Lab 2"
                },
                {
                        "10:15 AM - 12:15 PM",
                        "CSCI 21042 - Room 305",
                        "CSCI 21042 - Room 305",
                        "-",
                        "CSCI 21042 - Room 305",
                        "-"
                },
                {
                        "01:00 PM - 03:00 PM",
                        "-",
                        "-",
                        "ETEC 2062 - Lab 2",
                        "-",
                        "ETEC 2062 - Lab 2"
                },
                {
                        "03:15 PM - 05:15 PM",
                        "-",
                        "ETEC 2062 - Lab 2",
                        "-",
                        "ETEC 2062 - Lab 2",
                        "-"
                }
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(70);

        table.getTableHeader().setBackground(
                new Color(105, 55, 210)
        );

        table.getTableHeader().setForeground(Color.WHITE);

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0; i < table.getColumnCount(); i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 110, 700, 350);
        rightPanel.add(scrollPane);

        contentPane.add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}