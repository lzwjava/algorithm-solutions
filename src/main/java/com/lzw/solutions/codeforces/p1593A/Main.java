package com.lzw.solutions.codeforces.p1593A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    int cal(int v) {
        if (v == max) {
            if (cnt > 1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return max + 1 - v;
        }
    }

    int max;
    int cnt;

    void count(int v) {
        if (v == max) {
            cnt++;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            max = Integer.max(a, b);
            max = Integer.max(max, c);
            cnt = 0;
            count(a);
            count(b);
            count(c);
            int A = cal(a);
            int B = cal(b);
            int C = cal(c);
            out.append(String.format("%d %d %d\n", A, B, C));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
