package com.algorithm.solutions.uva.p389;

import java.io.*;
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
            String a = st.nextToken();
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(a, b);
            String s = Integer.toString(num, c);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append(Character.toUpperCase(s.charAt(i)));
            }
            String ans = sb.toString();
            if (ans.length() > 7) {
                ans = "ERROR";
            }
            out.append(String.format("%7s\n", ans));
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
