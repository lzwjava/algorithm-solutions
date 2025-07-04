package com.lzw.solutions.uva.p440;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int m = 0;
            for (m = 1; ; m++) {
                LinkedList<Integer> list = new LinkedList<>();
                for (int i = 1; i <= n; i++) {
                    list.add(i);
                }
                int p = 0;
                while (list.size() > 1) {
                    list.remove(p);
                    p = (p + m - 1) % list.size();
                }
                if (list.getFirst() == 2) {
                    break;
                }
            }
            out.append(String.format("%d\n", m));
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
