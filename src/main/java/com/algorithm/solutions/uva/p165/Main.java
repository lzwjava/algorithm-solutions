package com.algorithm.solutions.uva.p165;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    private final BufferedReader in;
    private final PrintWriter out;

    public Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    private void close() throws IOException {
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    private void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int h = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (h == 0 && k == 0) {
                break;
            }
            
        }
    }
}
