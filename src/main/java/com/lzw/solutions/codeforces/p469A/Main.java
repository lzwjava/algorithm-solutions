package com.lzw.solutions.codeforces.p469A;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < p; i++) {
            int v = in.nextInt();
            set.add(v);
        }
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int v = in.nextInt();
            set.add(v);
        }
        if (set.size() == n) {
            System.out.println("I become the guy.");
        } else {
            System.out.println("Oh, my keyboard!");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
