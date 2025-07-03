package com.lzw.solutions.codeforces.p1608B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int sum = n - 2;
            int h;
            if (n % 2 == 1) {
                h = sum / 2 + 1;
            } else {
                h = sum / 2;
            }
            if (a + b <= sum && a <= h && b <= h && Math.abs(a - b) <= 1) {
                int c = 0;
                int d = n - 1;
                ArrayList<Integer> res = new ArrayList<>();
                if (a >= b) {
                    res.add(0);
                    c++;
                    while (a > 0 || b > 0) {
                        if (a > 0) {
                            res.add(d);
                            a--;
                            d--;
                        }
                        if (b > 0) {
                            res.add(c);
                            b--;
                            c++;
                        }
                    }
                } else {
                    res.add(n - 1);
                    d--;
                    while (a > 0 || b > 0) {
                        if (b > 0) {
                            res.add(c);
                            b--;
                            c++;
                        }
                        if (a > 0) {
                            res.add(d);
                            a--;
                            d--;
                        }
                    }
                }
                if (res.get(res.size() - 1) == c - 1) {
                    for (int j = c; j <= d; j++) {
                        res.add(j);
                    }
                } else {
                    for (int j = d; j >= c; j--) {
                        res.add(j);
                    }
                }
                for (int i = 0; i < n; i++) {
                    if (i != 0) {
                        out.append(' ');
                    }
                    out.append(String.format("%d", res.get(i) + 1));
                }
                out.append('\n');
            } else {
                out.append("-1\n");
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
