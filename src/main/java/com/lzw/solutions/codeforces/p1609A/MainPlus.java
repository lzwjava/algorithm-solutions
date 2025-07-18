package com.lzw.solutions.codeforces.p1609A;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainPlus {

    Scanner in;
    PrintWriter out;

    MainPlus() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            ArrayList<Long> ns = new ArrayList<Long>();
            for (int i = 0; i < n; i++) {
                long v = in.nextInt();
                ns.add(v);
            }
            Collections.sort(ns);
            long max = 0;
            int cnt = 0;
            while (true) {
                long sum = 0;
                for (int k = 0; k < ns.size(); k++) {
                    sum += ns.get(k);
                }
                if (sum > max) {
                    max = sum;
                } else {
                    cnt++;
                    if (cnt > 100) {
                        break;
                    }
                }
                int len = ns.size();
                if (len == 1) {
                    break;
                }
                int even = 0;
                int eveni = -1;
                for (int i = 0; i < len; i++) {
                    if (ns.get(i) % 2 == 0) {
                        even++;
                        eveni = i;
                    }
                }
                int i = -1;
                if (even == 1) {
                    i = eveni;
                } else {
                    for (i = 0; i < len; i++) {
                        if (ns.get(i) % 2 == 0) {
                            break;
                        }
                    }
                }
                int j = -1;
                for (j = len - 1; j >= 0; j--) {
                    if (j != i) {
                        break;
                    }
                }
                if (i < 0 || i >= len || j < 0 || j >= len) {
                    break;
                }
                long vi = ns.get(i);
                long vj = ns.get(j);
                long ai = vi / 2;
                long aj = vj * 2;
                ns.remove(vi);
                ns.remove(vj);
                ns.add(ai);
                ns.add(aj);
                Collections.sort(ns);
            }
            out.append(String.format("%d\n", max));
            t--;
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {

        MainPlus main = new MainPlus();
        main.solve();
        main.close();
    }
}
