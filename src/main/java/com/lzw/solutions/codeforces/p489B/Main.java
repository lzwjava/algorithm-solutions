package com.lzw.solutions.codeforces.p489B;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0, j = 0;
        int cnt = 0;
        while (i < n && j < m) {
            if (Math.abs(a[i] - b[j]) <= 1) {
                cnt++;
                i++;
                j++;
            } else {
                if (a[i] < b[j]) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
