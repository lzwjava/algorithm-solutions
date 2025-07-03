package com.algorithm.solutions.codeforces.p977B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

    int n;
    String s;
    Map<String, Integer> map;

    int count(String sub) {
        Integer c = map.get(sub);
        if (c == null) {
            c = 0;
        }
        c++;
        map.put(sub, c);
        return c;
    }

    void solve() throws IOException {
        n = Integer.parseInt(in.readLine());
        s = in.readLine();
        map = new HashMap<>();
        int max = 0;
        String maxs = "";
        for (int i = 0; i < n - 1; i++) {
            String sub = s.substring(i, i + 2);
            int c = count(sub);
            if (c > max) {
                max = c;
                maxs = sub;
            }
        }
        out.append(String.format("%s\n", maxs));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}