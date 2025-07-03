package com.lzw.solutions.codeforces.p519B;

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

    int diff(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < b.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i);
            }
        }
        return a.get(a.size() - 1);
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            a.add(v);
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n - 1; i++) {
            int v = Integer.parseInt(st.nextToken());
            b.add(v);
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n - 2; i++) {
            int v = Integer.parseInt(st.nextToken());
            c.add(v);
        }
        Collections.sort(a);
        Collections.sort(b);
        Collections.sort(c);
        int d1 = diff(a, b);
        int d2 = diff(b, c);
        out.append(String.format("%d\n", d1));
        out.append(String.format("%d\n", d2));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}