package com.lzw.solutions.codeforces.p1354B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    int cal(String s, int i, int j) {
        int len = j - i + 1;
        if (len <= 2) {
            return Integer.MAX_VALUE;
        }
        if (len == 3) {
            Set<Character> set = new HashSet<>();
            for (int k = i; k <= j; k++) {
                set.add(s.charAt(k));
            }
            if (set.size() == 3) {
                return 3;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        int mid = (i + j) / 2;
        int v1 = cal(s, i, mid);
        int v2 = cal(s, mid + 1, j);
        char c = s.charAt(mid);
        String origin = "123";
        List<Character> missing = new ArrayList<>();
        for (char oc : origin.toCharArray()) {
            if (oc != c) {
                missing.add(oc);
            }
        }
        Pos p0 = missingIndex(s, i, j, mid, missing.get(0));
        Pos p1 = missingIndex(s, i, j, mid, missing.get(1));
        int d = dist(p0, p1, mid);
        int min = Integer.min(v1, v2);
        min = Integer.min(min, d);
        return min;
    }

    int dist(Pos p0, Pos p1, int mid) {
        int d = Integer.MAX_VALUE;
        if (p0.l != -1 && p1.r != -1) {
            d = Integer.min(d, p1.r - p0.l + 1);
        }
        if (p0.r != -1 && p1.l != -1) {
            d = Integer.min(d, p0.r - p1.l + 1);
        }
        if (p0.l != -1 && p1.l != -1) {
            int m1 = Integer.min(p0.l, p1.l);
            d = Integer.min(d, mid - m1 + 1);
        }
        if (p0.r != -1 && p1.r != -1) {
            int m1 = Integer.max(p0.r, p1.r);
            d = Integer.min(d, m1 - mid + 1);
        }
        return d;
    }

    Pos missingIndex(String s, int i, int j, int mid, char c) {
        int r = -1;
        for (r = mid + 1; r <= j; r++) {
            if (s.charAt(r) == c) {
                break;
            }
        }
        if (r > j) {
            r = -1;
        }
        int l = -1;
        for (l = mid - 1; l >= i; l--) {
            if (s.charAt(l) == c) {
                break;
            }
        }
        if (l < i) {
            l = -1;
        }
        return new Pos(l, r);
    }

    class Pos {
        int l, r;

        Pos(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            String s = in.readLine();
            int ans = cal(s, 0, s.length() - 1);
            if (ans == Integer.MAX_VALUE) {
                ans = 0;
            }
            out.append(String.format("%d\n", ans));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}