package com.lzw.solutions.codeforces.p1609A;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Gen {

    Scanner in;
    PrintWriter out;

    Gen() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = 10000;
        out.append(String.format("%d\n", t));
        Random random = new Random();
        for (int i = 0; i < t; i++) {
            int n = random.nextInt(15) + 1;
            out.append(String.format("%d\n", n));
            for (int j = 0; j < n; j++) {
                int v = random.nextInt(15) + 1;
                out.append(String.format("%d ", v));
            }
            out.append('\n');
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
            outStream = new PrintStream("3.in");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Gen main = new Gen();
        main.solve();
        main.close();
    }
}
