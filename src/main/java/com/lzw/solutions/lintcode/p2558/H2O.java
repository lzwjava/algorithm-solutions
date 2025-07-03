package com.lzw.solutions.lintcode.p2558;

import java.util.concurrent.Semaphore;
import java.util.function.Consumer;

class H2O {
    Semaphore h, o;

    public H2O() {
        h = new Semaphore(2, true);
        o = new Semaphore(0, true);
    }

    public void hydrogen(Consumer releaseHydrogen) {
        try {
            h.acquire();
            releaseHydrogen.accept(0);
            o.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Consumer releaseOxygen) {
        try {
            o.acquire(2);
            releaseOxygen.accept(0);
            h.release(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
