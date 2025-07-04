package com.lzw.solutions.uva.p679;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int depth = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            int total = 1 << depth;
            boolean states[] = new boolean[total + 1];
            int stopPos = 0;
            for (int i = 0; i < index; i++) {
                int cur = 1;
                while (true) {
                    int next;
                    if (states[cur]) {
                        next = 2 * cur + 1;
                    } else {
                        next = 2 * cur;
                    }
                    states[cur] = !states[cur];
                    if (next < total) {
                        cur = next;
                    } else {
                        break;
                    }
                }
                if (i == index - 1) {
                    stopPos = cur;
                }
            }
            out.append(String.format("%d\n", stopPos));
            n--;
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

        Main1 main = new Main1();
        main.solve();
        main.close();
    }
}
