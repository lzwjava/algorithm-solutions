package com.lzw.solutions.uva.p371;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    HashMap<Long, Integer> lens;

    int calLen(long x) {
        Integer v = lens.get(x);
        if (v != null) {
            return v;
        }
        long nx;
        if (x % 2 == 0) {
            nx = x / 2;
        } else {
            nx = 3 * x + 1;
        }
        int nv;
        if (nx == 1) {
            nv = 1;
        } else {
            nv = 1 + calLen(nx);
        }
        lens.put(x, nv);
        return nv;
    }

    class Query {
        int l;
        int h;

        Query() {}

        Query(int l, int h) {
            this.l = l;
            this.h = h;
        }
    }

    void solve() throws IOException {
        lens = new HashMap<>();
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if (l == 0 && h == 0) {
                break;
            }
            if (l > h) {
                int tmp = l;
                l = h;
                h = tmp;
            }
            int maxLen = 0;
            int maxI = 0;
            for (int i = l; i <= h; i++) {
                int ilen = calLen((long) i);
                if (ilen > maxLen) {
                    maxLen = ilen;
                    maxI = i;
                }
            }
            out.append(String.format(
                    "Between %d and %d, %d generates the longest sequence of %d values.\n", l, h, maxI, maxLen));
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
