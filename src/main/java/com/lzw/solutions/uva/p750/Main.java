package com.lzw.solutions.uva.p750;

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

    void permutation(int[] rows, boolean[] vis, int cur, int targetX, int targetY) {
        if (cur == 8) {
            total++;
            out.append(String.format("%2d", total));
            out.append(String.format("%6s", ""));
            for (int i = 0; i < 8; i++) {
                if (i != 0) {
                    out.append(" ");
                }
                out.append(String.format("%d", rows[i] + 1));
            }
            out.append('\n');
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (!vis[i]) {
                if (cur == targetY) {
                    if (i != targetX) {
                        continue;
                    }
                }
                boolean ok = true;
                for (int j = 0; j < cur; j++) {
                    int jrow = rows[j];
                    if (j + jrow == cur + i) {
                        //   /
                        ok = false;
                        break;
                    }
                    if (cur - j == i - jrow) {
                        //   \
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    vis[i] = true;
                    rows[cur] = i;
                    permutation(rows, vis, cur + 1, targetX, targetY);
                    vis[i] = false;
                }
            }
        }
    }

    int total = 0;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            in.readLine();
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            boolean[] vis = new boolean[8];
            int[] rows = new int[8];
            out.append("SOLN       COLUMN\n");
            out.append(String.format("%2s", "#"));
            out.append(String.format("%6s", ""));
            for (int i = 0; i < 8; i++) {
                if (i != 0) {
                    out.append(" ");
                }
                out.append(String.valueOf(i + 1));
            }
            out.append('\n');
            out.append('\n');
            total = 0;
            permutation(rows, vis, 0, x, y);
            t--;
            if (t != 0) {
                out.append('\n');
            }
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
