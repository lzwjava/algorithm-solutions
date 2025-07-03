package com.algorithm.solutions.uva.p278;

import java.io.*;
import java.util.ArrayList;
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
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            char ch = st.nextToken().charAt(0);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int ans;
            if (ch == 'r') {
                ans = Math.min(m, n);
            } else if (ch == 'k') {
                ans = m * n / 2;
            } else if (ch == 'Q') {
                ans = Math.min(m, n);
            } else {
                ans = (int) ((m + 1) / 2) * (int) ((n + 1) / 2);
            }
            out.append(String.format("%d\n", ans));
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
