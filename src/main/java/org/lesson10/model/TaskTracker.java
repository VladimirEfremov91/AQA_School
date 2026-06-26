package org.lesson10.model;

import java.util.ArrayList;

public class TaskTracker {
    private ArrayList<Task> tasks = new ArrayList<>();

//    Универсальный метод поиска задач
private Task findTask(String taskName) {
    for (Task task : tasks) {
        if (task.getTaskName().equals(taskName)) {
            return task;
        }
    }
    return null;
}

//    Метод создания новой задачи (по умолчанию создается невыполненная задача)
    public void addNewTask(String taskName) {
        Task task = new Task(taskName);
        tasks.add(task);
    }

//    Смотрим все задачи
    public void showAllTasks() {
        System.out.println("\nЗадачи:");
        for (Task task : tasks) {
            task.showTask();
        }
    }

//    Отмечаем задачу выполненной
    public void setTaskCompleted(String taskName) {
        Task foundTask = findTask(taskName);
        if (foundTask != null) {
            foundTask.setIsTaskCompleted(true);
            System.out.println("\nЗадача \"" + taskName + "\" отмечена как выполненная");
        } else {
            System.out.println("\nЗадача \"" + taskName + "\" не найдена");
        }
    }

//    Получаем статус задачи по имени
    public void getTaskStatus(String taskName) {
        Task foundTask = findTask(taskName);
        if (foundTask != null) {
            if (foundTask.getIsTaskCompleted()) {
                System.out.println("\nСтатус задачи \"" + taskName + "\": ВЫПОЛНЕНО");
            } else {
                System.out.println("\nСтатус задачи \"" + taskName + "\": НЕ ВЫПОЛНЕНО");
            }
        } else {
            System.out.println("\nЗадача \"" + taskName + "\" не найдена");
        }
    }

//    Получение статистики по всем задачам
    public void getTaskStatistics() {
        int completedTasks = 0;
        int totalTasks = tasks.size();
        for (Task task : tasks) {
            if (task.getIsTaskCompleted()) {
                completedTasks++;
            }
        }
        int openTasks = totalTasks - completedTasks;
        System.out.println("\nСтатистика по задачам:");
        System.out.println("Всего задач: " + totalTasks);
        System.out.println("Выполнено: " + completedTasks);
        System.out.println("Открыто: " + openTasks);
    }
}
