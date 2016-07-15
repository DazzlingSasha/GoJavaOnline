package com.todoList.servlets;

import java.util.HashMap;
import java.util.Map;

public class Task {
    private static Map<String, String> task = new HashMap<>();

    public static Map<String, String> getTask() {
        return task;
    }

    public void setTask(Map<String, String> task) {
        Task.task = task;
    }

    public static void addTask(String name, String category) {
        task.put(name, category);
    }
}
