package com.lzw.solutions.codeforces.p1003A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] a = parseArray(in.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        int mc = 0;
        for (int i = 0; i < n; i++) {
            Integer c = map.get(a[i]);
            if (c == null) {
                c = 0;
            }
            c++;
            map.put(a[i], c);
            mc = Integer.max(mc, c);
        }
        out.append(String.format("%d\n", mc));
    }
}
