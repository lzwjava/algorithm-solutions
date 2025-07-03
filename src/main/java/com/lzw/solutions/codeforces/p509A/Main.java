package com.lzw.solutions.codeforces.p509A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            grid[i][0] = grid[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }
        System.out.println(grid[n - 1][n - 1]);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
