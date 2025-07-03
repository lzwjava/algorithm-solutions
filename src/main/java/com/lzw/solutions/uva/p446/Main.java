package com.lzw.solutions.uva.p446;

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

    String padZeros(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int paddingLen = 13 - len;
        for (int i = 0; i < paddingLen; i++) {
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            String hex1 = st.nextToken();
            String operator = st.nextToken();
            String hex2 = st.nextToken();
            int d1 = Integer.parseInt(hex1, 16);
            int d2 = Integer.parseInt(hex2, 16);
            int result = 0;
            if (operator.equals("+")) {
                result = d1 + d2;
            } else {
                result = d1 - d2;
            }
            String b1 = Integer.toBinaryString(d1);
            String b2 = Integer.toBinaryString(d2);
            b1 = padZeros(b1);
            b2 = padZeros(b2);
            out.append(String.format("%s %s %s = %d\n", b1, operator, b2, result));
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

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
