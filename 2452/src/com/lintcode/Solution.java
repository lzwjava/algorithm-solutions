package com.lintcode;

import java.util.function.IntConsumer;

public class Solution {
    private Object lock;
    private int i;

    Solution() {
        lock = new Object();
    }

    public void printNumberInMainSubThread(int n, IntConsumer intConsumer) throws InterruptedException {
        this.i = 1;
        Thread thread = new Thread(() -> {
            try {
                synchronized (lock) {
                    while (i <= n) {
                        int j = i % 30;
                        if (j > 10 && j < 30 || j == 0) {
                            intConsumer.accept(i);
                            i++;
                            lock.notifyAll();
                        } else {
                            lock.wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        synchronized (lock) {
            while (i <= n) {
                int j = i % 30;
                if (j >= 1 && j <= 10) {
                    intConsumer.accept(i);
                    i++;
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }

    }
}
