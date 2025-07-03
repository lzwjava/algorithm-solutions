package com.lzw.solutions.lintcode.p2473;

public class ThreadSafeHashMap extends AbstractHashMap {

    public ThreadSafeHashMap(int baseSize) {
        super(baseSize);
    }

    public void put(int key, int value) {
        synchronized (this) {
            super.put(key, value);
        }
    }

    public int get(int index) {
        synchronized (this) {
            return super.get(index);
        }
    }
}