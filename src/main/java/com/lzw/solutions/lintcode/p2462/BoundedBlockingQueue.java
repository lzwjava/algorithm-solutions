package com.lzw.solutions.lintcode.p2462;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class BoundedBlockingQueue {

    private ArrayBlockingQueue<Integer> queue;
    private int capacity;
    private Object lock;

    public BoundedBlockingQueue(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
        this.capacity = capacity;
        lock = new Object();
    }

    public void enqueue(int element) {
        synchronized (lock) {
            try {
                while (queue.size() >= capacity) {
                    lock.wait();
                }
                queue.add(element);
                lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public int dequeue() {
        synchronized (lock) {
            try {
                while (queue.size() == 0) {
                    lock.wait();
                }
                int v = queue.poll();
                lock.notifyAll();
                return v;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public int size() {
        return queue.size();
    }
}