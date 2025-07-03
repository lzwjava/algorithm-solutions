package com.lzw.solutions.uva.p1673;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    BufferedReader in;
    PrintWriter out;

    static final int MAXN = (int) (1e6);
    private ArrayList<HashMap<Character, Integer>> to;
    private int[] link;
    private int[] len;
    private boolean[] finalState;
    private int last;
    private int sz;

    private void initialize() {
        link = new int[MAXN];
        len = new int[MAXN];
        finalState = new boolean[MAXN];

        last = 0;
        sz = 1;

        to = new ArrayList<>();

        for (int i = 0; i < MAXN; i++)
            to.add(new HashMap<>());
    }

    private void addCharacter(char c, boolean mark) {
        int prev = last;
        int curr = (last = sz++);

        if (mark)
            finalState[curr] = true;

        len[curr] = len[prev] + 1;
        for (; to.get(prev).get(c) == null; prev = link[prev])
            to.get(prev).put(c, curr);

        if (to.get(prev).get(c) == curr) {
            link[curr] = 0;
            return;
        }

        int next = to.get(prev).get(c);
        if (len[next] == len[prev] + 1) {
            link[curr] = next;
            return;
        }

        int next2 = sz++;

        if (mark)
            finalState[next2] = true;

        to.get(next2).putAll(to.get(next));

        link[next2] = link[next];
        len[next2] = len[curr] + 1;

        link[curr] = link[next] = next2;

        for (; to.get(prev).get(c) == next; prev = link[prev])
            to.get(prev).put(c, next2);
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            initialize();
            int n = Integer.parseInt(s);
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = in.readLine();
            }
            int totalLen = 0;
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    addCharacter('$', false);
                    totalLen++;
                }
                String str = strs[i];
                for (int j = 0; j < str.length(); j++) {
                    addCharacter(str.charAt(j), i == n - 1 && j == str.length() - 1);
                    totalLen++;
                }
            }
            int[] topo = new int[MAXN];
            int[] topocnt = new int[MAXN];

            for (int i = 0; i <= totalLen; i++) {
                topocnt[i] = 0;
            }
            for (int i = 0; i < sz; i++) {
                topocnt[len[i]]++;
            }
            for (int i = 1; i <= totalLen; i++) {
                topocnt[i] += topocnt[i - 1];
            }
            for (int i = 0; i < sz; i++) {
                topo[--topocnt[len[i]]] = i;
            }

            int ans = 0;
            int[] cnt = new int[sz];
            int[] sum = new int[sz];
            for (int i = 0; i < sz; i++) {
                cnt[i] = sum[i] = 0;
            }
            cnt[0] = 1;
            int mod = 2012;
            for (int i = 0; i < sz; i++) {
                int fa = topo[i];
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    char c = (char) (j + '0');
                    if (to.get(fa).get(c) != null) {
                        int son = link[to.get(fa).get(c)];
                        cnt[son] = (cnt[son] + cnt[fa]) % mod;
                        sum[son] = (sum[son] + sum[fa] * 10 + cnt[fa] * j) % mod;
                    }
                }
                ans = (ans + sum[fa]) % mod;
            }
            out.append(String.format("%d\n", ans));
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