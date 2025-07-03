package com.algorithm.solutions.codeforces.p1614C;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {

    Scanner in;
    PrintWriter out;

    Main() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    class Segment implements Comparable<Segment> {
        int l, r, x;

        Segment(int l, int r, int x) {
            this.l = l;
            this.r = r;
            this.x = x;
        }

        boolean contain(Segment a, Segment b) {
            return b.l >= a.l && b.r <= a.r;
        }

        int len() {
            return r - l + 1;
        }

        @Override
        public int compareTo(Segment o) {
            if (contain(this, o)) {
                return 1;
            } else if (contain(o, this)) {
                return -1;
            }
            return Integer.compare(len(), o.len());
        }
    }

    int ans;

    int mod = 1000000007;

    void permutation(int[] a, int st, int n, int cur, int m, int xor) {
        if (cur == n) {
            ans = (ans + xor) % mod;
            return;
        }
        for (int i = st; i < m; i++) {
            int nxor;
            if (xor == -1) {
                nxor = a[i];
            } else {
                nxor = xor ^ a[i];
            }
            permutation(a, i + 1, n, cur + 1, m, nxor);
        }
    }

    int cal(int n, int m, Segment[] segments) {
        Random random = new Random();
        Arrays.sort(segments);
        int[] a = new int[n];
        Arrays.fill(a, -1);
        int v = -1;
        for (int i = 0; i < m; i++) {
            Segment s = segments[i];
            if (v == -1) {
                v = s.x;
            } else {
                v |= s.x;
            }
            if (s.l == s.r) {
                a[s.l] = s.x;
            } else {
                List<Integer> list = new ArrayList<>();
                for (int j = s.l; j <= s.r; j++) {
                    if (a[j] != -1) {
                        list.add(a[j]);
                    }
                }
                int len = s.len();
                int rest = len - list.size();
                if (rest > 0) {
                    int[] p = new int[rest];
                    while (true) {
                        for (int j = 0; j < rest; j++) {
                            p[j] = random.nextInt(20);
                        }
                        int or = p[0];
                        for (int j = 1; j < rest; j++) {
                            or = or | p[j];
                        }
                        for (int y : list) {
                            or = or | y;
                        }
                        if (or == s.x) {
                            break;
                        }
                    }
                    int q = 0;
                    for (int j = s.l; j <= s.r; j++) {
                        if (a[j] == -1) {
                            a[j] = p[q];
                            q++;
                        }
                    }
                }
            }
        }
//        out.append(String.format("%d\n", v));
//        for (int i = 0; i < n; i++) {
//            out.append(String.format("%d   %3s\n", a[i], Integer.toBinaryString(a[i])));
//        }
        ans = 0;
        for (int len = 1; len <= n; len++) {
            permutation(a, 0, len, 0, n, -1);
        }
//        out.append(String.format("%d\n", ans));
        return ans;
    }

    int cal1(int n, int m, Segment[] segments) {
        int x = segments[0].x;
        for (int i = 1; i < m; i++) {
            x |= segments[i].x;
        }
        BigInteger bi = BigInteger.valueOf(2);
        BigInteger bm = BigInteger.valueOf(mod);
        int ans = BigInteger.valueOf(x).multiply(bi.modPow(BigInteger.valueOf(n - 1), bm)).mod(bm).intValue();
        return ans;
    }

    void solve() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int m = in.nextInt();
            Segment[] segments = new Segment[m];
            for (int i = 0; i < m; i++) {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                int x = in.nextInt();
                segments[i] = new Segment(l, r, x);
            }
            int ans = cal1(n, m, segments);
            out.append(String.format("%d\n", ans));
        }
    }
}