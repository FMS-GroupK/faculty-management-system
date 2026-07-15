package com.faculty.view;

import com.faculty.controller.AdminController;
import com.faculty.model.Department;
import com.faculty.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DepartmentPanel extends JPanel {

    private final AdminController controller;
    private final DefaultTableModel tableModel;
    private final JTable table;

    public DepartmentPanel(AdminController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 15));
        setBackground(UITheme.CONTENT_BG);
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Departments");
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

        tableModel = new DefaultTableModel(new String[]{"Name", "HOD", "No of Staff"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.getTableHeader().setBackground(UITheme.TABLE_HEADER);
        table.getTableHeader().setForeground(UITheme.WHITE);
        table.getTableHeader().setFont(UITheme.FONT_LABEL);
        table.setSelectionBackground(UITheme.ROW_ALT);
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
            openForm(getDepartments().get(row));
        });
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
                return;
            }
            Department d = getDepartments().get(row);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete department \"" + d.getName() + "\"?", "Confirm delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteDepartment(d.getId());
                refresh();
            }
        });

        refresh();
    }

    private List<Department> cachedList;

    private List<Department> getDepartments() {
        return cachedList;
    }

    public void refresh() {
        cachedList = controller.getAllDepartments();
        tableModel.setRowCount(0);
        for (Department d : cachedList) {
            tableModel.addRow(new Object[]{d.getName(), d.getHodName(), d.getStaffCount()});
        }
    }

    private void openForm(Department existing) {
        JTextField nameField = new JTextField(existing != null ? existing.getName() : "");
        JTextField hodField = new JTextField(existing != null ? existing.getHodName() : "");

        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.add(new JLabel("Department name:"));
        form.add(nameField);
        form.add(new JLabel("HOD name:"));
        form.add(hodField);

        int result = JOptionPane.showConfirmDialog(this, form,
                existing == null ? "Add Department" : "Edit Department",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (nameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Department name is required.");
                return;
            }
            if (existing == null) {
                Department d = new Department(0, nameField.getText().trim(), hodField.getText().trim());
                controller.addDepartment(d);
            } else {
                existing.setName(nameField.getText().trim());
                existing.setHodName(hodField.getText().trim());
                controller.updateDepartment(existing);
            }
            refresh();
        }
    }
}
