package com.lzw.solutions.uva.p256;

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
            int half = (int) Math.pow(10, n / 2);
            for (int i = 0; i < half; i++) {
                int square = i * i;
                int left = square / half;
                int right = square % half;
                if (Math.pow(left + right, 2) == square) {
                    System.out.println(String.format("%" + n + "d", square).replace(" ", "0"));
                }
            }
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
