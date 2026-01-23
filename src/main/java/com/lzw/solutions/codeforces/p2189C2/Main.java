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

            if ((n & (n - 1)) == 0) {  // power of 2
                pw.println(-1);
                continue;
            }

            TreeSet<Integer> available = new TreeSet<>(Comparator.reverseOrder());  // largest first
            for (int i = 1; i <= n; i++) {
                available.add(i);
            }

            boolean[] isAvail = new boolean[n + 2];
            Arrays.fill(isAvail, 1, n + 1, true);

            int[] p = new int[n + 1];
            p[n] = n;
            available.remove(n);
            isAvail[n] = false;

            boolean ok = true;
            for (int pos = n - 1; pos >= 1; pos--) {
                Iterator<Integer> it = available.iterator();
                boolean found = false;
                while (it.hasNext()) {
                    int v = it.next();
                    int w = v ^ pos;
                    if (w >= 1 && w <= n && !isAvail[w]) {
                        p[pos] = v;
                        available.remove(v);
                        isAvail[v] = false;
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