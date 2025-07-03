package com.lzw.solutions.codeforces.p510A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], '.');
        }
        boolean full = true;
        boolean right = true;
        for (int i = 0; i < n; i++) {
            if (full) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = '#';
                }
            } else {
                if (right) {
                    grid[i][m - 1] = '#';
                } else {
                    grid[i][0] = '#';
                }
                right = !right;
            }
            full = !full;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
