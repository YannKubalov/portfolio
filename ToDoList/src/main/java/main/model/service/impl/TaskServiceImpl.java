package main.model.service.impl;

import main.model.service.TaskService;
import main.model.Task;
import main.model.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public int newTask(String name) {
        Task task = new Task();
        task.setName(name);
        return taskRepository.saveAndFlush(task).getId();
    }

    @Override
    public ResponseEntity deleteTask(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateTask(int id, String name) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.updateById(id, name);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(tasks::add);
        return tasks;
    }

    @Override
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
}
