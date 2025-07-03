package com.algorithm.solutions.uva.p12558;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int a, b, k;
    int[] ks;
    double target;
    List<Integer> ans;

    boolean isInt(double a) {
        return Math.abs(Math.round(a) - a) < 1e-6;
    }

    long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    boolean judge(List<Integer> dms) {
        long all = b;
        for (int x : dms) {
            all = lcm(all, x);
        }
        long left = all / b * a;
        long right = 0;
        for (int x : dms) {
            right += all / x;
        }
        return left == right;
    }

    void dfs(List<Integer> dms, int start, int cur, int len, double sum) {
        if (cur == len - 1) {
            double fn = 1.0 / (target - sum);
            if (isInt(fn)) {
                int ifn = (int) Math.round(fn);
                if (ifn > dms.get(dms.size() - 1) && !forbid(ifn)) {
                    dms.add(ifn);
                    if (judge(dms)) {
                        if (ans == null || better(dms, ans)) {
                            ans = new ArrayList<>(dms);
                        }
                    }
                    dms.remove(dms.size() - 1);
                }
            }
            return;
        }
        for (int i = start; ; i++) {
            if (forbid(i)) {
                continue;
            }
            if (ans != null && i >= ans.get(ans.size() - 1)) {
                break;
            }
            double nsum = sum + 1.0 / i;
            if (nsum < target) {
                double max = nsum;
                for (int j = cur + 1; j < len; j++) {
                    max += 1.0 / (i + j - cur);
                }
                if (max > target) {
                    dms.add(i);
                    dfs(dms, i + 1, cur + 1, len, nsum);
                    dms.remove(dms.size() - 1);
                } else {
                    break;
                }
            }
        }
    }

    boolean better(List<Integer> dms, List<Integer> ans) {
        if (dms.size() != ans.size()) {
            return dms.size() < ans.size();
        } else {
            int size = dms.size();
            for (int i = size - 1; i >= 0; i--) {
                int a = dms.get(i);
                int b = ans.get(i);
                if (a != b) {
                    return a < b;
                }
            }
        }
        return false;
    }

    boolean forbid(int x) {
        for (int i = 0; i < k; i++) {
            if (ks[i] == x) {
                return true;
            }
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int u = 0; u < t; u++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            ks = new int[k];
            for (int i = 0; i < k; i++) {
                ks[i] = Integer.parseInt(st.nextToken());
            }
            target = a * 1.0 / b;
            ans = null;
            for (int len = 2; ; len++) {
                List<Integer> dms = new ArrayList<>();
                dfs(dms, 2, 0, len, 0);
                if (ans != null) {
                    break;
                }
            }
            out.append(String.format("Case %d: ", u + 1));
            out.append(String.format("%d/%d=", a, b));
            for (int i = 0; i < ans.size(); i++) {
                if (i != 0) {
                    out.append("+");
                }
                out.append(String.format("%d/%d", 1, ans.get(i)));
            }
            out.append('\n');
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        MainPlus m = new MainPlus();
        m.solve();
        m.close();
    }
}