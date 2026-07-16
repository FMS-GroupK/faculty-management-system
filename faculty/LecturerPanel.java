package com.faculty.controller.faculty;

import com.faculty.controller.AdminController;
import com.faculty.model.Lecturer;
import com.faculty.model.Department;
import com.faculty.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LecturerPanel extends JPanel {

    private final AdminController controller;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private List<Lecturer> cachedList;

    public LecturerPanel(AdminController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 15));
        setBackground(UITheme.CONTENT_BG);
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Lecturers");
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
                new String[]{"Full Name", "Department", "Courses teaching", "Email", "Mobile Number"}, 0) {
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
            Lecturer l = cachedList.get(row);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete lecturer \"" + l.getFullName() + "\"?", "Confirm delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteLecturer(l.getId());
                refresh();
            }
        });

        refresh();
    }

    public void refresh() {
        cachedList = controller.getAllLecturers();
        tableModel.setRowCount(0);
        for (Lecturer l : cachedList) {
            tableModel.addRow(new Object[]{l.getFullName(), l.getDepartmentName(),
                    l.getCoursesTeaching() == null ? "" : l.getCoursesTeaching(),
                    l.getEmail(), l.getMobileNumber()});
        }
    }

    private void openForm(Lecturer existing) {
        List<Department> departments = controller.getAllDepartments();

        JTextField nameField = new JTextField(existing != null ? existing.getFullName() : "");
        JComboBox<Department> deptCombo = new JComboBox<>(departments.toArray(new Department[0]));
        JTextField emailField = new JTextField(existing != null ? existing.getEmail() : "");
        JTextField mobileField = new JTextField(existing != null ? existing.getMobileNumber() : "");

        if (existing != null && existing.getDepartmentId() != null) {
            for (Department dep : departments) {
                if (dep.getId() == existing.getDepartmentId()) {
                    deptCombo.setSelectedItem(dep);
                    break;
                }
            }
        }

        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.add(new JLabel("Full name:"));
        form.add(nameField);
        form.add(new JLabel("Department:"));
        form.add(deptCombo);
        form.add(new JLabel("Email:"));
        form.add(emailField);
        form.add(new JLabel("Mobile number:"));
        form.add(mobileField);

        int result = JOptionPane.showConfirmDialog(this, form,
                existing == null ? "Add Lecturer" : "Edit Lecturer",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Full name and email are required.");
                return;
            }
            Department selectedDept = (Department) deptCombo.getSelectedItem();
            Integer deptId = selectedDept != null ? selectedDept.getId() : null;

            if (existing == null) {
                Lecturer l = new Lecturer(0, nameField.getText().trim(), deptId,
                        emailField.getText().trim(), mobileField.getText().trim());
                controller.addLecturer(l);
            } else {
                existing.setFullName(nameField.getText().trim());
                existing.setDepartmentId(deptId);
                existing.setEmail(emailField.getText().trim());
                existing.setMobileNumber(mobileField.getText().trim());
                controller.updateLecturer(existing);
            }
            refresh();
        }
    }
}
