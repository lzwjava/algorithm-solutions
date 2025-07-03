package com.lzw.solutions.lintcode.p2450;

import java.util.function.IntConsumer;

public class Solution {
    public void printHelloWorld(IntConsumer intConsumer) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                intConsumer.accept(0);
            }
        });
        thread.start();
        thread.join();
    }
}