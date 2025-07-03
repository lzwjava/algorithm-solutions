package com.lzw.solutions.uva.p10783;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int caseNum = 1;
        while (t > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int sum = 0;
            for (int i = a; i <= b; i++) {
                if (i % 2 == 1) {
                    sum += i;
                }
            }
            System.out.println(String.format("Case %d: %d", caseNum, sum));

            caseNum++;
            t--;
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
