package com.faculty.view;
import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    private final Color PURPLE = new Color(105, 55, 210);

    public SidebarPanel(JFrame currentFrame, String selectedPage) {

        setPreferredSize(new Dimension(250, 650));
        setBackground(PURPLE);
        setLayout(null);

        //first image icon(user)
        JLabel userImageLabel = new JLabel(
                loadIcon("/images/user.png", 70, 70)
        );

        userImageLabel.setBounds(20, 35, 80, 80);
        add(userImageLabel);


        // Lecturer details
        JLabel welcomeLabel = new JLabel("Welcome,");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBounds(105, 40, 130, 30);
        add(welcomeLabel);

        JLabel nameLabel = new JLabel("Kumar Sangakkara");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBounds(105, 75, 140, 30);
        add(nameLabel);

        JLabel roleLabel = new JLabel("Lecturer");
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        roleLabel.setBounds(105, 110, 100, 25);
        add(roleLabel);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(20, 155, 210, 2);
        add(separator1);


        // Buttons before selected
        JButton dashboardButton = createMenuButton(
                "Dashboard", "/images/dashboard-white.png"
        );

        dashboardButton.setBounds(15, 180, 220, 50);
        add(dashboardButton);


        JButton profileButton = createMenuButton(
                "Profile Details", "/images/profile-white.png"
        );

        profileButton.setBounds(15, 245, 220, 50);
        add(profileButton);


        JButton timetableButton = createMenuButton(
                "Time Table", "/images/timetable-white.png"
        );

        timetableButton.setBounds(15, 310, 220, 50);
        add(timetableButton);


        JButton coursesButton = createMenuButton(
                "Teaching Courses", "/images/courses-white.png"
        );

        coursesButton.setBounds(15, 375, 220, 50);
        add(coursesButton);


        JButton logoutButton = createMenuButton(
                "Logout",
                "/images/logout-white.png"
        );

        logoutButton.setBounds(15, 550, 220, 50);
        add(logoutButton);



        // Selected page button

        if (selectedPage.equals("dashboard")) {

            selectButton(
                    dashboardButton,
                    "/images/dashboard-purple.png"
            );

        } else if (selectedPage.equals("profile")) {

            selectButton(
                    profileButton,
                    "/images/profile-purple.png"
            );

        } else if (selectedPage.equals("timetable")) {

            selectButton(
                    timetableButton,
                    "/images/timetable-purple.png"
            );

        } else if (selectedPage.equals("courses")) {

            selectButton(
                    coursesButton,
                    "/images/courses-purple.png"
            );
        }


        // Navigation
        dashboardButton.addActionListener(e -> {

            if (!selectedPage.equals("dashboard")) {
                currentFrame.dispose();
                new LecturerDashboard();
            }
        });

        profileButton.addActionListener(e -> {

            if (!selectedPage.equals("profile")) {
                currentFrame.dispose();
                new LecturerProfile();
            }
        });

        timetableButton.addActionListener(e -> {

            if (!selectedPage.equals("timetable")) {
                currentFrame.dispose();
                new LecturerTimeTable();
            }
        });

        coursesButton.addActionListener(e -> {

            if (!selectedPage.equals("courses")) {
                currentFrame.dispose();
                new LecturerTeachingCourses();
            }
        });

        logoutButton.addActionListener(e -> {

            int answer = JOptionPane.showConfirmDialog(
                    currentFrame,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION) {
                currentFrame.dispose();
            }
        });
    }

    private JButton createMenuButton(
            String text,
            String imagePath
    ) {

        ImageIcon icon = loadIcon(imagePath, 22, 22);

        JButton button = new JButton(text, icon);

        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(PURPLE);

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);

        return button;
    }

   //insert logo to the left sidebar
        private ImageIcon loadIcon(String path, int width, int height) {

            java.net.URL imageURL = getClass().getResource(path);

            if (imageURL == null) {
                System.out.println("Image not found: " + path);
                return null;
            }

            ImageIcon originalIcon = new ImageIcon(imageURL);

            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    width,
                    height,
                    Image.SCALE_SMOOTH
            );

            return new ImageIcon(scaledImage);
        }


        //button colors change when it is selected or not select
        private void selectButton(
                JButton button,
                String selectedIconPath
        ) {

            button.setBackground(Color.WHITE);
            button.setForeground(PURPLE);

            button.setIcon(
                    loadIcon(selectedIconPath, 22, 22)
            );
        }
}