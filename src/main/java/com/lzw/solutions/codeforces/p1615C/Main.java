package com.lzw.solutions.codeforces.p1615C;

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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        //        m.test();
        m.close();
    }

    String randBinary(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = (char) (random.nextInt(2) + '0');
            sb.append(c);
        }
        return sb.toString();
    }

    void test() {
        while (true) {
            int n = random.nextInt(8) + 1;
            String a = randBinary(n);
            String b = randBinary(n);
            //            a = "01";
            //            b = "10";
            //            a = "0000111";
            //            b = "0011001";
            n = a.length();
            int a1 = solveInside(n, a, b);
            int a2 = solve1Inside(n, a, b, true);
            //            out.flush();
            assert (a1 == a2);
            //            if (a1 != a2) {
            //                break;
            //            }
        }
    }

    Random random = new Random();

    List<String> cal1(int n, String a, String b) {
        if (a.equals(b)) {
            return new ArrayList<>();
        }
        List<String> changed = new ArrayList<>();
        while (!a.equals(b) && changed.size() < 1000) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (a.charAt(i) == '1') {
                    list.add(i);
                }
            }
            int ln = list.size();
            if (ln == 0) {
                return null;
            }
            int j = random.nextInt(ln);
            int pj = list.get(j);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = a.charAt(i);
                if (i == pj) {
                    sb.append(c);
                } else {
                    if (c == '1') {
                        sb.append('0');
                    } else {
                        sb.append('1');
                    }
                }
            }
            a = sb.toString();
            changed.add(a);
        }
        return changed;
    }

    int solve1Inside(int n, String a, String b, boolean print) {
        int c = Integer.MAX_VALUE;
        List<String> minChanged = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            List<String> changed = cal1(n, a, b);
            int cs;
            if (changed == null) {
                cs = 1000;
            } else {
                cs = changed.size();
            }
            if (cs < c) {
                c = cs;
                minChanged = changed;
            }
        }
        if (c == 1000) {
            return -1;
        } else {
            if (print) {
                for (String s : minChanged) {
                    out.append(String.format("%s\n", s));
                }
            }
            return c;
        }
    }

    void solve1() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String a = in.readLine();
            String b = in.readLine();
            int ans = solve1Inside(n, a, b, false);
            out.append(String.format("%d\n", ans));
        }
    }

    int solveInside(int n, String a, String b) {
        List<Character> diffList = new ArrayList<>();
        List<Character> sameList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sameList.add(a.charAt(i));
            } else {
                diffList.add(a.charAt(i));
            }
        }
        int same = sameList.size();
        int diff = diffList.size();

        int s1 = 0;
        for (char c : sameList) {
            if (c == '1') {
                s1++;
            }
        }
        int s0 = sameList.size() - s1;
        int d1 = 0;
        for (char c : diffList) {
            if (c == '1') {
                d1++;
            }
        }
        int d0 = diffList.size() - d1;
        int ans;
        if (same == n) {
            ans = 0;
        } else {
            int v = Integer.MAX_VALUE;
            if (same % 2 == 1 && s1 == s0 + 1) {
                v = Math.min(v, same);
            }
            if (diff % 2 == 0 && d1 == d0) {
                v = Math.min(v, diff);
            }
            if (v == Integer.MAX_VALUE) {
                ans = -1;
            } else {
                ans = v;
            }
        }
        return ans;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String a = in.readLine();
            String b = in.readLine();
            int ans = solveInside(n, a, b);
            out.append(String.format("%d\n", ans));
        }
    }
}
