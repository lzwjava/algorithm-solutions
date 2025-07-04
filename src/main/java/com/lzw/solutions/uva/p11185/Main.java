package com.lzw.solutions.uva.p11185;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            int n = Integer.parseInt(line);
            if (n < 0) {
                break;
            }
            String s = Integer.toString(n, 3);
            System.out.println(s);
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
