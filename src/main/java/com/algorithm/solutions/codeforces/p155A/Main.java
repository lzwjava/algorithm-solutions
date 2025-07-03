package com.algorithm.solutions.codeforces.p155A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int min = 0, max = 0;
        int amazing = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if (i == 0) {
                min = max = v;
            } else {
                if (v > max || v < min) {
                    amazing++;
                }
                max = Integer.max(max, v);
                min = Integer.min(min, v);
            }
        }
        System.out.println(amazing);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
