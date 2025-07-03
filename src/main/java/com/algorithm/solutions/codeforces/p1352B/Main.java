package com.algorithm.solutions.codeforces.p1352B;

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
            int k = Integer.parseInt(st.nextToken());
            int[] a = new int[k];
            if (n % 2 == 1 && k % 2 == 0) {
                out.append("NO\n");
            } else {
                int f;
                if (n % 2 == 0) {
                    if (k % 2 == 0) {
                        f = (int) Math.floor(n * 1.0 / k);
                    } else {
                        f = (int) Math.floor(n * 1.0 / k);
                        if (f % 2 != 0) {
                            f--;
                        }
                    }
                } else {
                    // n%2=1, k%2=1
                    f = (int) Math.floor(n * 1.0 / k);
                    if (f % 2 == 0) {
                        f--;
                    }
                }
                for (int i = 0; i < k - 1; i++) {
                    a[i] = f;
                }
                a[k - 1] = n - (k - 1) * f;
                if (f <= 0 || a[k - 1] <= 0) {
                    out.append("NO\n");
                } else {
                    out.append("YES\n");
                    for (int i = 0; i < k; i++) {
                        if (i != 0) {
                            out.append(' ');
                        }
                        out.append(String.format("%d", a[i]));
                    }
                    out.append('\n');
                }
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