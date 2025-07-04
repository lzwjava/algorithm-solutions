package com.lzw.solutions.uva.p10196;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int c = 1;
        while (true) {
            char[][] grid = new char[8][8];
            boolean end = true;
            for (int i = 0; i < 8; i++) {
                String s = in.readLine();
                for (int j = 0; j < 8; j++) {
                    grid[i][j] = s.charAt(j);
                    if (grid[i][j] != '.') {
                        end = false;
                    }
                }
            }
            if (end) {
                break;
            }

            c++;
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
