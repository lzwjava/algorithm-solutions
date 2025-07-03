package com.lzw.solutions.codeforces.p1478A;

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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            List<List<Integer>> groups = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int gn = groups.size();
                int j;
                for (j = 0; j < gn; j++) {
                    List<Integer> gj = groups.get(j);
                    int last = gj.get(gj.size() - 1);
                    if (last < a[i]) {
                        break;
                    }
                }
                if (j == gn) {
                    List<Integer> list = new ArrayList<>();
                    list.add(a[i]);
                    groups.add(list);
                } else {
                    groups.get(j).add(a[i]);
                }
            }
            out.append(String.format("%d\n", groups.size()));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
