package com.lzw.solutions.codeforces.p1551B1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

    int cal(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Integer cnt = map.get(c);
            if (cnt == null) {
                cnt = 0;
            }
            cnt++;
            map.put(c, cnt);
        }
        int sum = 0;
        for (char key : map.keySet()) {
            Integer cnt = map.get(key);
            if (cnt <= 2) {
                sum += cnt;
            } else {
                sum += 2;
            }
        }
        return sum / 2;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            int maxk = cal(s);
            out.append(String.format("%d\n", maxk));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}