package com.lzw.solutions.codeforces.p1481A;

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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int px = Integer.parseInt(st.nextToken());
            int py = Integer.parseInt(st.nextToken());
            if (px == 0 && py == 0) {
                out.append("YES\n");
                continue;
            }
            String s = in.readLine();
            int up = 0, down = 0, left = 0, right = 0;
            boolean ok = false;
            for (char c : s.toCharArray()) {
                if (c == 'U') {
                    up++;
                } else if (c == 'D') {
                    down++;
                } else if (c == 'L') {
                    left++;
                } else if (c == 'R') {
                    right++;
                }
                boolean ok1 = false;
                if (px >= 0 && right >= px) {
                    ok1 = true;
                } else if (px < 0 && left >= -px) {
                    ok1 = true;
                }
                if (ok1) {
                    boolean ok2 = false;
                    if (py >= 0 && up >= py) {
                        ok2 = true;
                    } else if (py < 0 && down >= -py) {
                        ok2 = true;
                    }
                    if (ok2) {
                        ok = true;
                        break;
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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}