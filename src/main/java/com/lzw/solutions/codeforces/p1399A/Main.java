package com.lzw.solutions.codeforces.p1399A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int[] as = new int[n];
            for (int i = 0; i < n; i++) {
                as[i] = in.nextInt();
            }
            Arrays.sort(as);
            boolean ok = true;
            for (int i = 0; i < n - 1; i++) {
                if (as[i + 1] - as[i] > 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
