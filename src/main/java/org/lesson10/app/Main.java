package org.lesson10.app;

import org.lesson10.model.TaskTracker;

public class Main {
    public static void main(String[] var0) {
        TaskTracker taskTracker = new TaskTracker();
        taskTracker.addNewTask("Сделать упражнения по гиту");
        taskTracker.addNewTask("Сделать домашку в IDEA");
        taskTracker.addNewTask("Онжумания");
        taskTracker.addNewTask("прес качат");
        taskTracker.getTaskStatus("Сделать домашку в IDEA");
        taskTracker.getTaskStatus("Сделать домашку в IDEA ");
        taskTracker.getTaskStatistics();
        taskTracker.showAllTasks();
        taskTracker.setTaskCompleted("Сделать домашку");
        taskTracker.setTaskCompleted("Сделать домашку в IDEA");
        taskTracker.showAllTasks();
        taskTracker.getTaskStatistics();

    }
}
