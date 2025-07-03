package com.algorithm.solutions.codeforces.p1521A;

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

    boolean distinct(int x, int y, int z) {
        return x != y && x != z && y != z;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b == 1) {
                out.append("NO\n");
            } else {
                long x, y, z;
                y = a;
                x = (long) a * b;
                z = x + y;
                out.append("YES\n");
                out.append(String.format("%d %d %d\n", x, y, z));
            }
            t--;
        }
    }

    void solve1() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            List<Integer> goods = new ArrayList<>();
            List<Integer> nearlys = new ArrayList<>();
            for (int i = 1; i <= a * b * 10; i++) {
                if (i % (a * b) == 0) {
                    goods.add(i);
                } else if (i % a == 0) {
                    nearlys.add(i);
                }
            }
            boolean ok = false;
            for (int x : goods) {
                for (int y : nearlys) {
                    int z = x + y;
                    if (!distinct(x, y, z)) {
                        continue;
                    }
                    int zi = Collections.binarySearch(nearlys, z);
                    if (zi >= 0) {
                        ok = true;
                        out.append(String.format("YES\n"));
                        out.append(String.format("%d %d %d\n", x, y, z));
                        break;
                    }
                    if (x > y) {
                        z = x - y;
                        if (!distinct(x, y, z)) {
                            continue;
                        }
                        zi = Collections.binarySearch(nearlys, z);
                        if (zi >= 0) {
                            ok = true;
                            out.append(String.format("YES\n"));
                            out.append(String.format("%d %d %d\n", y, z, x));
                            break;
                        }
                    }
                }
                if (ok) {
                    break;
                }
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