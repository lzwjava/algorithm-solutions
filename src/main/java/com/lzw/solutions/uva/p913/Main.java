package com.lzw.solutions.uva.p913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }
            long n = Integer.parseInt(s);
            long k = (n + 1) / 2; // line k, n = 2*k - 1
            // in n , there are k lines, n is number k line
            long sum = (1 + n) * k / 2;

            long sum3 = 0;
            for (int i = 0; i < 3; i++) {
                sum3 += 2 * (sum - i) - 1;
            }
            System.out.println(sum3);
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
