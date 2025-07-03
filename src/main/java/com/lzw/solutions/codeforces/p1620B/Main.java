package com.lzw.solutions.codeforces.p1620B;

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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int w, h;
            StringTokenizer st = new StringTokenizer(in.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            int k1 = Integer.parseInt(st.nextToken());
            int[] xa = new int[k1];
            for (int i = 0; i < k1; i++) {
                xa[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            int k2 = Integer.parseInt(st.nextToken());
            int[] xb = new int[k2];
            for (int i = 0; i < k2; i++) {
                xb[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            int k3 = Integer.parseInt(st.nextToken());
            int[] ya = new int[k3];
            for (int i = 0; i < k3; i++) {
                ya[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            int k4 = Integer.parseInt(st.nextToken());
            int[] yb = new int[k4];
            for (int i = 0; i < k4; i++) {
                yb[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(xa);
            Arrays.sort(xb);
            Arrays.sort(ya);
            Arrays.sort(yb);

            long max = 0;
            if (k1 >= 2) {
                max = Long.max(max, (long) (xa[k1 - 1] - xa[0]) * h);
            }
            if (k2 >= 2) {
                max = Long.max(max, (long) (xb[k2 - 1] - xb[0]) * h);
            }
            if (k3 >= 2) {
                max = Long.max(max, (long) (ya[k3 - 1] - ya[0]) * w);
            }
            if (k4 >= 2) {
                max = Long.max(max, (long) (yb[k4 - 1] - yb[0]) * w);
            }
            out.append(String.format("%d\n", max));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
