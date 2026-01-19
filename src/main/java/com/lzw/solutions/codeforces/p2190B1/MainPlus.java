// gpt
package com.lzw.solutions.codeforces.p2190B1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainPlus {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine().trim();

            // Precompute suffix counts of '(' and ')'
            int[] sufOpen = new int[n + 1];
            int[] sufClose = new int[n + 1];

            for (int i = n - 1; i >= 0; i--) {
                sufOpen[i] = sufOpen[i + 1];
                sufClose[i] = sufClose[i + 1];
                if (s.charAt(i) == '(') sufOpen[i]++;
                else sufClose[i]++;
            }

            int answer = -1;

            // Try to skip the earliest ')' that allows a valid suffix RBS
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == ')') {
                    int pairs = Math.min(sufOpen[i + 1], sufClose[i + 1]);
                    if (pairs > 0) {
                        answer = 2 * pairs;
                        break;
                    }
                }
            }

            out.append(answer).append('\n');
        }

        System.out.print(out.toString());
    }
}
