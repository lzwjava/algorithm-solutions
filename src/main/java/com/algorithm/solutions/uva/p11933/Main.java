package com.algorithm.solutions.uva.p11933;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            String s = Integer.toBinaryString(n);
            ArrayList<Integer> indices = new ArrayList<Integer>();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                int idx = len - 1 - i;
                char ch = s.charAt(idx);
                if (ch == '1') {
                    indices.add(idx);
                }
            }
            char[] a = new char[len];
            Arrays.fill(a, '0');
            char[] b = new char[len];
            Arrays.fill(b, '0');
            for (int i = 0; i < indices.size(); i++) {
                if (i % 2 == 0) {
                    a[indices.get(i)] = '1';
                } else {
                    b[indices.get(i)] = '1';
                }
            }
            int an = Integer.parseInt(new String(a), 2);
            int bn = Integer.parseInt(new String(b), 2);
            out.append(String.format("%d %d\n", an, bn));
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
