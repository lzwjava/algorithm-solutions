package com.lintcode;

import java.util.function.Consumer;

class H2OMinus {

    private Object lock = new Object();
    private int count = 0;

    public H2OMinus() {

    }

    public void hydrogen(Consumer releseHydrogen)  {
        try {
            synchronized (lock){
                while (count == 2){
                    lock.wait();
                }
                releseHydrogen.accept(0);
                count++;
                lock.notifyAll();
            }
        } catch (InterruptedException e){
        }
    }
    
    public void oxygen(Consumer releseOxygen)  {
        try {
            synchronized (lock){
                while (count !=2){
                    lock.wait();
                }
                releseOxygen.accept(0);
                count=0;
                lock.notifyAll();
            }
        } catch (InterruptedException e){
        }
    }
}