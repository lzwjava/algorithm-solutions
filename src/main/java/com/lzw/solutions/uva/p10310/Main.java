package com.lzw.solutions.uva.p10310;

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
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            double x1, y1, x2, y2;
            x1 = Double.parseDouble(st.nextToken());
            y1 = Double.parseDouble(st.nextToken());
            x2 = Double.parseDouble(st.nextToken());
            y2 = Double.parseDouble(st.nextToken());
            boolean found = false;
            double fx = 0, fy = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(in.readLine());
                double x, y;
                x = Double.parseDouble(st.nextToken());
                y = Double.parseDouble(st.nextToken());
                double d1 = Math.hypot(x1 - x, y1 - y);
                double d2 = Math.hypot(x2 - x, y2 - y);
                if (Double.compare(d1, d2 / 2) <= 0 && !found) {
                    found = true;
                    fx = x;
                    fy = y;
                }
            }
            if (found) {
                out.append(String.format("The gopher can escape through the hole at (%.3f,%.3f).\n", fx, fy));
            } else {
                out.append("The gopher cannot escape.\n");
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
