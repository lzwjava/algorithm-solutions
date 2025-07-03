package com.lzw.solutions.uva.p10703;

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
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0 && n == 0) {
                break;
            }

            boolean[][] grid = new boolean[w][h];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(in.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                if (x1 > x2) {
                    int t = x1;
                    x1 = x2;
                    x2 = t;
                }
                if (y1 > y2) {
                    int t = y1;
                    y1 = y2;
                    y2 = t;
                }
                for (int x = x1 - 1; x < x2; x++) {
                    for (int y = y1 - 1; y < y2; y++) {
                        grid[x][y] = true;
                    }
                }
            }

            int s = 0;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (!grid[i][j]) {
                        s++;
                    }
                }
            }

            if (s == 0) {
                out.append("There is no empty spots.\n");
            } else if (s == 1) {
                out.append("There is one empty spot.\n");
            } else {
                out.append(String.format("There are %d empty spots.\n", s));
            }
            in.readLine();
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
