package com.lzw.solutions.lintcode.p2502;

import java.util.List;
import java.util.concurrent.Semaphore;

public class ThreadSafeArrayList extends AbstractArrayList {

    private Semaphore sa;

    public ThreadSafeArrayList() {
        super();
        sa = new Semaphore(1, true);
    }

    public void append(int element) {
        try {
            sa.acquire();
            super.append(element);
            sa.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int get(int index) {
        try {
            sa.acquire();
            int v = super.get(index);
            sa.release();
            return v;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
    }
}