package com.lzw.solutions.uva.p10515;

import java.io.*;
import java.math.BigInteger;
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
            StringTokenizer st = new StringTokenizer(in.readLine());
            String m = st.nextToken();
            String n = st.nextToken();
            if (m.equals("0") && n.equals("0")) {
                break;
            }
            BigInteger bi = new BigInteger(m);
            BigInteger ans = bi.modPow(new BigInteger(n), BigInteger.valueOf(10));
            out.append(String.format("%d\n", ans.intValue()));
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
