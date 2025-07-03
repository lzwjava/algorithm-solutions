package com.algorithm.solutions.uva.p343;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            String a = st.nextToken();
            String b = st.nextToken();
            boolean found = false;
            for (int i = 2; i <= 36; i++) {
                for (int j = 2; j <= 36; j++) {
                    try {
                        int a1 = Integer.parseInt(a, i);
                        int b1 = Integer.parseInt(b, j);
                        if (a1 == b1) {
                            out.append(String.format("%s (base %d) = %s (base %d)\n", a, i, b, j));
                            found = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                out.append(String.format("%s is not equal to %s in any base 2..36\n", a, b));
            }
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
