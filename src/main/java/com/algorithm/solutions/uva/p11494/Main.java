package com.algorithm.solutions.uva.p11494;

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
            int x1, y1, x2, y2;
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            if (x1 == 0 && y1 == 0 && x2 == 0 && y2 == 0) {
                break;
            }
            int ans = 0;
            if (x1 == x2 && y1 == y2) {
                ans = 0;
            } else if (x1 - y1 == x2 - y2 || x1 + y1 == x2 + y2 || x1 == x2 || y1 == y2) {
                ans = 1;
            } else {
                ans = 2;
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
