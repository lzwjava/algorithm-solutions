package com.algorithm.solutions.uva.p10018;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            long p = sc.nextInt();
            long resultNum = 0;
            int i;
            for (i = 0; i < 1000; i++) {
                String s = String.format("%d", p);
                StringBuilder sb = new StringBuilder(s);
                String rs = sb.reverse().toString();
                if (rs.equals(s)) {
                    resultNum = p;
                    break;
                }
                long rp = Long.parseLong(rs);
                long sum = p + rp;
                p = sum;
            }
            System.out.println(String.format("%d %d", i, resultNum));
            n--;
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
