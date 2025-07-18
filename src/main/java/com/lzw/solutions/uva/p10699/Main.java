package com.lzw.solutions.uva.p10699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;
    ArrayList<Integer> primes;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void calPrimes() {
        primes = new ArrayList<>();
        for (int i = 2; i <= 1000; i++) {
            if (i > 2 && i % 2 == 0) {
                continue;
            }
            boolean ok = true;
            int si = (int) Math.sqrt(i);
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }
    }

    void solve() throws IOException {
        calPrimes();
        while (true) {
            String s = in.readLine();
            int n = Integer.parseInt(s);
            if (n == 0) {
                break;
            }
            int count = 0;
            int x = n;
            for (int i = 0; i < primes.size(); i++) {
                int pj = primes.get(i);
                if (x % pj == 0) {
                    count++;
                    while (x % pj == 0) {
                        x /= pj;
                    }
                }
                if (x == 1) {
                    break;
                }
            }
            if (x != 1) {
                count++;
            }
            out.append(String.format("%d : %d\n", n, count));
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
