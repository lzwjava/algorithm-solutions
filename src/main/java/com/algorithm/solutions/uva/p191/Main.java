package com.algorithm.solutions.uva.p191;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean moreOrEq(double a, double b) {
        return Double.compare(a, b) >= 0;
    }

    boolean lessOrEq(double a, double b) {
        return Double.compare(a, b) <= 0;
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            double x1, y1, x2, y2, xleft, ytop, xright, ybottom;
            x1 = Double.parseDouble(st.nextToken());
            y1 = Double.parseDouble(st.nextToken());
            x2 = Double.parseDouble(st.nextToken());
            y2 = Double.parseDouble(st.nextToken());

            if (y1 < y2) {
                double t = x1;
                x1 = x2;
                x2 = t;

                t = y1;
                y1 = y2;
                y2 = t;
            }

            xleft = Double.parseDouble(st.nextToken());
            ytop = Double.parseDouble(st.nextToken());
            xright = Double.parseDouble(st.nextToken());
            ybottom = Double.parseDouble(st.nextToken());

            if (xright < xleft) {
                double t = xleft;
                xleft = xright;
                xright = t;
            }

            if (ytop < ybottom) {
                double t = ytop;
                ytop = ybottom;
                ybottom = t;
            }

            boolean intersect = false;

            if (Double.compare(x1, x2) != 0) {
                if (moreOrEq(x1, xleft) && lessOrEq(x1, xright) && moreOrEq(y1, ybottom) && lessOrEq(y1, ytop)) {
                    intersect = true;
                }
                if (!intersect) {
                    if (moreOrEq(x2, xleft) && lessOrEq(x2, xright) && moreOrEq(y2, ybottom) && lessOrEq(y2, ytop)) {
                        intersect = true;
                    }
                }
                if (!intersect) {
                    double k = (y1 - y2) / (x1 - x2);
                    double b = y1 - k * x1;

                    double y = xleft * k + b;
                    if (moreOrEq(y, ybottom) && lessOrEq(y, ytop) && lessOrEq(y, y1) && moreOrEq(y, y2)) {
                        intersect = true;
                    }

                    if (!intersect) {
                        y = xright * k + b;
                        if (moreOrEq(y, ybottom) && lessOrEq(y, ytop) && lessOrEq(y, y1) && moreOrEq(y, y2)) {
                            intersect = true;
                        }
                    }

                    double xmin = Math.min(x1, x2);
                    double xmax = Math.max(x1, x2);

                    if (!intersect) {
                        double x = (ytop - b) / k;
                        if (moreOrEq(x, xleft) && lessOrEq(x, xright) &&
                            moreOrEq(x, xmin) && lessOrEq(x, xmax)) {
                            intersect = true;
                        }
                    }

                    if (!intersect) {
                        double x = (ybottom - b) / k;
                        if (moreOrEq(x, xleft) && lessOrEq(x, xright) && moreOrEq(x, xmin) && lessOrEq(x, xmax)) {
                            intersect = true;
                        }
                    }
                }
            } else {
                if (moreOrEq(x1, xleft) && lessOrEq(x1, xright)) {
                    if (moreOrEq(y1, ytop) && lessOrEq(y2, ytop) ||
                        moreOrEq(y1, ybottom) && lessOrEq(y2, ybottom) ||
                        lessOrEq(y1, ytop) && moreOrEq(y2, ybottom)) {
                        intersect = true;
                    }
                }
            }
            if (intersect) {
                out.append("T\n");
            } else {
                out.append("F\n");
            }
            n--;
        }
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
