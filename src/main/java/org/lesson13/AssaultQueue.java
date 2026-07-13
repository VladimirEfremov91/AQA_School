package org.lesson13;

import java.util.LinkedList;

public class AssaultQueue {
    private LinkedList queue;

    public AssaultQueue() {
        queue = new LinkedList<>();
    }

    public LinkedList getQueue() {
        return queue;
    }

    public void setQueue(LinkedList queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return "AssaultQueue{" +
                "queue=" + queue +
                '}';
    }


    public void addRecruit(String name) {
        queue.addLast(name);
    }

    public String retreatCoward() {
       return queue.removeFirst().toString();
    }

    public void printQueue() {
        System.out.println(queue);
    }
}
