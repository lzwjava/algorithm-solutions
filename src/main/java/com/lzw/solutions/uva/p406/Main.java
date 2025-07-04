package com.lzw.solutions.uva.p406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    void solve() throws IOException {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 1000; i++) {
            boolean ok = true;
            int si = (int) Math.sqrt(i);
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }
        primes.add(0, 1);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String strs[] = line.split("\\s+");
            int n = Integer.parseInt(strs[0]);
            int c = Integer.parseInt(strs[1]);
            int i = Collections.binarySearch(primes, n);
            if (i < 0) {
                i = -(i + 1) - 1;
            }
            int total = (i - 0 + 1);
            int c2;
            int start;
            if (total % 2 == 0) {
                c2 = c * 2;
                start = i / 2 - c + 1;
            } else {
                c2 = c * 2 - 1;
                start = i / 2 - c + 1;
            }
            if (start < 0) {
                start = 0;
            }
            int end = start + c2 - 1;
            if (end > i) {
                end = i;
            }
            System.out.print(String.format("%d %d:", n, c));
            for (int j = start; j <= end; j++) {
                System.out.print(" ");
                System.out.print(primes.get(j));
            }
            System.out.println();
            System.out.println();
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
