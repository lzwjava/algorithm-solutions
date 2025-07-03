package com.lzw.solutions.codeforces.p1620C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

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

    void solve1() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            String s = in.readLine();
            List<Integer> bs = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            int p = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == 'a') {
                    if (p > 0) {
                        sb.append('*');
                        bs.add(p * k);
                        p = 0;
                    }
                    sb.append('a');
                } else if (c == '*') {
                    p++;
                }
            }
            if (p > 0) {
                sb.append('*');
                bs.add(p * k);
            }
            String ns = sb.toString();
            int m = bs.size();
            BigInteger[] ps = new BigInteger[m];
            BigInteger q = BigInteger.ONE;
            for (int i = m - 1; i >= 0; i--) {
                ps[i] = q;
                q = q.multiply(BigInteger.valueOf(bs.get(i) + 1));
            }
            int[] as = new int[m];
            x--;
            for (int i = 0; i < m; i++) {
                BigInteger bx = BigInteger.valueOf(x);
                if (bx.compareTo(ps[i]) < 0) {
                    continue;
                }
                as[i] = (int) (x / ps[i].longValue());
                x %= ps[i].longValue();
                if (x == 0) {
                    break;
                }
            }
            p = 0;
            StringBuilder nsb = new StringBuilder();
            for (int i = 0; i < ns.length(); i++) {
                char c = ns.charAt(i);
                if (c == '*') {
                    for (int j = 0; j < as[p]; j++) {
                        nsb.append('b');
                    }
                    p++;
                } else {
                    nsb.append(c);
                }
            }
            out.append(String.format("%s\n", nsb));
            t--;
        }
    }

    String toStr(int n, String s, int[] bs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                sb.append(c);
            } else if (c == '*') {
                if (bs[i] > 0) {
                    for (int j = 0; j < bs[i]; j++) {
                        sb.append('b');
                    }
                }
            }
        }
        return sb.toString();
    }

    void permutation(String s, int[] bs, int i, int k, int n) {
        if (i == n) {
            String ns = toStr(n, s, bs);
            set.add(ns);
            return;
        }
        char c = s.charAt(i);
        if (c == 'a') {
            permutation(s, bs, i + 1, k, n);
        } else if (c == '*') {
            for (int j = 0; j <= k; j++) {
                bs[i] = j;
                permutation(s, bs, i + 1, k, n);
            }
        }
    }

    Set<String> set;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            String s = in.readLine();
            set = new HashSet<>();
            int[] bs = new int[n];
            permutation(s, bs, 0, k, n);
            List<String> list = new ArrayList<>();
            list.addAll(set);
            Collections.sort(list);
            out.append(String.format("%s\n", list.get((int) x - 1)));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve1();
        m.close();
    }
}
