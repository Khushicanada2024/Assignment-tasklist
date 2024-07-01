package org.example;

import java.util.Arrays;

public class TaskList {
    private Task[] tasks;
    private int size;

    public TaskList() {
        tasks = new Task[10];
        size = 0;
    }

    // Add tasks in the list
    public void addTask(String description) {
        ensureCapacity();
        tasks[size++] = new Task(description);
    }

    // Remove the tasks from the list
    public void removeTask(int index) {
        if (index >= 0 & index < size) {
            for (int i = index; i < size - 1; i++) {
                tasks[i] = tasks[i + 1];
            }
            tasks[--size] = null;
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Mark tasks as completed
    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < size) {
            tasks[index].markAsCompleted();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // View the list of tasks
    public void viewTasks() {
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    // View statistics about the tasks
    public void viewStatistics() {
        int totalTasks = size;
        long completedTasks = Arrays.stream(tasks, 0, size).filter(Task::isCompleted).count();
        double completionPercentage = (totalTasks > 0) ? (completedTasks * 100.0 / totalTasks) : 0;

        System.out.println("Total tasks: " + totalTasks);
        System.out.println("Completed tasks: " + completedTasks);
        System.out.println("Completion percentage: " + String.format("%.2f", completionPercentage) + "%");
    }

    // Sort tasks based on their completion status
    public void sortTasksByCompletion() {
        Arrays.sort(tasks, 0, size, (t1, t2) -> Boolean.compare(t1.isCompleted(), t2.isCompleted()));
    }

    private void ensureCapacity() {
        if (size == tasks.length) {
            tasks = Arrays.copyOf(tasks, tasks.length * 2);
        }
    }
}
