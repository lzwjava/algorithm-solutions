package com.lzw.solutions.uva.p679;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int depth = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            int k = 1;
            for (int i = 0; i < depth - 1; i++) {
                if (index % 2 == 1) {
                    k = 2 * k;
                    index = (index + 1) / 2;
                } else {
                    k = 2 * k + 1;
                    index /= 2;
                }
            }
            out.append(String.format("%d\n", k));
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
