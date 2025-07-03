package com.lzw.solutions.uva.p11877;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

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
            int empty = n;
            n = 0;
            int total = 0;
            while (true) {
                // drink
                total += n;
                empty += n;
                n -= n;

                // change
                int sn = empty / 3;
                empty -= sn * 3;
                n = sn;

                if (n != 0) {
                    continue;
                } else {
                    if (empty == 0) {
                        break;
                    } else if (empty == 2) {
                        total += 1;
                        break;
                    } else if (empty == 1) {
                        break;
                    }
                }
            }
            out.append(String.format("%d\n", total));
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
