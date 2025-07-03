package com.lzw.solutions.uva.p10550;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int p = sc.nextInt();
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            int c3 = sc.nextInt();
            if (p == 0 && c1 == 0 && c2 == 0 && c3 == 0) {
                break;
            }
            int degree = 1080 + ((p - c1 + 40) % 40 + (c2 - c1 + 40) % 40 + (c2 - c3 + 40) % 40) * 9;
            System.out.println(degree);
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
