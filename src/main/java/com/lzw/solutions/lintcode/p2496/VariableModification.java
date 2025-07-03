package com.lzw.solutions.lintcode.p2496;

public class VariableModification {
    private int i;
    Object lock;

    public VariableModification() {
        this.i = 0;
        lock = new Object();
    }

    public void add1() throws Exception {
        synchronized (lock) {
            this.i = Main.increase(this.i);
        }
    }

    public void add2() throws Exception {
        synchronized (lock) {
            this.i = Main.increase(this.i);
        }
    }

    public void sub1() throws Exception {
        synchronized (lock) {
            this.i = Main.decrease(this.i);
        }
    }

    public void sub2() throws Exception {
        synchronized (lock) {
            this.i = Main.decrease(this.i);
        }
    }

    public int checkI() {
        return i;
    }
}