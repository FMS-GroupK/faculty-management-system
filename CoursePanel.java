package com.faculty.view;

import com.faculty.controller.AdminController;
import com.faculty.model.Course;
import com.faculty.model.Lecturer;
import com.faculty.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CoursePanel extends JPanel {

    private final AdminController controller;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private List<Course> cachedList;

    public CoursePanel(AdminController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 15));
        setBackground(UITheme.CONTENT_BG);
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Courses");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.PRIMARY);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        toolbar.setOpaque(false);
        JButton addBtn = UITheme.buildButton("Add new", UITheme.PRIMARY, UITheme.WHITE);
        JButton editBtn = UITheme.buildButton("Edit", new Color(0xB9B9B9), UITheme.WHITE);
        JButton deleteBtn = UITheme.buildButton("Delete", new Color(0xB9B9B9), UITheme.WHITE);
        toolbar.add(addBtn);
        toolbar.add(editBtn);
        toolbar.add(deleteBtn);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(title, BorderLayout.NORTH);
        header.add(toolbar, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(
                new String[]{"Course code", "Course name", "Credits", "Lecturer"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.getTableHeader().setBackground(UITheme.TABLE_HEADER);
        table.getTableHeader().setForeground(UITheme.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton saveBtn = UITheme.buildButton("Save changes", UITheme.PRIMARY, UITheme.WHITE);
        saveBtn.addActionListener(e -> refresh());
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setOpaque(false);
        footer.add(saveBtn);

        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> openForm(null));
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to edit.");
                return;
            }
            openForm(cachedList.get(row));
        });
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
                return;
            }
            Course c = cachedList.get(row);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete course \"" + c.getCourseCode() + "\"?", "Confirm delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteCourse(c.getId());
                refresh();
            }
        });

        refresh();
    }

    public void refresh() {
        cachedList = controller.getAllCourses();
        tableModel.setRowCount(0);
        for (Course c : cachedList) {
            tableModel.addRow(new Object[]{c.getCourseCode(), c.getCourseName(), c.getCredits(), c.getLecturerName()});
        }
    }

    private void openForm(Course existing) {
        List<Lecturer> lecturers = controller.getAllLecturers();

        JTextField codeField = new JTextField(existing != null ? existing.getCourseCode() : "");
        JTextField nameField = new JTextField(existing != null ? existing.getCourseName() : "");
        JTextField creditsField = new JTextField(existing != null ? String.valueOf(existing.getCredits()) : "2");
        JComboBox<Lecturer> lecturerCombo = new JComboBox<>(lecturers.toArray(new Lecturer[0]));

        if (existing != null && existing.getLecturerId() != null) {
            for (Lecturer lect : lecturers) {
                if (lect.getId() == existing.getLecturerId()) {
                    lecturerCombo.setSelectedItem(lect);
                    break;
                }
            }
        }

        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.add(new JLabel("Course code:"));
        form.add(codeField);
        form.add(new JLabel("Course name:"));
        form.add(nameField);
        form.add(new JLabel("Credits:"));
        form.add(creditsField);
        form.add(new JLabel("Lecturer:"));
        form.add(lecturerCombo);

        int result = JOptionPane.showConfirmDialog(this, form,
                existing == null ? "Add Course" : "Edit Course",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (codeField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Course code and name are required.");
                return;
            }
            int credits;
            try {
                credits = Integer.parseInt(creditsField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Credits must be a number.");
                return;
            }
            Lecturer selectedLecturer = (Lecturer) lecturerCombo.getSelectedItem();
            Integer lecturerId = selectedLecturer != null ? selectedLecturer.getId() : null;

            if (existing == null) {
                Course c = new Course(0, codeField.getText().trim(), nameField.getText().trim(), credits, lecturerId);
                controller.addCourse(c);
            } else {
                existing.setCourseCode(codeField.getText().trim());
                existing.setCourseName(nameField.getText().trim());
                existing.setCredits(credits);
                existing.setLecturerId(lecturerId);
                controller.updateCourse(existing);
            }
            refresh();
        }
    }
}
