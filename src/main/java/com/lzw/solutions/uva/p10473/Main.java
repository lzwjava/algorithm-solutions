package com.lzw.solutions.uva.p10473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            String s = in.readLine().trim();
            if (s.startsWith("0x")) {
                String subStr = s.substring(2);
                int num = Integer.parseInt(subStr, 16);
                String newStr = Integer.toString(num);
                out.append(newStr).append('\n');
            } else {
                int num = Integer.parseInt(s);
                if (num < 0) {
                    break;
                }
                String newStr = Integer.toHexString(num).toUpperCase();
                out.append(String.format("0x%s", newStr)).append('\n');
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
