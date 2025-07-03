package com.algorithm.solutions.codeforces.p344A;

import java.util.Scanner;

public class Main {

    boolean attract(String a, String b) {
        char ac = a.charAt(1);
        char bc = b.charAt(0);
        return ac == '1' && bc == '0' || ac == '0' && bc == '1';
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = in.next();
        }
        int group = 0;
        String prev = null;
        for (int i = 0; i < n; i++) {
            String s = strs[i];
            if (i == 0) {
                prev = s;
            } else {
                if (attract(prev, s)) {
                    prev = s;
                } else {
                    group++;
                    prev = s;
                }
            }
        }
        if (prev != null) {
            group++;
        }
        System.out.println(group);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
