package com.lzw.solutions.uva.p10229;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int fn(int n, int m) {
        if (m == 0) {
            return 0;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int m1 = fn(n - 1, m);
        int m2 = fn(n - 2, m);
        return (m1 + m2) % (1 << m);
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int ans;
            if (m == 0) {
                ans = 0;
            } else {
                int maxn = n;
                int a = 0;
                int b = 1;
                ArrayList<Integer> nums = new ArrayList<Integer>();
                nums.add(a);
                nums.add(b);
                boolean loop = false;
                for (long i = 2; i <= maxn; i++) {
                    int c = (a + b) % (1 << m);
                    if (b == 1 && c == 0) {
                        loop = true;
                        break;
                    }
                    nums.add(c);
                    a = b;
                    b = c;
                    if (i == maxn) {
                        break;
                    }
                }
                if (loop) {
                    int rest = n % nums.size();
                    ans = nums.get(rest);
                } else {
                    ans = nums.get(n);
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.solve();
        main.close();
    }
}
