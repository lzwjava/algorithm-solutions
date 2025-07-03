package com.lzw.solutions.uva.p11677;

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

    int cal(int h1, int m1, int h2, int m2) {
        if (m2 < m1) {
            m2 += 60;
            h2 -= 1;
        }
        return (h2 - h1) * 60 + m2 - m1;
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int h1, m1, h2, m2;
            h1 = Integer.parseInt(st.nextToken());
            m1 = Integer.parseInt(st.nextToken());
            h2 = Integer.parseInt(st.nextToken());
            m2 = Integer.parseInt(st.nextToken());
            if (h1 == 0 && m1 == 0 && h2 == 0 && m2 == 0) {
                break;
            }
            boolean sameDay = h2 > h1 || (h2 == h1 && m2 >= m1);
            int ans = 0;
            if (sameDay) {
                ans = cal(h1, m1, h2, m2);
            } else {
                ans = cal(h1, m1, 24, 0) + cal(0, 0, h2, m2);
            }
            out.append(String.format("%d\n", ans));
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
