package org.lesson10.model;

import java.util.ArrayList;

public class TaskTracker {
    ArrayList<Task> tasks = new ArrayList<Task>();
    Task task = new Task();

//    Метод создания новой задачи (по умолчанию создается невыполненная задача)
    public void addNewTask(String taskName) {
        task.setTaskName(taskName);
        task.setIsTaskCompleted(false);
        tasks.addLast(task);
    }

//    Смотрим все задачи
    public void showAllTasks() {
        for (Task task : tasks) {
            task.showTask();
        }
    }

//    Отмечаем задачу выполненной
    public void setTaskCompleted(String taskName) {
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                foundTask = task;
                break;
            }
        }
        if (foundTask != null) {
            foundTask.setIsTaskCompleted(true);
            System.out.println("Задача \"" + taskName + "\" отмечена как выполненная");
        } else {
            System.out.println("Задача \"" + taskName + "\" не найдена");
        }
    }

//    Получаем статус задачи по имени
    public void getTaskStatus(String taskName) {
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                foundTask = task;
                break;
            }
        }
        if (foundTask != null) {
            if (foundTask.getIsTaskCompleted() == true) {
                System.out.println("Статус задачи \"" + taskName + "\": ВЫПОЛНЕНО");
            } else {
                System.out.println("Статус задачи \"" + taskName + "\": НЕ ВЫПОЛНЕНО");
            }
        } else {
            System.out.println("Задача \"" + taskName + "\" не найдена");
        }
    }

//    Получение статистики по задачам
    public void getTaskStatistics() {
        int completedTasks = 0;
        int openTasks = 0;
        int totalTasks = tasks.size();
        for (Task task : tasks) {
            if (task.getIsTaskCompleted() == true) {
                completedTasks++;
            } else {
                openTasks++;
            }
        }
        System.out.println("Статистика по задачам:");
        System.out.println("Всего задач: " + totalTasks);
        System.out.println("Выполнено: " + completedTasks);
        System.out.println("Открыто: " + openTasks);

    }


}
