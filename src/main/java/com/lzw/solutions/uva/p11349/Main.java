package com.lzw.solutions.uva.p11349;

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
        int t = Integer.parseInt(in.readLine());
        for (int u = 0; u < t; u++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            st.nextToken();
            st.nextToken();
            int n = Integer.parseInt(st.nextToken());
            long[][] grid = new long[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Long.parseLong(st.nextToken());
                }
            }
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int si = n - 1 - i;
                    int sj = n - 1 - j;
                    if (grid[i][j] < 0 || grid[si][sj] < 0 || grid[i][j] != grid[si][sj]) {
                        ok = false;
                        break;
                    }
                }
            }
            out.append(String.format("Test #%d: ", u + 1));
            if (ok) {
                out.append("Symmetric.\n");
            } else {
                out.append("Non-symmetric.\n");
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
