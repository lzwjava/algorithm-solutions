package com.lzw.solutions.uva.p10334;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int maxn = 1001;
        BigInteger[] fibs = new BigInteger[maxn];
        fibs[0] = BigInteger.valueOf(1);
        fibs[1] = BigInteger.valueOf(2);
        for (int i = 2; i < maxn; i++) {
            fibs[i] = fibs[i - 1].add(fibs[i - 2]);
        }
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            int n = Integer.parseInt(line);
            out.append(String.format("%s\n", fibs[n].toString()));
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
