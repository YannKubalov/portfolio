package main;

import main.model.Task;


import java.util.TreeMap;


public class Storage {
    private static int currentId = 1;

    private static TreeMap<Integer, Task> taskList = new TreeMap<>();

    public static TreeMap<Integer, Task> getAllTasks() {
        return taskList;
    }

    public static int addTask(String name) {
        Task task = new Task();
        task.setName(name);
        int id = currentId++;
        task.setId(id);
        taskList.put(id, task);
        return id;
    }

    public static void deleteTask(int id) {
        if (taskList.containsKey(id)) {
            taskList.remove(id);
        }
    }

    public static TreeMap<Integer, Task> updateTask(int id, String update) {
        Task task = new Task();
        task.setName(update);
        taskList.replace(id,task);
        return getAllTasks();
    }

    public static void deleteAllTasks() {
       taskList.clear();
    }


}
