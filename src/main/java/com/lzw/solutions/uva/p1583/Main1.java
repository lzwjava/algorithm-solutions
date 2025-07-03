package com.lzw.solutions.uva.p1583;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        if (System.getProperty("os.name").equals("Mac OS X")) {
            inStream = new FileInputStream("1.in");
            System.setIn(inStream);
        }

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int j;
            String nStr = String.format("%d", n);
            int length = nStr.length();
            int min = n - length * 9;
            for (j = min; j < n; j++) {
                String s = String.format("%d", j);
                int digitSum = 0;
                for (int k = 0; k < s.length(); k++) {
                    int digit = s.charAt(k) - '0';
                    digitSum += digit;
                }
                if (j + digitSum == n) {
                    System.out.println(j);
                    break;
                }
            }
            if (j == n) {
                System.out.println(0);
            }
        }
        sc.close();

        if (inStream != null) {
            inStream.close();
        }
    }
}
