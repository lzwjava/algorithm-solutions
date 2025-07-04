package com.lzw.solutions.uva.p11723;

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

    int cal(int r, int n) {
        if (r <= n) {
            return 0;
        }
        int i;
        for (i = 1; i <= 26; i++) {
            if (r <= n + i * n) {
                return i;
            }
        }
        return -1;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int r = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (r == 0 && n == 0) {
                break;
            }
            out.append(String.format("Case %d: ", caseNum));
            int ans = cal(r, n);
            if (ans >= 0) {
                out.append(String.format("%d\n", ans));
            } else {
                out.append("impossible\n");
            }
            caseNum++;
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
    }
}
