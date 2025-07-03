package com.lzw.solutions.codeforces.p456A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
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

    class Laptop implements Comparable<Laptop> {
        int id, a, b;

        Laptop(int id, int a, int b) {
            this.a = a;
            this.b = b;
            this.id = id;
        }

        @Override
        public int compareTo(Laptop o) {
            return Integer.compare(a, o.a);
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        Laptop[] ls = new Laptop[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ls[i] = new Laptop(i, a, b);
        }
        Arrays.sort(ls);
        Laptop[] ps = ls.clone();
        Arrays.sort(ps, new Comparator<Laptop>() {
            @Override
            public int compare(Laptop o1, Laptop o2) {
                return Integer.compare(o1.b, o2.b);
            }
        });
        boolean correct = false;
        for (int i = 0; i < n; i++) {
            if (ls[i].id != ps[i].id) {
                correct = true;
                break;
            }
        }
        if (correct) {
            System.out.println("Happy Alex");
        } else {
            System.out.println("Poor Alex");
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
