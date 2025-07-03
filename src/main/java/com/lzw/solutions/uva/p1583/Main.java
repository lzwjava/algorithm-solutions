package com.lzw.solutions.uva.p1583;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {    
    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;        
        if (System.getProperty("os.name").equals("Mac OS X")) {
            inStream = new FileInputStream("1.in");
            System.setIn(inStream);
        }

        int maxNum = 100000;

        int generators[] = new int[maxNum+1];
        for (int i = 1; i <= maxNum; i++) {
            String s = String.format("%d", i);
            int digitSum = 0;
            for (int k = 0; k < s.length(); k++) {
                int digit = s.charAt(k) - '0';
                digitSum += digit;
            }
            int result = i + digitSum;
            if (result <= maxNum && generators[result] == 0) {
                generators[result] = i;
            }
        }

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            System.out.println(generators[n]);   
        }
        sc.close();

        if (inStream != null) {
            inStream.close();
        }
    }
}
