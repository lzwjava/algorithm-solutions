package com.lzw.solutions.codeforces.p742A;

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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int[] nums = new int[] {8, 4, 2, 6};
        int n = Integer.parseInt(in.readLine());
        int ans;
        if (n == 0) {
            ans = 1;
        } else {
            ans = nums[(n - 1) % 4];
        }
        out.append(String.format("%d\n", ans));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
