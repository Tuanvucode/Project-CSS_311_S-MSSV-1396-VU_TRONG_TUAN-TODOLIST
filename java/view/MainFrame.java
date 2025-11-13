/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package view;

import controller.TaskController;
import model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private TaskController controller;

    public MainFrame() {
        controller = new TaskController();

        setTitle("To-do List App");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("TO-DO LIST", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        // table setup
        model = new DefaultTableModel(new Object[]{"ID", "Tiêu đề", "Mô tả", "Deadline", "Trạng thái"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Thêm");
        JButton editBtn = new JButton("Sửa");
        JButton delBtn = new JButton("Xóa");
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(delBtn);
        add(btnPanel, BorderLayout.SOUTH);

        loadTasks(); // load dữ liệu ban đầu

        // --- SỰ KIỆN ---
        addBtn.addActionListener(e -> addTask());
        editBtn.addActionListener(e -> editTask());
        delBtn.addActionListener(e -> deleteTask());
    }

    private void loadTasks() {
        model.setRowCount(0); // xóa bảng cũ
        List<Task> list = controller.getAllTasks();
        for (Task t : list) {
            model.addRow(new Object[]{
                    t.getId(), t.getTitle(), t.getDescription(), t.getDeadline(), t.getStatus()
            });
        }
    }

    private void addTask() {
        String title = JOptionPane.showInputDialog(this, "Nhập tiêu đề công việc:");
        if (title == null || title.trim().isEmpty()) return;
        String desc = JOptionPane.showInputDialog(this, "Nhập mô tả:");
        String deadline = JOptionPane.showInputDialog(this, "Nhập deadline (YYYY-MM-DD):");
        String status = "Chưa hoàn thành";

        Task task = new Task(0, title, desc, deadline, status);
        if (controller.addTask(task)) {
            JOptionPane.showMessageDialog(this, "Đã thêm công việc!");
            loadTasks();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm công việc!");
        }
    }

    private void editTask() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 công việc để sửa!");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        String title = JOptionPane.showInputDialog(this, "Tiêu đề:", model.getValueAt(row, 1));
        String desc = JOptionPane.showInputDialog(this, "Mô tả:", model.getValueAt(row, 2));
        String deadline = JOptionPane.showInputDialog(this, "Deadline (YYYY-MM-DD):", model.getValueAt(row, 3));
        String status = JOptionPane.showInputDialog(this, "Trạng thái (Hoàn thành / Chưa hoàn thành):", model.getValueAt(row, 4));

        Task task = new Task(id, title, desc, deadline, status);
        if (controller.updateTask(task)) {
            JOptionPane.showMessageDialog(this, "Đã cập nhật công việc!");
            loadTasks();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật!");
        }
    }

    private void deleteTask() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 công việc để xóa!");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.deleteTask(id)) {
                JOptionPane.showMessageDialog(this, "Đã xóa công việc!");
                loadTasks();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa công việc!");
            }
        }
    }
}
