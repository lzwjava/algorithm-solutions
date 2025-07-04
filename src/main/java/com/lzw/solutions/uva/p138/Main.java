package com.lzw.solutions.uva.p138;

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

    void solve1() throws IOException {
        int t = 0;
        for (int n = 2; n <= 113097680; n++) {
            long s = (long) n * (n + 1) / 2;
            long k = (long) Math.sqrt(s);
            if (k * k == s) {
                out.append(String.format("%10d%10d\n", k, n));
                t++;
                if (t == 10) {
                    break;
                }
            }
        }
    }

    void solve() throws IOException {
        int[] ks = new int[] {6, 35, 204, 1189, 6930, 40391, 235416, 1372105, 7997214, 46611179};
        int[] ns = new int[] {8, 49, 288, 1681, 9800, 57121, 332928, 1940449, 11309768, 65918161};
        for (int i = 0; i < 10; i++) {
            out.append(String.format("%10d%10d\n", ks[i], ns[i]));
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
