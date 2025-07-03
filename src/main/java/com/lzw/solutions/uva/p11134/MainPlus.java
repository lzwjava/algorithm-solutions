package com.lzw.solutions.uva.p11134;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] xl, yl, xr, yr;
    int n;
    boolean found;

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    void permutation(Point[] ps, int i) {
        if (found) {
            return;
        }
        if (i == n) {
            found = true;
            for (int j = 0; j < n; j++) {
                Point p = ps[j];
                out.append(String.format("%d %d\n", p.x + 1, p.y + 1));
            }
            return;
        }
        for (int x = xl[i]; x <= xr[i]; x++) {
            for (int y = yl[i]; y <= yr[i]; y++) {
                ps[i] = new Point(x, y);
                if (check(ps, i + 1)) {
                    permutation(ps, i + 1);
                }
            }
        }
    }

    boolean check(Point[] ps, int m) {
        Point last = ps[m - 1];
        for (int i = 0; i < m - 1; i++) {
            Point p = ps[i];
            if (p.x == last.x || p.y == last.y) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            xl = new int[n];
            yl = new int[n];
            xr = new int[n];
            yr = new int[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                xl[i] = Integer.parseInt(st.nextToken()) - 1;
                yl[i] = Integer.parseInt(st.nextToken()) - 1;
                xr[i] = Integer.parseInt(st.nextToken()) - 1;
                yr[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            Point[] ps = new Point[n];
            found = false;
            permutation(ps, 0);
            if (!found) {
                out.append("IMPOSSIBLE\n");
            }
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        MainPlus m = new MainPlus();
        m.solve();
        m.close();
    }
}