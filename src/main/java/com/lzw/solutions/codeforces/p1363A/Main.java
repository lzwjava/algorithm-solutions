package com.lzw.solutions.codeforces.p1363A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            int x = Integer.parseInt(st.nextToken());
            int[] a = new int[n];
            st = new StringTokenizer(in.readLine());
            int c0 = 0, c1 = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                if (a[i] % 2 == 0) {
                    c0++;
                } else {
                    c1++;
                }
            }
            boolean ok;
            if (x == n) {
                ok = c1 % 2 == 1;
            } else {
                int start;
                if (x % 2 == 0) {
                    start = Integer.min(x - 1, c1);
                } else {
                    start = Integer.min(x, c1);
                }
                ok = false;
                for (int odd = start; odd >= 1; odd--) {
                    if (odd % 2 == 1 && odd + c0 >= x) {
                        ok = true;
                        break;
                    }
                }
            }
            if (ok) {
                out.append("Yes\n");
            } else {
                out.append("No\n");
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
