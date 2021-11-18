package com.lintcode;

public class Bank {
    private int account;
    // write your code

    public Bank(int account) {
        this.account = account;
    }

    public void saveMoney(int amount) throws Exception {
        this.account = Main.saveOperation(account, amount);
    }

    public void withdrawMoney(int amount) throws Exception {
        this.account = Main.withdrawOperation(account, amount);
    }

    public int checkAccount(){
        return account;
    }

}