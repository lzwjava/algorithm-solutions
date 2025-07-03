package com.lzw.solutions.uva.p11150;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int s = 0;
            while (n > 0) {
                if (n >= 3) {
                    int a = n / 3;
                    s += a * 3;
                    n -= a * 3;
                    n += a;
                } else if (n == 2) {
                    s += (n + 1);
                    n -= 2;
                } else if (n == 1) {
                    s += 1;
                    n -= 1;
                }
            }
            System.out.println(s);
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
