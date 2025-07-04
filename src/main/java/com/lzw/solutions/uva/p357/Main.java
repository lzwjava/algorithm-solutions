package com.lzw.solutions.uva.p357;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);

        int units[] = new int[] {1, 5, 10, 25, 50};

        int size = 30005;
        long ways[] = new long[size];
        ways[0] = 1;
        for (int i = 0; i < units.length; i++) {
            for (int j = units[i]; j < size; j++) {
                ways[j] += ways[j - units[i]];
            }
        }

        while (sc.hasNextInt()) {
            int n = sc.nextInt();

            long answer = ways[n];
            String part = "";
            if (answer > 1) {
                part = String.format("There are %d ways", answer);
            } else {
                part = "There is only 1 way";
            }
            System.out.println(String.format("%s to produce %d cents change.", part, n));
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
