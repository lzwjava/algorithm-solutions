package com.lzw.solutions.codeforces.p2190B1;

import java.util.*;

public class MainPlus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int test = 0; test < t; test++) {
            int n = sc.nextInt();
            String s = sc.next();

            int ans = -1;

            // Try every possible first difference position i (0-based)
            // where we change s[i] from ) to (
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != ')') continue;

                // simulate prefix up to i (with s[i] changed to '(' )
                int bal = 0;
                boolean valid = true;
                for (int j = 0; j < i; j++) {
                    bal += (s.charAt(j) == '(' ? 1 : -1);
                    if (bal < 0) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) continue;

                // now add the changed '('
                bal += 1;
                if (bal < 0) continue; // though unlikely here

                // now from i+1 to end, greedily take as far as possible
                // while keeping bal >= 0, and stop when bal returns to 0
                int len = i + 1; // 0-based: positions 0..i
                int startBal = bal;

                for (int j = i + 1; j < n; j++) {
                    bal += (s.charAt(j) == '(' ? 1 : -1);
                    if (bal < 0) break;
                    len++;
                    if (bal == 0) {
                        // found a valid completion
                        ans = Math.max(ans, len);
                        break; // no need to go further — longer won't be prefix-equal
                    }
                }
            }

            System.out.println(ans);
        }
        sc.close();
    }
}
