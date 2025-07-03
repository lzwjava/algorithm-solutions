package com.algorithm.solutions.uva.p10221;

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
            int s = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            String type = st.nextToken();
            double rad, arc, chord;
            if (type.equals("deg")) {
                while (a >= 360) {
                    a -= 360;
                }
                if (a >= 180) {
                    a = 360 - a;
                }
                rad = Math.toRadians(a);
            } else {
                while (a >= 60 * 24) {
                    a -= 60 * 24;
                }
                if (a >= 60 * 12) {
                    a = 60 * 24 - a;
                }
                double degree = a * 1.0 / 60;
                rad = Math.toRadians(degree);
            }
            double d = 6440 + s;
            arc = d * rad;

            chord = Math.cos((Math.PI - rad) / 2) * 2 * d;
            out.append(String.format("%.6f %.6f\n", arc, chord));
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
//            outStream = new PrintStream("1.out");
            System.setIn(inStream);
//            System.setOut(outStream);
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
