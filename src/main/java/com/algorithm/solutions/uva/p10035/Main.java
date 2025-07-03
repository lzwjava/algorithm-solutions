package com.algorithm.solutions.uva.p10035;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    
    int add(String a, String b) {
        if (a.length() > b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }
        int alen = a.length();
        int blen = b.length();
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i;
        int carryCount = 0;
        for (i = 0; i < blen; i++) {
            int sum = carry;
            if (alen - 1 - i >= 0) {
                char cha = a.charAt(alen - 1 - i);
                sum += (cha - '0');
            }
            char chb = b.charAt(blen - 1 - i);
            sum += (chb - '0');
            if (sum >= 10) {
                carry = 1;
                sum = sum % 10;
                carryCount++;
            } else {
                carry = 0;
            }
            sb.append((char) ('0' + sum));
        }
        if (carry > 0) {
            sb.append("1");
        }
        return carryCount;
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String a = sc.next();
            String b = sc.next();
            if (a.equals("0") && b.equals("0")) {
                break;
            }
            int carryCount = add(a, b);
            if (carryCount == 0) {
                System.out.println("No carry operation.");                
            } else if (carryCount == 1) {
                System.out.println(String.format("%d carry operation.", carryCount));                
            } else {
                System.out.println(String.format("%d carry operations.", carryCount));
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
