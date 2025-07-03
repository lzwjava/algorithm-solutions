package com.lzw.solutions.uva.p386;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair implements Comparable<Pair> {
        int a;
        int b;
        int c;
        int d;

        Pair() {}

        Pair(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Main.Pair o) {
            if (a != o.a) {
                return Integer.compare(a, o.a);
            } else if (b != o.b) {
                return Integer.compare(b, o.b);
            } else if (c != o.c) {
                return Integer.compare(c, o.c);
            } else {
                return Integer.compare(d, o.d);
            }
        }
    }

    void cal(ArrayList<Main.Pair> list, int a) {
        for (int b = 2; b < a; b++) {
            for (int c = b + 1; c < a; c++) {
                double d = Math.pow(a, 3) - Math.pow(b, 3) - Math.pow(c, 3);
                if (d > 0) {
                    double cbrt = Math.cbrt(d);
                    if (cbrt % 1 == 0) {
                        int cb = (int) cbrt;
                        if (cb > 1 && cb > c) {
                            Pair p = new Pair(a, b, c, cb);
                            list.add(p);
                        }
                    }
                }
            }
        }
    }

    void solve() throws IOException {
        ArrayList<Pair> list = new ArrayList<>();
        for (int a = 3; a <= 200; a++) {
            cal(list, a);
        }
        Collections.sort(list);
        for (Pair p : list) {
            out.append(String.format("Cube = %d, Triple = (%d,%d,%d)\n", p.a, p.b, p.c, p.d));
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

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
