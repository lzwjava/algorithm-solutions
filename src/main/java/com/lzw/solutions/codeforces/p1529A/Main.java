package com.lzw.solutions.codeforces.p1529A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
            int n = Integer.parseInt(in.readLine());
            List<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                list.add(v);
            }
            for (; ; ) {
                int s = 0;
                int m = list.size();
                for (int i = 0; i < m; i++) {
                    s += list.get(i);
                }

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) * m > s) {
                        list.remove(i);
                        i--;
                    }
                }
                int nm = list.size();
                if (nm == m) {
                    break;
                }
            }
            out.append(String.format("%d\n", n - list.size()));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}