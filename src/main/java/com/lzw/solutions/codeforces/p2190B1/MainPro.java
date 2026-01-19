// grok
package com.lzw.solutions.codeforces.p2190B1;

import java.util.Scanner;

public class MainPro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int test = 0; test < t; test++) {
            int n = sc.nextInt();
            String s = sc.next();

            // prefix[i] = balance after first i characters (0-based)
            int[] prefix = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefix[i + 1] = prefix[i] + (s.charAt(i) == '(' ? 1 : -1);
            }

            int ans = -1;

            // Try each possible first differing position i (0-based)
            // where s[i] == ')' and we pretend it is '(' instead
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != ')') continue;

                // balance just before i
                int balBefore = prefix[i];

                // if we put '(' instead of ')' at position i
                int balAfterChange = balBefore + 1;

                // prefix must stay non-negative
                if (balAfterChange < 0) continue;

                // now try to take as many characters as possible from i+1 onwards
                int current = balAfterChange;
                int length = i + 1;  // 0..i inclusive

                boolean canContinue = true;
                for (int j = i + 1; j < n; j++) {
                    current += (s.charAt(j) == '(' ? 1 : -1);
                    if (current < 0) {
                        canContinue = false;
                        break;
                    }
                    length++;
                }

                // must end at balance 0
                if (canContinue && current == 0) {
                    ans = Math.max(ans, length);
                }
            }

            System.out.println(ans);
        }

        sc.close();
    }
}