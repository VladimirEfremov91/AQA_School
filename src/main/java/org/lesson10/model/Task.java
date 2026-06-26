package org.lesson10.model;

public class Task {
    private String taskName;
    private boolean isTaskCompleted;

    public Task(String taskName, Boolean isTaskCompleted) {
        this.taskName = taskName;
        this.isTaskCompleted = isTaskCompleted;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isTaskCompleted = false;
    }

//    public Task() {}

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getIsTaskCompleted() {
        return isTaskCompleted;
    }

    public void setIsTaskCompleted(Boolean isTaskCompleted) {
        this.isTaskCompleted = isTaskCompleted;
    }

    public void showTask() {
        if (isTaskCompleted) {
            System.out.println("[*] " + taskName);
        } else {
        System.out.println("[ ] " + taskName);
        }
    }

}
