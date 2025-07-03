package com.lzw.solutions.codeforces.p1154A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int[] xs = new int[4];
        for (int i = 0; i < 4; i++) {
            xs[i] = in.nextInt();
        }
        Arrays.sort(xs);
        int[] as = new int[3];
        for (int i = 0; i < 3; i++) {
            as[i] = xs[3] - xs[i];
        }
        System.out.println(String.format("%d %d %d\n", as[0], as[1], as[2]));
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
