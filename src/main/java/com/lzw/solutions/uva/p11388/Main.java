package com.lzw.solutions.uva.p11388;

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

    int gcd(int a, int b) {
        int min = a;
        if (b < min) {
            min = b;
        }
        int i;
        for (i = min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                break;
            }
        }
        if (i >= 1) {
            return i;
        } else {
            return -1;
        }
    }

    int lcm(int a, int b) {
        int max = a;
        if (b > max) {
            max = b;
        }
        int i;
        for (i = max; ; i++) {
            if (i % a == 0 && i % b == 0) {
                break;
            }
        }
        return i;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        // int g = gcd(3, 6);
        // int l = lcm(3, 6);
        // out.append(String.format("%d %d", g, l));
        while (t > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int g = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            if (l % g == 0) {
                out.append(String.format("%d %d\n", g, l));
            } else {
                out.append("-1\n");
            }
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
