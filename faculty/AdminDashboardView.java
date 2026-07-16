package com.faculty.controller.faculty;

import com.faculty.controller.AdminController;
import com.faculty.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardView extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);
    private final AdminController controller = new AdminController();

    private JButton studentsNav, lecturersNav, coursesNav, departmentsNav, degreesNav;

    private StudentPanel studentPanel;
    private LecturerPanel lecturerPanel;
    private CoursePanel coursePanel;
    private DepartmentPanel departmentPanel;
    private DegreePanel degreePanel;

    public AdminDashboardView() {
        setTitle("Faculty Management System - Admin");
        UITheme.applyStandardBounds(this);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(buildSidebar(), BorderLayout.WEST);
        add(buildContent(), BorderLayout.CENTER);

        showCard("Lecturers");
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(UITheme.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(25, 15, 15, 15));

        JLabel logo = new JLabel(UITheme.loadLogo(60, 60));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel welcome = new JLabel("Welcome, Admin");
        welcome.setFont(UITheme.FONT_HEADING);
        welcome.setForeground(UITheme.WHITE);
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 5, 20, 0));

        studentsNav = navButton("Students");
        lecturersNav = navButton("Lecturers");
        coursesNav = navButton("Courses");
        departmentsNav = navButton("Departments");
        degreesNav = navButton("Degrees");

        studentsNav.addActionListener(e -> showCard("Students"));
        lecturersNav.addActionListener(e -> showCard("Lecturers"));
        coursesNav.addActionListener(e -> showCard("Courses"));
        departmentsNav.addActionListener(e -> showCard("Departments"));
        degreesNav.addActionListener(e -> showCard("Degrees"));

        JButton logoutBtn = new JButton("Log out");
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBackground(UITheme.WHITE);
        logoutBtn.setForeground(UITheme.PRIMARY);
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Log out of the Admin dashboard?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new database.view.AuthenticationFrame().setVisible(true);
            }
        });

        sidebar.add(logo);
        sidebar.add(welcome);
        sidebar.add(studentsNav);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(lecturersNav);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(coursesNav);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(departmentsNav);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(degreesNav);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutBtn);

        return sidebar;
    }

    private JButton navButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(UITheme.SIDEBAR_BG);
        button.setForeground(UITheme.WHITE);
        button.setFont(UITheme.FONT_LABEL);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JPanel buildContent() {
        studentPanel = new StudentPanel(controller);
        lecturerPanel = new LecturerPanel(controller);
        coursePanel = new CoursePanel(controller);
        departmentPanel = new DepartmentPanel(controller);
        degreePanel = new DegreePanel(controller);

        contentPanel.add(studentPanel, "Students");
        contentPanel.add(lecturerPanel, "Lecturers");
        contentPanel.add(coursePanel, "Courses");
        contentPanel.add(departmentPanel, "Departments");
        contentPanel.add(degreePanel, "Degrees");

        return contentPanel;
    }

    private void showCard(String name) {
        switch (name) {
            case "Students":
                studentPanel.refresh();
                break;
            case "Lecturers":
                lecturerPanel.refresh();
                break;
            case "Courses":
                coursePanel.refresh();
                break;
            case "Departments":
                departmentPanel.refresh();
                break;
            case "Degrees":
                degreePanel.refresh();
                break;
        }
        cardLayout.show(contentPanel, name);
    }
}
