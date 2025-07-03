package com.lzw.solutions.codeforces.p1353B;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                b[i] = in.nextInt();
            }
            Arrays.sort(a);
            Arrays.sort(b);
            for (int i = 0; i < k; i++) {
                int j = n - 1 - i;
                if (a[i] < b[j]) {
                    int p = a[i];
                    a[i] = b[j];
                    b[j] = p;
                } else {
                    break;
                }
            }
            int s = 0;
            for (int i = 0; i < n; i++) {
                s += a[i];
            }
            System.out.println(s);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
