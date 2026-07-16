package com.faculty.controller.faculty;

import com.faculty.controller.AdminController;
import com.faculty.model.Degree;
import com.faculty.model.Department;
import com.faculty.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DegreePanel extends JPanel {

    private final AdminController controller;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private List<Degree> cachedList;

    public DegreePanel(AdminController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 15));
        setBackground(UITheme.CONTENT_BG);
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Degrees");
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

        tableModel = new DefaultTableModel(new String[]{"Degree", "Department", "No of Students"}, 0) {
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
            Degree d = cachedList.get(row);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete degree \"" + d.getName() + "\"?", "Confirm delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteDegree(d.getId());
                refresh();
            }
        });

        refresh();
    }

    public void refresh() {
        cachedList = controller.getAllDegrees();
        tableModel.setRowCount(0);
        for (Degree d : cachedList) {
            tableModel.addRow(new Object[]{d.getName(), d.getDepartmentName(), d.getStudentCount()});
        }
    }

    private void openForm(Degree existing) {
        List<Department> departments = controller.getAllDepartments();
        if (departments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add at least one Department first.");
            return;
        }

        JTextField nameField = new JTextField(existing != null ? existing.getName() : "");
        JComboBox<Department> deptCombo = new JComboBox<>(departments.toArray(new Department[0]));
        if (existing != null) {
            for (Department dep : departments) {
                if (dep.getId() == existing.getDepartmentId()) {
                    deptCombo.setSelectedItem(dep);
                    break;
                }
            }
        }

        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.add(new JLabel("Degree name:"));
        form.add(nameField);
        form.add(new JLabel("Department:"));
        form.add(deptCombo);

        int result = JOptionPane.showConfirmDialog(this, form,
                existing == null ? "Add Degree" : "Edit Degree",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (nameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Degree name is required.");
                return;
            }
            Department selectedDept = (Department) deptCombo.getSelectedItem();
            if (existing == null) {
                Degree d = new Degree(0, nameField.getText().trim(), selectedDept.getId());
                controller.addDegree(d);
            } else {
                existing.setName(nameField.getText().trim());
                existing.setDepartmentId(selectedDept.getId());
                controller.updateDegree(existing);
            }
            refresh();
        }
    }
}
