package com.lintcode;

import java.util.*;

public class BoundedBlockingStack {
    private Stack<Integer> stack;
    private int capacity;
    private Object lock;

    public BoundedBlockingStack(int capacity) {
        stack = new Stack<>();
        this.capacity = capacity;
        lock = new Object();
    }

    public void push(int element) {
        synchronized (lock) {
            try {
                while (stack.size() >= capacity) {
                    lock.wait();
                }
                stack.add(element);
                lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int pop() {
        synchronized (lock) {
            try {
                while (stack.size() == 0) {
                    lock.wait();
                }
                int v = stack.pop();
                lock.notifyAll();
                return v;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public int size() {
        return stack.size();
    }
}