package com.lzw.solutions.uva.p10812;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            int s, d;
            s = sc.nextInt();
            d = sc.nextInt();
            if (s < d || (s + d) % 2 == 1) {
                System.out.println("impossible");
            } else {
                int a = (s + d) / 2;
                int b = (s - d) / 2;
                System.out.println(String.format("%d %d", a, b));
            }
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
    }
}
