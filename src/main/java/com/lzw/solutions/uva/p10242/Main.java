package com.lzw.solutions.uva.p10242;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String readLine() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                return null;
            }
            line = line.trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
    }

    void solve1() throws IOException {
        while (true) {
            String s = readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            double x1, y1, x2, y2, x3, y3, x4, y4;
            x1 = Double.parseDouble(st.nextToken());
            y1 = Double.parseDouble(st.nextToken());
            x2 = Double.parseDouble(st.nextToken());
            y2 = Double.parseDouble(st.nextToken());
            x3 = Double.parseDouble(st.nextToken());
            y3 = Double.parseDouble(st.nextToken());
            x4 = Double.parseDouble(st.nextToken());
            y4 = Double.parseDouble(st.nextToken());
            int p = 0;
            while (true) {
                if (Double.compare(x2, x3) == 0 && Double.compare(y2, y3) == 0) {
                    break;
                }
                double t;
                if (p % 2 == 0) {
                    t = x1;
                    x1 = x2;
                    x2 = t;

                    t = y1;
                    y1 = y2;
                    y2 = t;
                } else if (p % 2 == 1) {
                    t = x3;
                    x3 = x4;
                    x4 = t;

                    t = y3;
                    y3 = y4;
                    y4 = t;
                }
                p++;
            }
            double x = x4 - (x2 - x1);
            double y = y4 - (y2 - y1);
            out.append(String.format("%.3f %.3f\n", x, y));
        }
    }

    void solve() throws IOException {
        while (true) {
            String s = readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            double x1, y1, x2, y2, x3, y3, x4, y4;
            x1 = Double.parseDouble(st.nextToken());
            y1 = Double.parseDouble(st.nextToken());
            x2 = Double.parseDouble(st.nextToken());
            y2 = Double.parseDouble(st.nextToken());
            x3 = Double.parseDouble(st.nextToken());
            y3 = Double.parseDouble(st.nextToken());
            x4 = Double.parseDouble(st.nextToken());
            y4 = Double.parseDouble(st.nextToken());
            int p = 0;
            while (true) {
                if (Double.compare(x2, x3) == 0 && Double.compare(y2, y3) == 0) {
                    break;
                }
                double t;
                if (p % 2 == 0) {
                    t = x1;
                    x1 = x2;
                    x2 = t;

                    t = y1;
                    y1 = y2;
                    y2 = t;
                } else if (p % 2 == 1) {
                    t = x3;
                    x3 = x4;
                    x4 = t;

                    t = y3;
                    y3 = y4;
                    y4 = t;
                }
                p++;
            }
            double k1 = 0, b1 = 0, k2 = 0, b2 = 0;
            if (Double.compare(x1, x2) != 0) {
                k1 = (y2 - y1) / (x2 - x1);
                b1 = y1 - k1 * x1;
            }
            if (Double.compare(x3, x4) != 0) {
                k2 = (y4 - y3) / (x4 - x3);
                b2 = y3 - k2 * x3;
            }
            if (Double.compare(x1, x2) != 0 && Double.compare(x3, x4) != 0) {
                // k1, x4,y4
                double ub = y4 - k1 * x4;
                // k2, x1, y1
                double vb = y1 - k2 * x1;

                // k1*x+ub = k2*x+vb
                double x = (ub - vb) / (k2 - k1);
                double y = k1 * x + ub;
                out.append(String.format("%.3f %.3f\n", x, y));
            } else if (Double.compare(x1, x2) == 0 && Double.compare(x3, x4) != 0) {
                // x = x1
                double x = x4;
                // y = k2 * x + vb
                // x1, y1
                // y1 = k2 * x1 + vb
                double vb = y1 - k2 * x1;
                double y = k2 * x + vb;
                out.append(String.format("%.3f %.3f\n", x, y));
            } else if (Double.compare(x1, x2) != 0 && Double.compare(x3, x4) == 0) {
                // x = x1
                double x = x1;
                double ub = y4 - k1 * x4;
                double y = k1 * x + ub;
                out.append(String.format("%.3f %.3f\n", x, y));
            }
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
