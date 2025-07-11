package com.lzw.solutions.uva.p147;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    int currency[] = new int[] {5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};

    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = 30005;
        long ways[] = new long[size];
        ways[0] = 1;

        for (int i = 0; i < currency.length; i++) {
            int end = size - currency[i];
            for (int j = 0; j < end; j += 5) {
                ways[j + currency[i]] += ways[j];
            }
        }
        while (true) {
            String line = br.readLine();
            line = line.trim();
            String strs[] = line.split("\\.");
            int a = Integer.parseInt(strs[0]);
            int b = Integer.parseInt(strs[1]);
            if (a == 0 && b == 0) {
                break;
            }
            int s = a * 100 + b;
            System.out.println(String.format("%6s%17d", line, ways[s]));
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
