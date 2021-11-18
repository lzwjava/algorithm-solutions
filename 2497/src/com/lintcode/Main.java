package com.lintcode;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    private static PrintStream printStream;
    private static int bankMoney = 0;

    public static int saveOperation(int account, int amount) throws Exception{
        if(bankMoney != account) {
            Exception exception = new Exception("Don't cheat!\nYour money is " + account + ". The real money is " + bankMoney + ".");
            throw exception;
        }
        bankMoney += amount;
        return bankMoney;
    }

    public static int withdrawOperation(int account, int amount) throws Exception{
        if(bankMoney != account) {
            Exception exception = new Exception("Don't cheat!\nYour money is " + account + ". The real money is " + bankMoney + ".");
            throw exception;
        }
        if(bankMoney < amount){
            Exception exception = new Exception("Money" + bankMoney + " in bank is lowwer than what you want to withdraw(" + account + ").");
            throw exception;
        }
        bankMoney -= amount;
        return bankMoney;
    }

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            printStream = new PrintStream(outputPath);
            List<Integer> saveMoneyList = new LinkedList<Integer>();
            List<Integer> withdrawMoneyList = new LinkedList<Integer>();
            int initialBankMoney = 0;
            while(in.hasNext()) {
                String operation = in.nextLine();
                operation = operation.substring(0, operation.length() - 1);
                String[] operations = operation.split("\\(");
                if (operations[0].equals("save")) {
                    saveMoneyList.add(Integer.parseInt(operations[1]));
                } else if (operations[0].equals("withdraw")) {
                    withdrawMoneyList.add(Integer.parseInt(operations[1]));
                } else if (operations[0].equals("Bank") && Integer.parseInt(operations[1]) != 0) {
                    initialBankMoney = Integer.parseInt(operations[1]);
                    bankMoney = Integer.parseInt(operations[1]);
                }
            }
            final Bank bank = new Bank(initialBankMoney);
            Thread[] threads = new Thread[2];
            threads[0] = new Thread(() -> {
                try {
                    for(int i = 0; i < saveMoneyList.size(); i++) {
                        bank.saveMoney(saveMoneyList.get(i));
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[1] = new Thread(() -> {
                try {
                    for(int i = 0; i < withdrawMoneyList.size(); i++) {
                        bank.withdrawMoney(withdrawMoneyList.get(i));
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
            for(int i = 0; i < 2; i++) {
                threads[i].start();
            }
            for(int i = 0; i < 2; i++) {
                threads[i].join();
            }
            if( bankMoney != bank.checkAccount()) {
                printStream.write(("Don't cheat!\\nYour money is " + String.valueOf(bank.checkAccount()) + ".\nThe real money is " + bankMoney + ".").getBytes("Utf-8"));
            }
            printStream.write(String.valueOf(bankMoney).getBytes("Utf-8"));
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}