package com.lzw.solutions.codeforces.p1324B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            Map<Integer, List<Integer>> map = new HashMap<>();
            boolean ok = false;
            for (int i = 0; i < n; i++) {
                List<Integer> list = map.get(a[i]);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(i);
                map.put(a[i], list);
                if (list.size() >= 2) {
                    int ls = list.size();
                    int d = list.get(ls - 1) - list.get(0);
                    if (d > 1) {
                        ok = true;
                        break;
                    }
                }
            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}