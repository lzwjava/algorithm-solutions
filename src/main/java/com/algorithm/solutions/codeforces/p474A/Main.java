package com.algorithm.solutions.codeforces.p474A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
        String[] strs = new String[]{"qwertyuiop", "asdfghjkl;", "zxcvbnm,./"};
        char ch = in.readLine().charAt(0);
        int d = 0;
        if (ch == 'R') {
            d = -1;
        } else {
            d = 1;
        }
        String s = in.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char nc = ' ';
            for (int j = 0; j < strs.length; j++) {
                String si = strs[j];
                for (int k = 0; k < si.length(); k++) {
                    if (si.charAt(k) == c) {
                        int nk = k + d;
                        nc = si.charAt(nk);
                        break;
                    }
                }
                if (nc != ' ') {
                    break;
                }
            }
            sb.append(nc);
        }
        out.append(String.format("%s\n", sb.toString()));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}