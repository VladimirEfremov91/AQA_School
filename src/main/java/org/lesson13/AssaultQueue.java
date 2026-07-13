package org.lesson13;

import java.util.LinkedList;

public class AssaultQueue {
    private final LinkedList<String> queue;

    public AssaultQueue() {
        queue = new LinkedList<>();
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
       return queue.removeFirst();
    }

    public void printQueue() {
        System.out.println(queue);
    }
}
