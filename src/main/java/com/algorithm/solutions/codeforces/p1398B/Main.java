package com.algorithm.solutions.codeforces.p1398B;

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
            t--;
            String s = in.readLine();
            s = s + "0";
            List<Integer> ones = new ArrayList<>();
            int p = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    p++;
                } else {
                    if (p > 0) {
                        ones.add(p);
                    }
                    p = 0;
                }
            }
            Collections.sort(ones);
            int on = ones.size();
            int c = 0;
            for (int i = 0; i < on; i += 2) {
                int j = on - 1 - i;
                c += ones.get(j);
            }
            out.append(String.format("%d\n", c));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}