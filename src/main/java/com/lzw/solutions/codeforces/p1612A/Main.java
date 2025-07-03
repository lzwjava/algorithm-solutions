package com.lzw.solutions.codeforces.p1612A;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    class Point {
        int x, y;

        Point() {
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int dist(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    Point parsePoint(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return new Point(x, y);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            Point a = new Point(0, 0);
            Point b = parsePoint(in.readLine());
            Point c = new Point(-1, -1);
            if (b.x % 2 == 0 && b.y % 2 == 0) {
                c.x = b.x / 2;
                c.y = b.y / 2;
            } else {
                int d = dist(a, b);
                if (d % 2 != 1) {
                    int hd = d / 2;
                    for (int x = 0; x <= b.x; x++) {
                        int d1 = x;
                        // 2*(d1 + d2) = d;
                        int y = hd - d1;
                        if (y >= 0) {
                            Point cc = new Point(x, y);
                            if (dist(a, cc) == dist(b, cc)) {
                                c.x = x;
                                c.y = y;
                                break;
                            }
                        }
                    }
                }
            }
            out.append(String.format("%d %d\n", c.x, c.y));
        }
    }
}