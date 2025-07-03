package com.algorithm.solutions.codeforces.p1585A;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int h = 1;
            boolean die = false;
            for (int i = 0; i < n; i++) {
                if (a[i] == 1) {
                    if (i > 0 && a[i - 1] == 1) {
                        h += 5;
                    } else {
                        h++;
                    }
                } else {
                    if (i > 0 && a[i - 1] == 0) {
                        die = true;
                        break;
                    }
                }
            }
            if (die) {
                System.out.println(-1);
            } else {
                System.out.println(h);
            }
        }
    }

}