package com.lzw.solutions.uva.p378;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        out.append("INTERSECTING LINES OUTPUT\n");
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x1, y1, x2, y2, x3, y3, x4, y4;
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            x3 = Integer.parseInt(st.nextToken());
            y3 = Integer.parseInt(st.nextToken());
            x4 = Integer.parseInt(st.nextToken());
            y4 = Integer.parseInt(st.nextToken());
            double k1 = 0, b1 = 0, k2 = 0, b2 = 0;
            if (x1 != x2) {
                k1 = (y2 - y1) * 1.0 / (x2 - x1);
                b1 = y1 - k1 * x1;
            }
            if (x3 != x4) {
                k2 = (y4 - y3) * 1.0 / (x4 - x3);
                b2 = y3 - k2 * x3;
            }
            if (x1 != x2 && x3 != x4) {
                if (Double.compare(k1, k2) == 0) {
                    if (Double.compare(b1, b2) == 0) {
                        out.append("LINE\n");
                    } else {
                        out.append("NONE\n");
                    }
                } else {
                    double x = (b1 - b2) / (k2 - k1);
                    double y = k1 * x + b1;
                    out.append(String.format("POINT %.2f %.2f\n", x, y));
                }
            } else if (x1 == x2 && x3 != x4) {
                // x = x1
                double x = x1;
                double y = k2 * x + b2;
                out.append(String.format("POINT %.2f %.2f\n", x, y));
            } else if (x1 != x2 && x3 == x4) {
                double x = x3;
                double y = k1 * x + b1;
                out.append(String.format("POINT %.2f %.2f\n", x, y));
            } else {
                if (x1 == x3) {
                    out.append("LINE\n");
                } else {
                    out.append("NONE\n");
                }
            }
            t--;
        }
        out.append("END OF OUTPUT\n");
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
