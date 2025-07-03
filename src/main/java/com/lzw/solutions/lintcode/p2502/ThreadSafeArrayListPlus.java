package com.lzw.solutions.lintcode.p2502;

public class ThreadSafeArrayListPlus extends AbstractArrayList {

    private Object lock = new Object();

    public ThreadSafeArrayListPlus() {
        super();
    }

    public void append(int element) {
        synchronized (lock) {
            super.append(element);
        }
    }

    public int get(int index) {
        synchronized (lock) {
            return super.get(index);
        }
    }
}
