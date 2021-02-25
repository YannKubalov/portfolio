package main.model.service;

import main.model.Task;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;


public interface TaskService {

    int newTask(String name);

    ResponseEntity deleteTask(int id);

    ResponseEntity updateTask(int id, String name);

    ArrayList<Task> getAllTasks();

    void deleteAllTasks();


}
