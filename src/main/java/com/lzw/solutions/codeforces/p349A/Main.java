package com.lzw.solutions.codeforces.p349A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

    Map<Integer, Integer> map;

    int getBill(int bill) {
        Integer c = map.get(bill);
        if (c == null) {
            c = 0;
        }
        return c;
    }

    void increase(int bill) {
        int c = getBill(bill);
        c++;
        map.put(bill, c);
    }

    void decrese(int bill) {
        decrese(bill, 1);
    }

    void decrese(int bill, int d) {
        int c = getBill(bill);
        c -= d;
        map.put(bill, c);
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int ticket = 25;
        map = new HashMap<>();
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            int change = v - ticket;
            if (change == 0) {
                increase(v);
            } else {
                int c = getBill(change);
                if (c > 0) {
                    decrese(change);
                    increase(v);
                } else {
                    if (change == 75) {
                        if (getBill(25) >= 1 && getBill(50) >= 1) {
                            decrese(25);
                            decrese(50);
                        } else if (getBill(25) >= 3) {
                            decrese(25, 3);
                        } else {
                            ok = false;
                            break;
                        }
                    } else if (change == 25) {
                        if (getBill(25) >= 1) {
                            decrese(25);
                        } else {
                            ok = false;
                            break;
                        }
                    }
                    increase(v);
                }
            }
        }
        if (ok) {
            out.append("YES\n");
        } else {
            out.append("NO\n");
        }
    }

}