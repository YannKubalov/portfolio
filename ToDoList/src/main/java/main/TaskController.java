package main;

import main.model.service.TaskService;
import main.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping(value = "/tasks/")
    public int newTask(String name) {
        return taskService.newTask(name);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);

    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity updateTask(@PathVariable int id, String name) {
        return taskService.updateTask(id, name);
    }

    @GetMapping("/tasks/")
    public ArrayList<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/tasks/")
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }

}
