package com.algorithm.solutions.codeforces.p1454B;

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

    Map<Integer, Integer> map;
    Map<Integer, Integer> ps;

    void count(int v, int pos) {
        Integer c = map.get(v);
        if (c == null) {
            c = 0;
        }
        c++;
        map.put(v, c);
        ps.put(v, pos);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            map = new HashMap<>();
            ps = new HashMap<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                count(v, i);
            }
            List<Integer> list = new ArrayList<>();
            for (int v : map.keySet()) {
                int c = map.get(v);
                if (c == 1) {
                    list.add(v);
                }
            }
            int ans;
            if (list.size() == 0) {
                ans = -1;
            } else {
                Collections.sort(list);
                Integer v = list.get(0);
                ans = ps.get(v) + 1;
            }
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}