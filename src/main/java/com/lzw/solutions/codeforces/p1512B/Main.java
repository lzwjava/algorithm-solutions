package com.lzw.solutions.codeforces.p1512B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

    class Point {
        int i, j;

        Point() {
        }

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            char[][] grid = new char[n][n];
            Point[] ps = new Point[4];
            int p = 0;
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < n; j++) {
                    char c = s.charAt(j);
                    grid[i][j] = c;
                    if (c == '*') {
                        ps[p] = new Point(i, j);
                        p++;
                    }
                }
            }
            ps[2] = new Point();
            ps[3] = new Point();
            if (ps[0].i == ps[1].i) {
                int ni = (ps[0].i + 1) % n;
                ps[2].i = ps[3].i = ni;
                ps[2].j = ps[0].j;
                ps[3].j = ps[1].j;
            } else if (ps[0].j == ps[1].j) {
                int nj = (ps[0].j + 1) % n;
                ps[2].j = ps[3].j = nj;
                ps[2].i = ps[0].i;
                ps[3].i = ps[1].i;
            } else {
                ps[2].i = ps[0].i;
                ps[2].j = ps[1].j;
                ps[3].i = ps[1].i;
                ps[3].j = ps[0].j;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    boolean mark = false;
                    for (int k = 0; k < 4; k++) {
                        if (ps[k].i == i && ps[k].j == j) {
                            mark = true;
                            break;
                        }
                    }
                    if (mark) {
                        out.append('*');
                    } else {
                        out.append('.');
                    }
                }
                out.append('\n');
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}