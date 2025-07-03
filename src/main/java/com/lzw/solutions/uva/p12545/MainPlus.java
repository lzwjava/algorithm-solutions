package com.lzw.solutions.uva.p12545;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        MainPlus m = new MainPlus();
        m.solve();
        m.close();
    }

    int count(String s, char c) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                cnt++;
            }
        }
        return cnt;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int tt = 0; tt < t; tt++) {
            String a = in.readLine();
            String b = in.readLine();
            int n = a.length();
            int a1 = count(a, '1');
            int a0 = count(a, '0');
            int au = count(a, '?');

            int b1 = count(b, '1');
            int b0 = count(b, '0');
            int ans = 0;
            if (b0 >= a0 && b1 >= a1) {
                int c0 = b0 - a0;
                int c1 = b1 - a1;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    char ch = a.charAt(i);
                    if (ch != '?') {
                        sb.append(ch);
                    } else {
                        char bc = b.charAt(i);
                        if (bc == '1') {
                            if (c1 > 0) {
                                sb.append('1');
                                c1--;
                            } else {
                                sb.append('0');
                                c0--;
                            }
                        } else {
                            if (c0 > 0) {
                                sb.append('0');
                                c0--;
                            } else {
                                sb.append('1');
                                c1--;
                            }
                        }
                    }
                }
                String na = sb.toString();
                int swap = 0;
                for (int i = 0; i < n; i++) {
                    if (na.charAt(i) != b.charAt(i)) {
                        swap++;
                    }
                }
                ans = au + swap / 2;
            } else {
                ans = -1;
            }
            out.append(String.format("Case %d: %d\n", tt + 1, ans));
        }
    }
}