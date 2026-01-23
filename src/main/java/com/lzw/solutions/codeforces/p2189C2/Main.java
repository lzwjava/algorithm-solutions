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

            // If n is power of 2, impossible
            if ((n & (n - 1)) == 0) {
                pw.println(-1);
                continue;
            }

            TreeSet<Integer> available = new TreeSet<>();
            for (int i = 1; i <= n; i++) {
                available.add(i);
            }

            int[] p = new int[n + 1];
            p[n] = n;
            available.remove(n);

            boolean ok = true;
            for (int pos = n - 1; pos >= 1; pos--) {
                Iterator<Integer> it = available.iterator();
                boolean found = false;
                while (it.hasNext()) {
                    int v = it.next();
                    int w = v ^ pos;
                    // Only accept if w is valid and already placed
                    if (w >= 1 && w <= n && !available.contains(w)) {
                        p[pos] = v;
                        available.remove(v);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ok = false;
                    break;
                }
            }

            if (!ok) {
                pw.println(-1);
            } else {
                for (int i = 1; i <= n; i++) {
                    if (i > 1) pw.print(" ");
                    pw.print(p[i]);
                }
                pw.println();
            }
        }

        pw.flush();
        pw.close();
    }
}