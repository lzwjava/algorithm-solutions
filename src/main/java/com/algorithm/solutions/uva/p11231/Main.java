package com.algorithm.solutions.uva.p11231;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0 && c == 0) {
                break;
            }
            int v = (n - 7) * (m - 7);
            int ans;
            if (v == 1) {
                if (c == 1) {
                    ans = 1;
                } else {
                    ans = 0;
                }
            } else {
                if (v % 2 == 0) {
                    ans = v / 2;
                } else {
                    if (c == 1) {
                        ans = (v + 1) / 2;
                    } else {
                        ans = v / 2;
                    }
                }
            }
            out.append(String.format("%d\n", ans));
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
