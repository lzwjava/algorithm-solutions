package com.lzw.solutions.uva.p10105;

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
        int[] fact = new int[13];
        fact[0] = 1;
        for (int i = 1; i < 13; i++) {
            fact[i] = fact[i - 1] * i;
        }

        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] ns = new int[k];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < k; i++) {
                ns[i] = Integer.parseInt(st.nextToken());
            }

            int product = 1;
            for (int i = 0; i < k; i++) {
                product *= fact[ns[i]];
            }
            out.append(String.format("%d\n", fact[n] / product));
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
