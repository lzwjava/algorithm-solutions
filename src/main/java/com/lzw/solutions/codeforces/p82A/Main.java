package com.lzw.solutions.codeforces.p82A;

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

    int cal(int x) {
        return ((1 << x) - 1) * 5;
    }

    void solve() throws IOException {
        String[] names = new String[]{"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        int n = Integer.parseInt(in.readLine());
        int x = (int) (Math.log(n * 1.0 / 5 + 1) / Math.log(2) - 1);
        while (true) {
            if (cal(x) < n && cal(x + 1) >= n) {
                break;
            }
            x++;
        }
        int d = n - cal(x);
        int a = (d - 1) / (1 << x);
        out.append(String.format("%s\n", names[a]));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}