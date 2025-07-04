package com.lzw.solutions.codeforces.p1616B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        //        m.test();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    String cal(String s) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            String s1 = s.substring(0, i + 1);
            if (!ans.equals("")) {
                if (ans.compareTo(s1) < 0) {
                    break;
                }
            }
            String s2 = new StringBuilder(s1).reverse().toString();
            String ns = String.format("%s%s", s1, s2);
            if (ans.equals("")) {
                ans = ns;
            } else {
                if (ns.compareTo(ans) < 0) {
                    ans = ns;
                }
            }
        }
        return ans;
    }

    class Seg {
        char c;
        int p;
        int end;

        Seg(char c, int p, int end) {
            this.c = c;
            this.p = p;
            this.end = end;
        }

        int start() {
            // end- start+1=p
            return end + 1 - p;
        }
    }

    String cal1(String s) {
        int n = s.length();
        char lastC = ' ';
        int lastP = 0;
        List<Seg> list = new ArrayList<>();
        String ns = String.format("%s.", s);
        for (int i = 0; i < ns.length(); i++) {
            char c = ns.charAt(i);
            if (c != lastC) {
                if (lastC != ' ') {
                    list.add(new Seg(lastC, lastP, i - 1));
                }
                lastC = c;
                lastP = 1;
            } else {
                lastP++;
            }
        }
        int ln = list.size();
        String ans = "";
        for (int i = 0; i < ln; i++) {
            Seg seg = list.get(i);
            int pos;
            if (i == 0) {
                pos = 0;
            } else {
                Seg leftSeg = list.get(i - 1);
                if (Character.compare(leftSeg.c, seg.c) < 0) {
                    pos = seg.start();
                } else {
                    pos = seg.end;
                }
            }
            String s1 = s.substring(0, pos + 1);
            if (!ans.equals("")) {
                if (ans.compareTo(s1) < 0) {
                    break;
                }
            }
            String s2 = new StringBuilder(s1).reverse().toString();
            String tns = String.format("%s%s", s1, s2);
            if (ans.equals("")) {
                ans = tns;
            } else {
                if (tns.compareTo(ans) < 0) {
                    ans = tns;
                }
            }
        }
        return ans;
    }

    String randomString(int len) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            char c = (char) (random.nextInt(10) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    void test() {
        while (true) {
            String s = randomString(5);
            String ans = cal(s);
            String ans1 = cal1(s);
            assert (ans.equals(ans1));
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            String ans = cal1(s);
            out.append(String.format("%s\n", ans));
        }
    }
}
