package com.algorithm.solutions.uva.p190;

import java.io.*;
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
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            double ax, ay, bx, by, cx, cy;
            ax = Double.parseDouble(st.nextToken());
            ay = Double.parseDouble(st.nextToken());
            bx = Double.parseDouble(st.nextToken());
            by = Double.parseDouble(st.nextToken());
            cx = Double.parseDouble(st.nextToken());
            cy = Double.parseDouble(st.nextToken());

            double dd[] = new double[3];
            dd[0] = Math.hypot(ax - bx, ay - by);
            dd[1] = Math.hypot(ax - cx, ay - cy);
            dd[2] = Math.hypot(bx - cx, by - cy);
            Arrays.sort(dd);
            double h = 0, k = 0, r = 0;
            boolean ok = false;
            if (Double.compare(Math.hypot(dd[0], dd[1]), dd[2]) == 0) {
                double t;
                int p = 0;
                while (true) {
                    double[] hh = new double[3];
                    hh[0] = Math.hypot(ax - bx, ay - by);
                    hh[1] = Math.hypot(ax - cx, ay - cy);
                    hh[2] = Math.hypot(bx - cx, by - cy);
                    if (Double.compare(Math.hypot(hh[0], hh[1]), hh[2]) == 0) {
                        break;
                    }
                    if (p % 2 == 0) {
                        t = ax;
                        ax = bx;
                        bx = t;

                        t = ay;
                        ay = by;
                        by = t;
                    } else {
                        t = ax;
                        ax = cx;
                        cx = t;

                        t = ay;
                        ay = cy;
                        cy = t;
                    }
                    p++;
                }
                if (Double.compare(ax, bx) == 0 || Double.compare(ay, by) == 0) {
                    double mx1 = (ax + bx) / 2;
                    double my1 = (ay + by) / 2;

                    double mx2 = (ax + cx) / 2;
                    double my2 = (ay + cy) / 2;

                    if (Double.compare(ax, bx) == 0) {
                        h = mx2;
                        k = my1;
                    } else {
                        h = mx1;
                        k = my2;
                    }
                    r = Math.hypot(h - ax, k - ay);
                    ok = true;
                }
            }
            if (!ok) {
                int p = 0;
                double t;
                while (true) {
                    if (Double.compare(ax, bx) != 0 && Double.compare(ax, cx) != 0
                        && Double.compare(ay, by) != 0 && Double.compare(ay, cy) != 0) {
                        break;
                    }
                    if (p % 2 == 0) {
                        t = ax;
                        ax = bx;
                        bx = t;

                        t = ay;
                        ay = by;
                        by = t;
                    } else {
                        t = ax;
                        ax = cx;
                        cx = t;

                        t = ay;
                        ay = cy;
                        cy = t;
                    }
                    p++;
                }
                // y = kx+b
                // ay = k*ax +b
                // by = k*bx+b
                double k1 = (by - ay) / (bx - ax);
                double b1 = ay - k1 * ax;
                double k2 = (cy - ay) / (cx - ax);
                double b2 = ay - k2 * ax;

                double mx1 = (ax + bx) / 2;
                double my1 = (ay + by) / 2;
                double mk1 = -1 / k1;

                // y-my1 = mk1(x-mx1)

                double mx2 = (ax + cx) / 2;
                double my2 = (ay + cy) / 2;
                double mk2 = -1 / k2;

                // y-my2 = mk2(x-mx2)

                double mb1 = -mx1 * mk1 + my1;
                double mb2 = -mx2 * mk2 + my2;

                // y = mk1*x+mb1
                // y = mk2*x+mb2
                h = (mb2 - mb1) / (mk1 - mk2);
                k = mk1 * h + mb1;
                r = Math.hypot(h - ax, k - ay);
            }
            // x^2-2xh+h^2 + y^2-2ky+k^2-r^2 =0
            double c = -2 * h;
            double d = -2 * k;
            double e = r * r - h * h - k * k;
            out.append(String.format("(x %s)^2 + (y %s)^2 = %.3f^2\n", sign(-h), sign(-k), r));
            out.append(String.format("x^2 + y^2 %sx %sy %s = 0\n", sign(c), sign(d), sign(-e)));
            out.append('\n');
        }
    }

    String sign(double x) {
        StringBuilder sb = new StringBuilder();
        if (x < 0) {
            sb.append("-");
        } else {
            sb.append("+");
        }
        sb.append(" ");
        sb.append(String.format("%.3f", Math.abs(x)));
        return sb.toString();
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
//            outStream = new PrintStream("1.out");
            System.setIn(inStream);
//            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
