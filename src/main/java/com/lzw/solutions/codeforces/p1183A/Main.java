package com.lzw.solutions.codeforces.p1183A;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    int sumOfDigits(int x) {
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

    void solve() throws IOException {
        int a = Integer.parseInt(in.readLine());
        while (true) {
            int s = sumOfDigits(a);
            if (s % 4 == 0) {
                break;
            }
            a++;
        }
        out.append(String.format("%d\n", a));
    }
}
