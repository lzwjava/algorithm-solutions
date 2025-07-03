package com.lzw.solutions.codeforces.p1335B;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    int n, a, b;

    boolean permutation(char[] chs, int cur) {
        int si = cur - a;
        if (si < 0) {
            si = 0;
        }
        Set<Character> set = new HashSet<>();
        for (int j = si; j < cur; j++) {
            set.add(chs[j]);
        }
        if ((cur >= a && set.size() != b) || set.size() > b || (cur < a && set.size() + a - cur < b)) {
            return false;
        }
        if (cur == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(chs[i]);
            }
            String s = sb.toString();
            System.out.println(s);
            return true;
        }
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            chs[cur] = c;
            boolean ok = permutation(chs, cur + 1);
            if (ok) {
                return true;
            }
        }
        return false;
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            n = in.nextInt();
            a = in.nextInt();
            b = in.nextInt();
            char[] chs = new char[n];
            permutation(chs, 0);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
