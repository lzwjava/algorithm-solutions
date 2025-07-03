package com.lzw.solutions.uva.p10684;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main1 {

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
            int max = Integer.MIN_VALUE;
            int d[][] = new int[n][n];
            for (int i = 0; i < n; i++) {
                d[i][i] = nums[i];
                if (d[i][i] > max) {
                    max = d[i][i];
                }
            }
            for (int w = 1; w <= n - w; w++) {
                for (int i = 0; i < n - w; i++) {
                    int j = i + w;
                    d[i][j] = Math.max(d[i][j - 1], d[i][j - 1] + nums[j]);
                    if (d[i][j] > max) {
                        max = d[i][j];
                    }
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
