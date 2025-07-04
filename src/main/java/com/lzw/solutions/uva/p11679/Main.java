package com.lzw.solutions.uva.p11679;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair {
        int d, c, v;

        Pair(int d, int c, int v) {
            this.d = d;
            this.c = c;
            this.v = v;
        }
    }

    void solve() throws IOException {
        while (true) {
            int b, n;
            StringTokenizer st = new StringTokenizer(in.readLine());
            b = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (b == 0 && n == 0) {
                break;
            }
            int[] bs = new int[b];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < b; i++) {
                bs[i] = Integer.parseInt(st.nextToken());
            }
            Pair[] pairs = new Pair[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(in.readLine());
                int d, c, v;
                d = Integer.parseInt(st.nextToken()) - 1;
                c = Integer.parseInt(st.nextToken()) - 1;
                v = Integer.parseInt(st.nextToken());
                pairs[i] = new Pair(d, c, v);
            }
            for (int i = 0; i < n; i++) {
                Pair p = pairs[i];
                bs[p.d] -= p.v;
                bs[p.c] += p.v;
            }
            boolean ok = true;
            for (int i = 0; i < b; i++) {
                if (bs[i] < 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.append("S\n");
            } else {
                out.append("N\n");
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
