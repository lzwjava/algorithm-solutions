package com.lzw.solutions.codeforces.p599A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] d = new int[3];
        for (int i = 0; i < 3; i++) {
            d[i] = Integer.parseInt(st.nextToken());
        }
        int min = Integer.min(2 * d[0] + 2 * d[1], d[0] + d[1] + d[2]);
        min = Integer.min(min, ((d[0] + d[2]) * 2));
        min = Integer.min(min, ((d[1] + d[2]) * 2));
        out.append(String.format("%d\n", min));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}