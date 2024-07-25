package com.gargouri.TaskManager.controller;


import com.gargouri.TaskManager.Task;
import com.gargouri.TaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/task")

public class TaskController {

    private final TaskService taskService ;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping(path = "/all")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id ){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<Task> addTaskById(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(task, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTasks(){
        taskService.deleteAllTasks();
        return ResponseEntity.noContent().build();
    }

}
