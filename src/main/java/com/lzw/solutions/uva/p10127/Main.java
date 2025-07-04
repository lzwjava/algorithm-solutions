package com.lzw.solutions.uva.p10127;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = 0;
            try {
                n = sc.nextInt();
            } catch (NoSuchElementException e) {
                break;
            }
            if (n == 0) {
                System.out.println(0);
                continue;
            }
            int mods[] = new int[10000];
            mods[0] = 1 % n;
            for (int i = 1; i < 10000; i++) {
                mods[i] = (mods[i - 1] * 10) % n;
            }
            int p = 0;
            int i;
            for (i = 0; ; i++) {
                p = (p + mods[i]) % n;
                if (p == 0) {
                    break;
                }
            }
            // System.out.println(n);
            System.out.println(i + 1);
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

        new Main().solve();
    }
}
