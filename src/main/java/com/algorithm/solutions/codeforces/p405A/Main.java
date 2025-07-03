package com.algorithm.solutions.codeforces.p405A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] as = new int[n];
        int row = 0;
        for (int i = 0; i < n; i++) {
            as[i] = in.nextInt();
            if (as[i] > row) {
                row = as[i];
            }
        }
        int[][] grid = new int[row][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < as[i]; j++) {
                grid[j][i] = 1;
            }
        }
        for (int i = 0; i < row; i++) {
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    c++;
                }
            }
            if (c > 0) {
                Arrays.fill(grid[i], 0);
                int p = 0;
                for (int j = n - 1; j >= 0; j--) {
                    grid[i][j] = 1;
                    p++;
                    if (p == c) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                System.out.print(' ');
            }
            int c = 0;
            for (int j = 0; j < row; j++) {
                if (grid[j][i] == 1) {
                    c++;
                }
            }
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
