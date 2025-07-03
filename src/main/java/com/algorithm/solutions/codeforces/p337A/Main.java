package com.algorithm.solutions.codeforces.p337A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] fs = new int[m];
        for (int i = 0; i < m; i++) {
            fs[i] = in.nextInt();
        }
        Arrays.sort(fs);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= m - n; i++) {
            int j = i + n - 1;
            int v = fs[j] - fs[i];
            if (v < min) {
                min = v;
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
