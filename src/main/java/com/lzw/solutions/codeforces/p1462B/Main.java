package com.lzw.solutions.codeforces.p1462B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    boolean match(String s, String a, int i, int p, List<String> list, String sb) {
        if (i == s.length()) {
            if (sb.length() > 0) {
                list.add(sb);
            }
            boolean result;
            if (p == a.length() && list.size() <= 1) {
                result = true;
            } else {
                result = false;
            }
            if (sb.length() > 0) {
                list.remove(list.size() - 1);
            }
            return result;
        }
        if (p == a.length()) {
            return match(s, a, s.length(), p, list, s.substring(i) + sb);
        }
        if (list.size() > 1) {
            return false;
        }
        char c = s.charAt(i);
        if (p < a.length() && c == a.charAt(p)) {
            if (sb.length() > 0) {
                list.add(sb);
            }
            boolean result = match(s, a, i + 1, p + 1, list, "");
            if (sb.length() > 0) {
                list.remove(list.size() - 1);
            }
            if (result) {
                return true;
            }
            result = match(s, a, i + 1, p, list, sb + c);
            if (result) {
                return true;
            }
        } else {
            boolean result = match(s, a, i + 1, p, list, sb + c);
            if (result) {
                return true;
            }
        }
        return false;
    }

    boolean check(String s, String a) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == a.charAt(p)) {
                p++;
                if (p == a.length()) {
                    break;
                }
            }
        }
        return p == a.length();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            String a = "2020";
            int c0 = 0, c2 = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    c0++;
                } else if (c == '2') {
                    c2++;
                }
            }
            boolean ok;
            if (c0 < 2 || c2 < 2 || !check(s, a)) {
                ok = false;
            } else {
                List<String> list = new ArrayList<>();
                ok = match(s, a, 0, 0, list, "");
            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
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
