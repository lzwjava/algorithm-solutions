package com.algorithm.solutions.codeforces.p1618B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            int m = n - 2;
            String[] strs = new String[m];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                strs[i] = st.nextToken();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(strs[0].charAt(0));
            boolean ok = false;
            for (int i = 0; i < m - 1; i++) {
                String a = strs[i];
                String b = strs[i + 1];
                char a1 = a.charAt(1);
                char b0 = b.charAt(0);
                if (a1 == b0) {
                    sb.append(a1);
                } else {
                    sb.append(String.format("%c%c", a1, b0));
                    ok = true;
                }
            }
            String last = strs[m - 1];
            if (ok) {
                sb.append(last.charAt(1));
            } else {
                sb.append(last.charAt(1));
                sb.append('a');
            }
            out.append(String.format("%s\n", sb));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}