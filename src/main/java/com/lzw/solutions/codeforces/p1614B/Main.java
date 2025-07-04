package com.lzw.solutions.codeforces.p1614B;

import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    class Item implements Comparable<Item> {
        int v, i;

        Item(int v, int i) {
            this.v = v;
            this.i = i;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(o.v, v);
        }
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            List<Integer> list = new ArrayList<>();
            List<Item> sorted = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int v = in.nextInt();
                list.add(v);
                sorted.add(new Item(v, i));
            }
            Collections.sort(sorted);

            int m = n + 1;
            int[] x = new int[m];
            x[0] = 0;
            int p = 1;
            int c = 0;
            for (int i = 1; i <= n; i++) {
                x[i] = p;
                c++;
                p *= -1;
                if (c == 2) {
                    p++;
                    c = 0;
                }
            }
            long time = 0;
            for (int i = 1; i <= n; i++) {
                long d = Math.abs(x[i] - x[0]);
                d *= sorted.get(i - 1).v;
                time += 2 * d;
            }
            List<Item> xs = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                xs.add(new Item(x[i], sorted.get(i - 1).i));
            }
            Collections.sort(xs, Comparator.comparingInt(o -> o.i));
            out.append(String.format("%d\n", time));
            out.append(String.format("%d ", 0));
            for (int i = 0; i < xs.size(); i++) {
                out.append(String.format("%d ", xs.get(i).v));
            }
            out.append('\n');
        }
        out.flush();
        out.close();
    }
}
