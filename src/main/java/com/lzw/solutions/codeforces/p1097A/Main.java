package com.lzw.solutions.codeforces.p1097A;

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

    void solve() throws IOException {
        String a = in.readLine();
        StringTokenizer st = new StringTokenizer(in.readLine());
        boolean ok = false;
        for (int i = 0; i < 5; i++) {
            String f = st.nextToken();
            if (f.charAt(0) == a.charAt(0) || f.charAt(1) == a.charAt(1)) {
                ok = true;
                break;
            }
        }
        if (ok) {
            out.append("YES\n");
        } else {
            out.append("NO\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}