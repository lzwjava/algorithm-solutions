package com.algorithm.solutions.codeforces.p1462C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            int x = Integer.parseInt(in.readLine());
            if (x > 45) {
                out.append("-1\n");
            } else {
                int p = 9;
                List<Integer> list = new ArrayList<>();
                while (x > 0) {
                    if (x >= p) {
                        list.add(p);
                        x -= p;
                        p--;
                    } else {
                        list.add(x);
                        x -= x;
                    }
                }
                Collections.sort(list);
                StringBuilder sb = new StringBuilder();
                for (int v : list) {
                    sb.append(String.format("%d", v));
                }
                out.append(String.format("%s\n", sb));
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}