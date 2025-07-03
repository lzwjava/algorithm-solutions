package com.lzw.solutions.uva.p12558;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int k;
    int[] ks;
    List<Long> ans;

    long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    void dfs(List<Long> dms, int start, int cur, int len, long a, long b) {
        if (cur == len - 1) {
            if (b % a != 0) {
                return;
            }
            if (b > dms.get(dms.size() - 1) && !forbid(b)) {
                dms.add(b);
                if (ans == null || better(dms, ans)) {
                    ans = new ArrayList<>(dms);
                }
                dms.remove(dms.size() - 1);
            }
            return;
        }
        for (int i = start; ; i++) {
            if (forbid(i)) {
                continue;
            }
            // a/b - n /i <=0
            // a/b <= n/i
            // a*i <= n*b
            if (a * i > (len - cur) * b) {
                break;
            }
            // a/b - 1/i = a2/ b2
            long b2 = b * i;
            long a2 = a * i - b;
            if (a2 > 0) {
                long g = gcd(a2, b2);
                dms.add((long) i);
                dfs(dms, i + 1, cur + 1, len, a2 / g, b2 / g);
                dms.remove(dms.size() - 1);
            }
        }
    }

    boolean better(List<Long> dms, List<Long> ans) {
        if (dms.size() != ans.size()) {
            return dms.size() < ans.size();
        } else {
            int size = dms.size();
            for (int i = size - 1; i >= 0; i--) {
                long a = dms.get(i);
                long b = ans.get(i);
                if (a != b) {
                    return a < b;
                }
            }
        }
        return false;
    }

    boolean forbid(long x) {
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
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            ks = new int[k];
            for (int i = 0; i < k; i++) {
                ks[i] = Integer.parseInt(st.nextToken());
            }
            ans = null;
            for (int len = 2; ; len++) {
                List<Long> dms = new ArrayList<>();
                dfs(dms, 2, 0, len, a, b);
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
        Main m = new Main();
        m.solve();
        m.close();
    }
}