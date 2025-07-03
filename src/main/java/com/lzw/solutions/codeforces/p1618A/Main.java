package com.lzw.solutions.codeforces.p1618A;

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
            int[] b = new int[7];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 7; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(b);
            int x = b[0];
            int y = b[1];
            int z = b[2];
            if (b[0] + b[1] == b[2]) {
                z = b[3];
            }
            out.append(String.format("%d %d %d\n", x, y, z));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}