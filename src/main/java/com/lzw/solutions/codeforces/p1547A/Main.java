package com.lzw.solutions.codeforces.p1547A;

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

    boolean between(int x1, int x2, int x3) {
        return (x1 < x2 && x2 < x3) || (x1 > x2 && x2 > x3);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            in.readLine();
            StringTokenizer st = new StringTokenizer(in.readLine());
            int xa = Integer.parseInt(st.nextToken()) - 1;
            int ya = Integer.parseInt(st.nextToken()) - 1;
            st = new StringTokenizer(in.readLine());
            int xb = Integer.parseInt(st.nextToken()) - 1;
            int yb = Integer.parseInt(st.nextToken()) - 1;
            st = new StringTokenizer(in.readLine());
            int xf = Integer.parseInt(st.nextToken()) - 1;
            int yf = Integer.parseInt(st.nextToken()) - 1;
            int da = Math.abs(xa - xb);
            int db = Math.abs(ya - yb);
            int ans;
            if ((between(xa, xf, xb) && (ya == yb && ya == yf)) || (between(ya, yf, yb) && (xa == xb && xa == xf))) {
                ans = da + db + 2;
            } else {
                ans = da + db;
            }
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
