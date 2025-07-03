package com.lzw.solutions.uva.p108;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    void work() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[][] = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            int x = i / n;
            int y = i % n;
            nums[x][y] = sc.nextInt();
        }
        int sum[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int s = 0;
                if (i - 1 >= 0) {
                    s += sum[i - 1][j];
                }
                if (j - 1 >= 0) {
                    s += sum[i][j - 1];
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    s -= sum[i - 1][j - 1];
                }
                s += nums[i][j];
                sum[i][j] = s;
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int w = 1; w <= n - x; w++) {
                    for (int h = 1; h <= n - y; h++) {
                        if (x == 0 && y == 0 && w == n && h == n) {
                            continue;
                        }
                        // [x, x+w-1]  [y, y+h-1]

                        // [0,x-1], [y,y+h-1]
                        int topRightSum = 0;
                        if (x >= 1) {
                            topRightSum += sum[x - 1][y + h - 1];
                            if (y >= 1) {
                                topRightSum -= sum[x - 1][y - 1];
                            }
                        }
                        // bottom left sum
                        // [x, x+w-1], [0, y-1]
                        int bottomLeftSum = 0;
                        if (y >= 1) {
                            bottomLeftSum += sum[x + w - 1][y - 1];
                            if (x >= 1) {
                                bottomLeftSum -= sum[x - 1][y - 1];
                            }
                        }

                        int s = sum[x + w - 1][y + h - 1];
                        s = s - topRightSum - bottomLeftSum;
                        if (x >= 1 && y >= 1) {
                            s -= sum[x - 1][y - 1];
                        }
                        if (s > maxSum) {
                            maxSum = s;
                        }
                    }
                }
            }
        }
        System.out.println(maxSum);
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
