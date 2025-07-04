package com.lzw.solutions.uva.p10534;

import java.io.*;
import java.util.Random;

public class Gen {

    BufferedReader in;
    PrintWriter out;

    Gen() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int maxn = 10000;
        out.append(String.format("%d\n", maxn));
        for (int i = 0; i < maxn; i++) {
            out.append(String.format("%d ", new Random().nextInt(1000)));
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
            outStream = new PrintStream("3.in");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Gen main = new Gen();
        main.solve();
        main.close();
    }
}
