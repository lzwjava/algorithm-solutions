package com.lzw.solutions.codeforces.p1397A;

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

    Map<Character, Integer> map;

    void count(char ch) {
        Integer c = map.get(ch);
        if (c == null) {
            c = 0;
        }
        c++;
        map.put(ch, c);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (char c : s.toCharArray()) {
                    count(c);
                }
            }
            boolean ok = true;
            for (char ch : map.keySet()) {
                int cnt = map.get(ch);
                if (cnt % n != 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
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
