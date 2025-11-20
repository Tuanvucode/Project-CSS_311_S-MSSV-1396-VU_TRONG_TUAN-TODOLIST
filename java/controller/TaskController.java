/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controller;

import model.Task;
import model.TaskDAO;
import java.util.List;
import java.util.ArrayList;

public class TaskController {
    private TaskDAO dao;
    private List<Runnable> refreshCallbacks = new ArrayList<>();

    public TaskController() {
        dao = new TaskDAO();
    }

    // Thêm callback để các frame đăng ký
    public void addRefreshCallback(Runnable callback) {
        refreshCallbacks.add(callback);
    }

    // Gọi tất cả callback khi data thay đổi
    private void notifyDataChanged() {
        for (Runnable callback : refreshCallbacks) {
            if (callback != null) {
                callback.run();
            }
        }
    }

    public List<Task> getAllTasks() {
        return dao.getAllTasks();
    }

    public boolean addTask(Task task) {
        boolean success = dao.addTask(task);
        if (success) {
            notifyDataChanged(); // Thông báo data đã thay đổi
        }
        return success;
    }

    public boolean updateTask(Task task) {
        boolean success = dao.updateTask(task);
        if (success) {
            notifyDataChanged();
        }
        return success;
    }

    public boolean deleteTask(int id) {
        boolean success = dao.deleteTask(id);
        if (success) {
            notifyDataChanged();
        }
        return success;
    }
    
    public boolean updateTaskStatus(int id, String status) {
        boolean success = dao.updateTaskStatus(id, status);
        if (success) {
            notifyDataChanged();
        }
        return success;
    }
    
    // getTaskById
    public Task getTaskById(int id) {
        List<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}