package com.lzw.solutions.codeforces.p112A;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String lowercase(String a) {
        StringBuilder sb = new StringBuilder();
        for (char ch : a.toCharArray()) {
            sb.append(Character.toLowerCase(ch));
        }
        return sb.toString();
    }

    void solve() throws IOException {
        String a = in.readLine();
        String b = in.readLine();
        String la = lowercase(a);
        String lb = lowercase(b);
        int v = la.compareTo(lb);
        if (v < 0) {
            v = -1;
        } else if (v > 0) {
            v = 1;
        }
        out.append(String.format("%d\n", v));
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
