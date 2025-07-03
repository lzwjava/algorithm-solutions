package com.algorithm.solutions.uva.p11650;

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
            String line = in.readLine();
            String[] splits = line.split(":");
            int h = Integer.parseInt(splits[0]);
            int m = Integer.parseInt(splits[1]);
            int rh, rm;
            if (h == 12 && m == 0) {
                rh = h;
                rm = m;
            } else {
                if (h == 12) {
                    h = 0;
                } else if (h == 11) {
                    h = -1;
                }
                rh = 11 - h;
                rm = 60 - m;
                if (rm == 60) {
                    rh = (rh + 1) % 12;
                    rm = 0;
                }
            }
            out.append(String.format("%02d:%02d\n", rh, rm));
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
