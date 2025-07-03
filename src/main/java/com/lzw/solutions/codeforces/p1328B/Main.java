package com.lzw.solutions.codeforces.p1328B;

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

    int cal(int p) {
        if (p % 2 == 0) {
            return p / 2 * (p - 1);
        } else {
            return (p - 1) / 2 * p;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int p = (int) Math.sqrt((long) 2 * k) + 1;
            while (k <= cal(p)) {
                p--;
            }
            p++;
            int s = cal(p - 1);
            int a1 = n - p;
            int x = k - s;
            int a2 = p - 1 - x;
            int a3 = n - 2 - a1 - a2;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < a1; i++) {
                sb.append('a');
            }
            sb.append('b');
            for (int i = 0; i < a2; i++) {
                sb.append('a');
            }
            sb.append('b');
            for (int i = 0; i < a3; i++) {
                sb.append('a');
            }
            out.append(String.format("%s\n", sb));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}