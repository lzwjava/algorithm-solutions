package com.lzw.solutions.codeforces.p499B;

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

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            map.put(a, b);
        }
        st = new StringTokenizer(in.readLine());
        int len = st.countTokens();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                sb.append(' ');
            }
            String a = st.nextToken();
            String b = map.get(a);
            if (a.length() <= b.length()) {
                sb.append(a);
            } else {
                sb.append(b);
            }
        }
        out.append(String.format("%s\n", sb.toString()));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}