package com.lzw.solutions.codeforces.p1619C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    long cal(long a, long b) {
        StringBuilder sb = new StringBuilder();
        while (a != 0 || b != 0) {
            int ta = (int) (a % 10);
            int tb = (int) (b % 10);
            int sa = ta + tb;
            String s = String.format("%s", sa);
            String s1 = new StringBuilder(s).reverse().toString();
            sb.append(s1);

            a /= 10;
            b /= 10;
        }
        String fs = sb.reverse().toString();
        try {
            return Long.parseLong(fs);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    boolean isValid(String s) {
        if (s.length() > 2) {
            return false;
        }
        if (s.length() == 2 && s.charAt(0) == '0') {
            return false;
        }
        if (s.length() == 0) {
            return false;
        }
        return true;
    }

    int calDigit(String s, int c) {
        int v = Integer.parseInt(s);
        if (v < c) {
            return -1;
        } else {
            return v - c;
        }
    }

    long toInt(int[] bs, int m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(String.format("%d", bs[i]));
        }
        return Long.parseLong(sb.reverse().toString());
    }

    void permutation(int[] idx, String[] ns, int[] bs, int i) {
        if (found) {
            return;
        }
        if (i >= 1 && idx[i - 1] == 0) {
            long b = toInt(bs, i);
            if (cal(a, b) == s) {
                found = true;
                this.b = b;
            } else {
                found = true;
                this.b = -1;
            }
            return;
        }
        int st;
        if (i == 0) {
            st = sn - 1;
        } else {
            st = idx[i - 1] - 1;
        }
        for (int j = st; j >= 0; j--) {
            idx[i] = j;
            int s1, e1;
            if (i == 0) {
                s1 = j;
                e1 = sn;
            } else {
                s1 = j;
                e1 = idx[i - 1];
            }
            String sub = ss.substring(s1, e1);
            if (!isValid(sub)) {
                break;
            }
            ns[i] = sub;
            int d = calDigit(sub, digitA(i));
            if (d == -1) {
                continue;
            }
            bs[i] = d;
            permutation(idx, ns, bs, i + 1);
        }
    }

    int digitA(int j) {
        if (j < an) {
            return as.charAt(an - 1 - j) - '0';
        } else {
            return 0;
        }
    }

    long a, s, b;
    String as, ss;
    int an, sn;
    boolean found;

    long trySolve(long a, long s) {
        this.a = a;
        this.s = s;
        as = String.format("%s", a);
        ss = String.format("%s", s);
        an = as.length();
        sn = ss.length();
        found = false;
        int m = sn;
        int[] idx = new int[m];
        int[] bs = new int[m];
        String[] ns = new String[m];
        permutation(idx, ns, bs, 0);

        if (!found) {
            return -1;
        } else {
            return b;
        }
    }

    void test() {
        Random random = new Random();
        for (int i = 1; i < 10000; i++) {
            long a = (long) (random.nextDouble() * 1e9);
            long b = (long) (random.nextDouble() * 1e9);
            long s = cal(a, b);
            if (s == -1) {
                continue;
            }
            long v = trySolve(a, s);
            assert (v == b);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            long a = Long.parseLong(st.nextToken());
            long s = Long.parseLong(st.nextToken());
            long ans = trySolve(a, s);
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        //        m.test();
        m.close();
    }
}
