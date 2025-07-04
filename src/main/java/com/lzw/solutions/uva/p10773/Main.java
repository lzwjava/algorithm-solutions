package com.lzw.solutions.uva.p10773;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
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
        for (int i = 0; i < t; i++) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            out.append(String.format("Case %d: ", i + 1));
            if (v >= u || u == 0 || v == 0) {
                out.append("can't determine\n");
            } else {
                double dd = d, vv = v, uu = u;
                double x = Math.sqrt(uu * uu - vv * vv);
                double ans = dd / x - dd / uu;
                out.append(String.format("%.3f\n", ans));
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
