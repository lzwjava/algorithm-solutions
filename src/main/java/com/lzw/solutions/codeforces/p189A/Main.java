package com.lzw.solutions.codeforces.p189A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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

    int n;
    List<Integer> a;
    int max;

    void cal(int cur, int s, int m) {
        if (m + s / a.get(cur) < max) {
            return;
        }
        if (cur == a.size() - 1) {
            if (s % a.get(cur) != 0) {
                return;
            }
            int v = s / a.get(cur);
            if (m + v > max) {
                max = m + v;
            }
            return;
        }

        int k = s / a.get(cur);

        for (int i = k; i >= 0; i--) {
            if (s >= a.get(cur) * i) {
                int ns = s - a.get(cur) * i;
                cal(cur + 1, ns, m + i);
            }
        }
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            int v = Integer.parseInt(st.nextToken());
            set.add(v);
        }
        a = new ArrayList<>(set);
        Collections.sort(a);
        max = -1;
        cal(0, n, 0);
        out.append(String.format("%d\n", max));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
