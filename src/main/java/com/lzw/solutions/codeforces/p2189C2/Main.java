// grok-4.1-thinking
package com.lzw.solutions.codeforces.p2189C2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());

            // Check if n is power of 2
            if ((n & (n - 1)) == 0) {
                pw.println(-1);
                continue;
            }

            // TreeSet to keep available numbers in sorted order
            TreeSet<Integer> available = new TreeSet<>();
            for (int i = 1; i <= n; i++) {
                available.add(i);
            }

            int[] p = new int[n + 1];

            // Place n at position n
            p[n] = n;
            available.remove(n);

            // From n-1 down to 1
            for (int pos = n - 1; pos >= 1; pos--) {
                boolean found = false;

                // Try smallest available numbers first
                Iterator<Integer> it = available.iterator();
                while (it.hasNext()) {
                    int v = it.next();
                    int w = v ^ pos;

                    // Check if w is invalid or already used
                    if (w < 1 || w > n || !available.contains(w)) {
                        // v is good to place at pos
                        p[pos] = v;
                        available.remove(v);
                        found = true;
                        break;
                    }
                }

                // In theory this should never happen for non-power-of-2 n
                if (!found) {
                    pw.println(-1);
                    break;
                }
            }

            if (p[1] == 0) continue; // failed case (should not occur)

            // Output the permutation
            for (int i = 1; i <= n; i++) {
                if (i > 1) pw.print(" ");
                pw.print(p[i]);
            }
            pw.println();
        }

        pw.flush();
        pw.close();
    }
}