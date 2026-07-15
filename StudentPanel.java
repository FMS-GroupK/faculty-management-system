package com.faculty.view;

import com.faculty.controller.AdminController;
import com.faculty.model.Student;
import com.faculty.model.Degree;
import com.faculty.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentPanel extends JPanel {

    private final AdminController controller;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private List<Student> cachedList;

    public StudentPanel(AdminController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 15));
        setBackground(UITheme.CONTENT_BG);
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Students");
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
                new String[]{"Full Name", "Student ID", "Degree", "Email", "Mobile Number"}, 0) {
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
            Student s = cachedList.get(row);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete student \"" + s.getFullName() + "\"?", "Confirm delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteStudent(s.getId());
                refresh();
            }
        });

        refresh();
    }

    public void refresh() {
        cachedList = controller.getAllStudents();
        tableModel.setRowCount(0);
        for (Student s : cachedList) {
            tableModel.addRow(new Object[]{s.getFullName(), s.getStudentNumber(), s.getDegreeName(),
                    s.getEmail(), s.getMobileNumber()});
        }
    }

    private void openForm(Student existing) {
        List<Degree> degrees = controller.getAllDegrees();

        JTextField nameField = new JTextField(existing != null ? existing.getFullName() : "");
        JTextField studentIdField = new JTextField(existing != null ? existing.getStudentNumber() : "");
        JComboBox<Degree> degreeCombo = new JComboBox<>(degrees.toArray(new Degree[0]));
        JTextField emailField = new JTextField(existing != null ? existing.getEmail() : "");
        JTextField mobileField = new JTextField(existing != null ? existing.getMobileNumber() : "");

        if (existing != null && existing.getDegreeId() != null) {
            for (Degree deg : degrees) {
                if (deg.getId() == existing.getDegreeId()) {
                    degreeCombo.setSelectedItem(deg);
                    break;
                }
            }
        }

        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.add(new JLabel("Full name:"));
        form.add(nameField);
        form.add(new JLabel("Student ID:"));
        form.add(studentIdField);
        form.add(new JLabel("Degree:"));
        form.add(degreeCombo);
        form.add(new JLabel("Email:"));
        form.add(emailField);
        form.add(new JLabel("Mobile number:"));
        form.add(mobileField);

        int result = JOptionPane.showConfirmDialog(this, form,
                existing == null ? "Add Student" : "Edit Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (nameField.getText().trim().isEmpty() || studentIdField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Full name and Student ID are required.");
                return;
            }
            Degree selectedDegree = (Degree) degreeCombo.getSelectedItem();
            Integer degreeId = selectedDegree != null ? selectedDegree.getId() : null;

            if (existing == null) {
                Student s = new Student(0, nameField.getText().trim(), studentIdField.getText().trim(),
                        degreeId, emailField.getText().trim(), mobileField.getText().trim());
                controller.addStudent(s);
            } else {
                existing.setFullName(nameField.getText().trim());
                existing.setStudentNumber(studentIdField.getText().trim());
                existing.setDegreeId(degreeId);
                existing.setEmail(emailField.getText().trim());
                existing.setMobileNumber(mobileField.getText().trim());
                controller.updateStudent(existing);
            }
            refresh();
        }
    }
}
