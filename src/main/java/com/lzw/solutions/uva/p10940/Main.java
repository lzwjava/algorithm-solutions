package com.lzw.solutions.uva.p10940;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int maxn = 500001;
        int[] map = new int[maxn];
        int p = 4;
        map[0] = 0;
        map[1] = 1;
        map[2] = 2;
        map[3] = 2;
        for (int i = 4; i < maxn; i++) {
            int v = map[i - 1] + 2;
            if (map[i - 1] == p) {
                v = 2;
                p *= 2;
            }
            map[i] = v;
        }
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            out.append(String.format("%d\n", map[n]));
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
