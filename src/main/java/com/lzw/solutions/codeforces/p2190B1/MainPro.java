// grok
package com.lzw.solutions.codeforces.p2190B1;

import java.util.Scanner;

public class MainPro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int cas = 0; cas < T; cas++) {
            int n = sc.nextInt();
            String s = sc.next();

            int ans = -1;

            for (int split = 1; split <= n; split += 2) {  // possible lengths of t
                // check if we can form a regular seq of length 'split' that is better than s
                // i.e. first difference position has ( in t, ) in s

                int open = 0;
                boolean foundDiff = false;
                boolean ok = true;

                for (int i = 0; i < split; i++) {
                    char want = (open > 0 && i + 1 < split) ? ')' : '(';
                    // but we prefer to follow s as long as possible

                    if (!foundDiff) {
                        if (s.charAt(i) == '(') {
                            open++;
                        } else {
                            // s has ), we can choose to put ( here → this becomes the diff point
                            if (open > 0) {
                                // we could close, but we decide to open instead
                                foundDiff = true;
                                open++;  // pretend we put (
                            } else {
                                open--;  // forced to follow s
                            }
                        }
                    } else {
                        // after difference, freely choose to make it valid
                        if (open > 0 && i + 1 < split) {
                            open--;  // prefer close if possible
                        } else {
                            open++;  // open
                        }
                    }

                    if (open < 0) {
                        ok = false;
                        break;
                    }
                }

                if (ok && open == 0 && foundDiff) {
                    ans = Math.max(ans, split);
                }
            }

            System.out.println(ans);
        }
        sc.close();
    }
}