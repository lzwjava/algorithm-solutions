package com.lzw.solutions.uva.p10074;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            int[][] grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int area = 0;
            for (int i1 = 0; i1 < m; i1++) {
                for (int i2 = i1; i2 < m; i2++) {
                    for (int j1 = 0; j1 < n; j1++) {
                        boolean q = true;
                        for (int x = i1; x <= i2; x++) {
                            if (grid[x][j1] == 1) {
                                q = false;
                                break;
                            }
                        }
                        if (!q) {
                            continue;
                        }
                        boolean ok = true;
                        for (int j2 = j1; j2 < n; j2++) {
                            int t = (i2 - i1 + 1) * (j2 - j1 + 1);
                            if (t < area) {
                                continue;
                            }
                            for (int x = i1; x <= i2; x++) {
                                for (int y = j1; y <= j2 && ok; y++) {
                                    if (grid[x][y] == 1) {
                                        ok = false;
                                        break;
                                    }
                                }
                            }
                            if (ok) {
                                if (t > area) {
                                    area = t;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            out.append(String.format("%d\n", area));
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.solve();
        main.close();
    }
}
