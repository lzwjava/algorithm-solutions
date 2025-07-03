package com.lzw.solutions.codeforces.p1609A;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    Scanner in;
    PrintWriter out;

    Main() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            long[] ns = new long[n];
            for (int i = 0; i < n; i++) {
                long v = in.nextInt();
                ns[i] = v;
            }
            int k = 0;
            for (int i = 0; i < n; i++) {
                while (ns[i] % 2 == 0) {
                    ns[i] /= 2;
                    k++;
                }
            }
            Arrays.sort(ns);
            for (int i = 0; i < k; i++) {
                ns[n - 1] *= 2;
            }
            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += ns[i];
            }
            out.append(String.format("%d\n", ans));
            t--;
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
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
