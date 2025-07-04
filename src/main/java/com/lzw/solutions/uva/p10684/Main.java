package com.lzw.solutions.uva.p10684;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int nums[] = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int max = 0;
            int tmp = 0;
            for (int i = 0; i < n; i++) {
                tmp += nums[i];
                if (tmp > max) {
                    max = tmp;
                }
                if (tmp < 0) {
                    tmp = 0;
                }
            }
            if (max > 0) {
                System.out.println(String.format("The maximum winning streak is %d.", max));
            } else {
                System.out.println("Losing streak.");
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

        new Main().solve();
    }
}
