package com.lzw.solutions.codeforces.p1608A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

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

    Random random = new Random();

    int randomInt(int d, int i) {
        return random.nextInt(d) + d * i;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());

        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            int d = 1000000000 / n;
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    a[i] = randomInt(d, i);
                } else {
                    do {
                        a[i] = randomInt(d, i);
                    } while (a[i] % a[i - 1] == 0 || a[i] <= a[i - 1]);
                }
            }
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", a[i]));
            }
            out.append('\n');
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}