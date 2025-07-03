package com.lzw.solutions.codeforces.p228A;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int v = in.nextInt();
            set.add(v);
        }
        int ans = 4 - set.size();
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
