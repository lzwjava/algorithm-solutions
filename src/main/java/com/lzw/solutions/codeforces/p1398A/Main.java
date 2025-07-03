package com.lzw.solutions.codeforces.p1398A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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

    class Item implements Comparable<Item> {
        int v;
        int i;

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
            int n = Integer.parseInt(in.readLine());
//            int[] a = new int[n];
            Item[] is = new Item[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                is[i] = new Item(v, i);
            }
            Arrays.sort(is);
            if (is[0].v + is[1].v <= is[n - 1].v) {
                int[] ids = new int[3];
                ids[0] = is[0].i;
                ids[1] = is[1].i;
                ids[2] = is[n - 1].i;
                Arrays.sort(ids);
                out.append(String.format("%d %d %d\n", ids[0] + 1, ids[1] + 1, ids[2] + 1));
            } else {
                out.append("-1\n");
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}