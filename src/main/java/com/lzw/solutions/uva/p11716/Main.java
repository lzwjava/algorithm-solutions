package com.lzw.solutions.uva.p11716;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            int len = s.length();
            int sq = (int) Math.round(Math.sqrt(len));
            if (Math.abs(sq * sq - len) > 1e-10) {
                out.append("INVALID\n");
            } else {
                char[][] grid = new char[sq][sq];
                for (int i = 0; i < len; i++) {
                    int x = i / sq;
                    int y = i % sq;
                    grid[x][y] = s.charAt(i);
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < sq; i++) {
                    for (int j = 0; j < sq; j++) {
                        sb.append(grid[j][i]);
                    }
                }
                out.append(String.format("%s\n", sb.toString()));
            }
            t--;
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
