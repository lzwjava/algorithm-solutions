package com.lzw.solutions.codeforces.p723A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int[] xs = new int[3];
        for (int i = 0; i < 3; i++) {
            xs[i] = in.nextInt();
        }
        Arrays.sort(xs);
        int ans = xs[2] - xs[0];
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
