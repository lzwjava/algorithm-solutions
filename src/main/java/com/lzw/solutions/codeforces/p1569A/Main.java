package com.lzw.solutions.codeforces.p1569A;

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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            boolean found = false;
            for (int l = 0; l < n && !found; l++) {
                for (int r = l; r < n; r++) {
                    String sub = s.substring(l, r + 1);
                    int ca = 0, cb = 0;
                    for (int k = 0; k < sub.length(); k++) {
                        char c = sub.charAt(k);
                        if (c == 'a') {
                            ca++;
                        } else if (c == 'b') {
                            cb++;
                        }
                    }
                    if (ca == cb) {
                        found = true;
                        out.append(String.format("%d %d\n", l + 1, r + 1));
                        break;
                    }
                }
            }
            if (!found) {
                out.append("-1 -1\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}
