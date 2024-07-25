package com.gargouri.TaskManager.service;

import com.gargouri.TaskManager.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task) ;
    Task updateTask(Task task , Long id);
    void deleteTask(Long id);
    void deleteAllTasks();
}
