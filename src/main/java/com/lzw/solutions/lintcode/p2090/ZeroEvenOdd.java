package com.lzw.solutions.lintcode.p2090;

import java.util.function.IntConsumer;

class ZeroEvenOdd {

    private int n;
    private int zeroCnt;
    private int positiveCnt;
    private int i;
    private Object lock;
    private boolean finished;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.zeroCnt = 0;
        this.positiveCnt = 0;
        this.i = 0;
        lock = new Object();
        this.finished = false;
    }

    public void zero(IntConsumer printZero) throws InterruptedException {
        synchronized (lock) {
            while (!finished) {
                while (zeroCnt > positiveCnt && !finished) {
                    lock.wait();
                }
                if (finished) {
                    break;
                }
                printZero.accept(0);
                zeroCnt++;
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printEven) throws InterruptedException {
        synchronized (lock) {
            while (!finished) {
                while ((positiveCnt >= zeroCnt || i % 2 == 0) && !finished) {
                    lock.wait();
                }
                if (finished) {
                    break;
                }
                printEven.accept(++i);
                if (i == n) {
                    finished = true;
                }
                positiveCnt++;
                lock.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printOdd) throws InterruptedException {
        synchronized (lock) {
            while (!finished) {
                while ((positiveCnt >= zeroCnt || i % 2 != 0) && !finished) {
                    lock.wait();
                }
                if (finished) {
                    break;
                }
                printOdd.accept(++i);
                if (i == n) {
                    finished = true;
                }
                positiveCnt++;
                lock.notifyAll();
            }
        }
    }
}
