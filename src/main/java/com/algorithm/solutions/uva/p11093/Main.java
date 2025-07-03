package com.algorithm.solutions.uva.p11093;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        for (int t = 0; t < tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[2 * n];
            int k = 0;
            while (k < 2 * n) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                while (st.hasMoreTokens()) {
                    a[k] = Integer.parseInt(st.nextToken());
                    k++;
                }
            }
            int[] ps = Arrays.copyOfRange(a, 0, n);
            int[] qs = Arrays.copyOfRange(a, n, 2 * n);
            int sum = 0;
            for (int i = 0; i < n; i++) {
                ps[i] -= qs[i];
                sum += ps[i];
            }

            out.append(String.format("Case %d: ", t + 1));
            boolean ok = false;
            if (sum >= 0) {
                for (int i = 0; i < n; i++) {
                    int s = 0;
                    int j;
                    for (j = 0; j < n; j++) {
                        int u = (i + j) % n;
                        s += ps[u];
                        if (s < 0) {
                            break;
                        }
                    }
                    if (j == n) {
                        ok = true;
                        out.append(String.format("Possible from station %d\n", i + 1));
                        break;
                    } else {
                        i += j;
                    }
                }
            }
            if (!ok) {
                out.append("Not possible\n");
            }
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}