package com.lzw.solutions.uva.p11743;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int digitSum(int x) {
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            String s = in.readLine();
            s = s.replace(" ", "");
            int s1 = 0;
            int s2 = 0;
            for (int i = 0; i < s.length(); i++) {
                int d = s.charAt(i) - '0';
                if (i % 2 == 0) {
                    s1 += digitSum(2 * d);
                } else {
                    s2 += d;
                }
            }
            if ((s1 + s2) % 10 == 0) {
                out.append("Valid\n");
            } else {
                out.append("Invalid\n");
            }
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
