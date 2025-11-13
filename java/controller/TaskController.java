/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controller;

import model.Task;
import model.TaskDAO;
import java.util.List;

public class TaskController {
    private TaskDAO dao;

    public TaskController() {
        dao = new TaskDAO();
    }

    public List<Task> getAllTasks() {
        return dao.getAllTasks();
    }

    public boolean addTask(Task task) {
        return dao.addTask(task);
    }

    public boolean updateTask(Task task) {
        return dao.updateTask(task);
    }

    public boolean deleteTask(int id) {
        return dao.deleteTask(id);
    }
}
