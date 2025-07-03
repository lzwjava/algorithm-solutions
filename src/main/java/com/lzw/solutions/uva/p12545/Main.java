package com.lzw.solutions.uva.p12545;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    class Pair {
        String a;
        String b;

        Pair(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }

    void deleteSame(Pair p) {
        int len = p.a.length();
        StringBuilder sa = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (p.a.charAt(i) != p.b.charAt(i)) {
                sa.append(p.a.charAt(i));
                sb.append(p.b.charAt(i));
            }
        }
        String na = sa.toString();
        String nb = sb.toString();
        p.a = na;
        p.b = nb;
    }

    int deleteSwap(Pair p) {
        int len = p.a.length();
        List<Integer> type0 = new ArrayList<>();
        List<Integer> type1 = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char ia = p.a.charAt(i);
            char ib = p.b.charAt(i);
            if (ia == '1' && ib == '0') {
                type1.add(i);
            } else if (ia == '0' && ib == '1') {
                type0.add(i);
            }
        }
        int size0 = type0.size();
        int size1 = type1.size();
        if (size0 > 0 && size1 > 0) {
            int min = Integer.min(size0, size1);
            List<Integer> delPos = new ArrayList<>();
            for (int i = 0; i < min; i++) {
                delPos.add(type0.get(i));
                delPos.add(type1.get(i));
            }
            StringBuilder sa = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                if (!delPos.contains(i)) {
                    sa.append(p.a.charAt(i));
                    sb.append(p.b.charAt(i));
                }
            }
            String na = sa.toString();
            String nb = sb.toString();
            p.a = na;
            p.b = nb;
            return min;
        } else {
            return 0;
        }
    }

    int count(String s, char ch) {
        int len = s.length();
        int c = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ch) {
                c++;
            }
        }
        return c;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int tt = 0; tt < t; tt++) {
            String a = in.readLine();
            String b = in.readLine();
            Pair p = new Pair(a, b);
            deleteSame(p);
            int step = deleteSwap(p);
            int len = p.a.length();
            if (len > 0) {
                int a1 = count(p.a, '1');
                int a0 = count(p.a, '0');
                int a_ = count(p.a, '?');

                int b1 = count(p.b, '1');
                int b0 = count(p.b, '0');

                int max1 = a1 + a_;
            }
            out.append(String.format("%s\n%s\n\n", p.a, p.b));
//            out.append(String.format("Case %d: %d\n", tt + 1, 0));
        }
    }
}