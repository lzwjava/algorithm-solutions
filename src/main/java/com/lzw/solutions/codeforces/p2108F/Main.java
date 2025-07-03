package com.lzw.solutions.codeforces.p2108F;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    long[] a;
    int n;
    long maxMEX;
    int[] maxP;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        long t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(in.readLine());
            a = new long[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                if (a[i] > n) {
                    a[i] = n;
                }
            }
            int[] p = new int[n];
            boolean[] used = new boolean[n];
            maxMEX = 0;
            for (int i = 0; i < n; i++) {
                p[i] = n - 1 - i;
            }
            maxP = new int[n];

            // maxMEX = getMex(p);
            permutation(p, used, 0);
            out.println(maxMEX);
            // printArray(maxP);
        }
    }

    void printArray(int[] p) {
        for (int i = 0; i < p.length; i++) {
            out.print(p[i] + " ");
        }
        out.println();
    }

    void permutation(int[] p, boolean[] used, int cur) {
        if (cur == n) {
            long mex;
            mex = getMex(p);
            if (mex > maxMEX) {
                maxMEX = mex;
                maxP = p.clone();
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    used[i] = true;
                    p[cur] = i;
                    permutation(p, used, cur + 1);
                    used[i] = false;
                }
            }
        }
    }

    private long getMex(int[] p) {
        long mex;
        long[] b = a.clone();
        for (int i = 0; i < n; i++) {
            int pos = p[i];
            long bpi = b[pos];
            for (int j = 0; j < bpi; j++) {
                if (pos + j + 1 < n) {
                    b[pos + j + 1] += 1;
                } else {
                    break;
                }
            }
            b[pos] = 0;
        }
        boolean nonDecreasing = true;
        for (int i = 0; i < n - 1; i++) {
            if (b[i] > b[i + 1]) {
                nonDecreasing = false;
                break;
            }
        }

        if (nonDecreasing) {
            Set<Long> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(b[i]);
            }
            for (mex = 1; ; mex++) {
                if (!set.contains(mex)) {
                    break;
                }
            }
        } else {
            mex = -1;
        }
        return mex;
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.solve();
        main.close();
    }
}
