package com.lzw.solutions.lintcode.p2497;

import java.util.concurrent.Semaphore;

public class Bank {
    private int account;
    private final Object lock;

    public Bank(int account) {
        this.account = account;
        lock = new Object();
    }

    public void saveMoney(int amount) throws Exception {
        synchronized (lock){
            this.account = Main.saveOperation(account, amount);
            lock.notifyAll();
        }
    }

    public void withdrawMoney(int amount) throws Exception {
        synchronized (lock){
            while (amount > this.account){
                lock.wait();
            }
            this.account = Main.withdrawOperation(account, amount);
        }
    }

    public int checkAccount(){
        return account;
    }

}