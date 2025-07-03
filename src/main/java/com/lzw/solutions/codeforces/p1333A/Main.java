package com.lzw.solutions.codeforces.p1333A;

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
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            char[][] g = new char[n][m];
            int s = 0;
            boolean even = n * m % 2 == 0;
            boolean black = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    char c;
                    if (black) {
                        c = 'B';
                    } else {
                        c = 'W';
                    }
                    g[i][j] = c;
                    s++;
                    if (!even || s != n * m - 1) {
                        black = !black;
                    }
                }
            }
            if (m % 2 == 0) {
                char c = g[n - 2][m - 1];
                g[n - 2][m - 1] = g[n - 2][m - 2];
                g[n - 2][m - 2] = c;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    out.append(g[i][j]);
                }
                out.append('\n');
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
