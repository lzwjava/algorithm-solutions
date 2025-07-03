package com.lzw.solutions.codeforces.p1359B;

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
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char[][] grid = new char[n][m];
            int white = 0;
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
                for (int j = 0; j < m; j++) {
                    char c = s.charAt(j);
                    grid[i][j] = c;
                    if (c == '.') {
                        white++;
                    }
                }
            }
            int ans = 0;
            if (2 * x <= y) {
                ans = white * x;
            } else {
                int t2 = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m - 1; j++) {
                        if (grid[i][j] == '.' && grid[i][j + 1] == '.') {
                            t2++;
                            grid[i][j] = grid[i][j + 1] = 'x';
                        }
                    }
                }
                int t1 = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (grid[i][j] == '.') {
                            t1++;
                        }
                    }
                }
                ans = x * t1 + y * t2;
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
