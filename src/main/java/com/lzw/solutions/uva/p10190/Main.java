package com.lzw.solutions.uva.p10190;

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
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            ArrayList<Integer> list = new ArrayList<Integer>();
            boolean ok = true;
            if (m == 0 || n % m != 0) {
                ok = false;
            } else {
                double v = Math.log(n) / Math.log(m);
                if (Math.abs(v - Math.round(v)) < 1e-10) {
                    while (true) {
                        list.add(n);
                        if (n <= 1) {
                            break;
                        }
                        n /= m;
                    }
                } else {
                    ok = false;
                }
            }
            if (ok) {
                for (int i = 0; i < list.size(); i++) {
                    if (i != 0) {
                        out.append(' ');
                    }
                    out.append(String.format("%d", list.get(i)));
                }
                out.append('\n');
            } else {
                out.append(String.format("Boring!\n"));
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
