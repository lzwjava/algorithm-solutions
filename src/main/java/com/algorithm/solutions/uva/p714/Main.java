package com.algorithm.solutions.uva.p714;

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

    int[] p;
    int m, k;

    boolean separate(int x, int[] seps) {
        int s = 0;
        int q = k;
        for (int i = m - 1; i >= 0; i--) {
            if (p[i] > x) {
                return false;
            }
            if (s + p[i] <= x && i >= q - 1) {
                s += p[i];
            } else {
                q--;
                s = p[i];
                if (q <= 0) {
                    break;
                }
                seps[q - 1] = i;
            }
        }
        q--;
        return q >= 0;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            p = new int[m];
            st = new StringTokenizer(in.readLine());
            int sum = 0;
            for (int i = 0; i < m; i++) {
                p[i] = Integer.parseInt(st.nextToken());
                sum += p[i];
            }
            int left = 0, right = sum;
            while (left != right) {
                int mid = (left + right) / 2;
                int[] seps = new int[k - 1];
                boolean ok = separate(mid, seps);
                if (ok) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int[] seps = new int[k - 1];
            separate(left, seps);
            int v = 0;
            for (int i = 0; i < m; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", p[i]));

                if (v < k - 1 && i == seps[v]) {
                    out.append(" /");
                    v++;
                }
            }
            out.append('\n');
            t--;
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