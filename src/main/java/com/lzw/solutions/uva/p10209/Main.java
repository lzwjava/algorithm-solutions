package com.lzw.solutions.uva.p10209;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            double a = Double.parseDouble(s);

            double arc1of4 = Math.PI * a * a / 4;
            double sq = a * a;

            double x, y, z;
            z = sq - a * a * Math.sin(Math.PI / 3) / 2 - Math.PI * a * a / 6;
            y = sq - arc1of4 - 2 * z;
            x = sq - 4 * (y + z);
            out.append(String.format("%.3f %.3f %.3f\n", x, 4 * y, 4 * z));
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
