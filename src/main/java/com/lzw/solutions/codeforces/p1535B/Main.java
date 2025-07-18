package com.lzw.solutions.codeforces.p1535B;

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

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            a = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                a.add(v);
            }
            Collections.sort(a, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1 % 2, o2 % 2);
                }
            });
            int c = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (gcd(a.get(i), 2 * a.get(j)) > 1) {
                        c++;
                    }
                }
            }
            out.append(String.format("%d\n", c));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
