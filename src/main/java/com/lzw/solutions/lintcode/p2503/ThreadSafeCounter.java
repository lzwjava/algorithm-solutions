package com.lzw.solutions.lintcode.p2503;

public class ThreadSafeCounter {
    private int i;
    private Object lock = new Object();

    public ThreadSafeCounter() {
        this.i = 0;
    }

    public void incr() {
        synchronized (lock) {
            this.i = Main.incr();
        }
    }

    public void decr() {
        synchronized (lock) {
            this.i = Main.decr();
        }
    }

    public int getCount() {
        return i;
    }
}
