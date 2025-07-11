package com.lzw.solutions.codeforces.p1622B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    class Item implements Comparable<Item> {
        int v, i;

        Item(int v, int i) {
            this.v = v;
            this.i = i;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(v, o.v);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] p = parseArray(in.readLine());
            String s = in.readLine();
            int c0 = 0, c1 = 0;
            int[] idxs = new int[n + 1];
            for (int i = 0; i < n; i++) {
                idxs[p[i]] = i;
            }
            List<Item> oneList = new ArrayList<>();
            List<Item> zeroList = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1') {
                    oneList.add(new Item(p[i], i));
                    c1++;
                } else {
                    zeroList.add(new Item(p[i], i));
                    c0++;
                }
            }
            Collections.sort(oneList);
            int[] flist = new int[n];
            int p1 = n;
            for (int i = c1 - 1; i >= 0; i--) {
                flist[oneList.get(i).i] = p1;
                p1--;
            }
            Collections.sort(zeroList);
            p1 = 1;
            for (int i = 0; i < c0; i++) {
                flist[zeroList.get(i).i] = p1;
                p1++;
            }

            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", flist[i]));
            }
            out.append('\n');
        }
    }
}
