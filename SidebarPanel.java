package database;

import com.faculty.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel(JFrame currentFrame, String selectedPage, String lecturerEmail, String lecturerName) {

        setPreferredSize(new Dimension(250, UITheme.FRAME_HEIGHT));
        setBackground(UITheme.PRIMARY);
        setLayout(null);

        // University of Kelaniya logo
        JLabel logoLabel = new JLabel(UITheme.loadLogo(70, 70));
        logoLabel.setBounds(20, 35, 80, 80);
        add(logoLabel);

        // Lecturer details
        JLabel welcomeLabel = new JLabel("Welcome,");
        welcomeLabel.setForeground(UITheme.WHITE);
        welcomeLabel.setFont(UITheme.FONT_HEADING);
        welcomeLabel.setBounds(105, 40, 130, 30);
        add(welcomeLabel);

        JLabel nameLabel = new JLabel(lecturerName != null ? lecturerName : "Lecturer");
        nameLabel.setForeground(UITheme.WHITE);
        nameLabel.setFont(UITheme.FONT_LABEL_BOLD);
        nameLabel.setBounds(105, 75, 140, 30);
        add(nameLabel);

        JLabel roleLabel = new JLabel("Lecturer");
        roleLabel.setForeground(UITheme.WHITE);
        roleLabel.setFont(UITheme.FONT_LABEL);
        roleLabel.setBounds(105, 110, 100, 25);
        add(roleLabel);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(20, 155, 210, 2);
        add(separator1);


        // Buttons - before selected
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

        dashboardButton.addActionListener(e -> {

            if (!selectedPage.equals("dashboard")) {
                currentFrame.dispose();
                new LecturerDashboard(lecturerEmail, lecturerName);
            }
        });

        profileButton.addActionListener(e -> {

            if (!selectedPage.equals("profile")) {
                currentFrame.dispose();
                new LecturerProfile(lecturerEmail, lecturerName);
            }
        });

        timetableButton.addActionListener(e -> {

            if (!selectedPage.equals("timetable")) {
                currentFrame.dispose();
                new LecturerTimeTable(lecturerEmail, lecturerName);
            }
        });

        coursesButton.addActionListener(e -> {

            if (!selectedPage.equals("courses")) {
                currentFrame.dispose();
                new LecturerTeachingCourses(lecturerEmail, lecturerName);
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
                new database.view.AuthenticationFrame().setVisible(true);
            }
        });
    }

    private JButton createMenuButton(
            String text,
            String imagePath
    ) {

        ImageIcon icon = UITheme.loadIcon(imagePath, 22, 22);

        JButton button = new JButton(text, icon);

        button.setFont(UITheme.FONT_LABEL_BOLD);
        button.setForeground(UITheme.WHITE);
        button.setBackground(UITheme.PRIMARY);

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);

        return button;
    }

    //button colors change
    private void selectButton(
            JButton button,
            String selectedIconPath
    ) {

        button.setBackground(UITheme.WHITE);
        button.setForeground(UITheme.PRIMARY);

        button.setIcon(
                UITheme.loadIcon(selectedIconPath, 22, 22)
        );
    }
}
