package com.lzw.solutions.codeforces.p2190B1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter out = new PrintWriter(System.out, true);

    // (()(()))
    // (())
    // ((()))
    // (()())
    // (())()
    // (())()
    // (()(()))
    // (()()(()))
    // ((())))
    // (((())))
    // (()(()))
    // (())()
    // ((())(()(())))

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                out.print(' ');
            }
            out.print(array[i]);
        }
        out.println();
    }

    private static void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            assert (s.length() == n);
            int ans;
            if (n == 2) {
                ans = -1;
            } else {
                //                if (s.contains("()((")) {
                //                    ans = n - 2;
                //                } else {
                int[] array = new int[n];
                int acc = 0;
                for (int i = 0; i < n; i++) {
                    if (s.charAt(i) == '(') {
                        acc--;
                    } else {
                        acc++;
                    }
                    array[i] = acc;
                }
                printArray(array);
                ans = 0;
                //                }
            }
            out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
        out.close();
    }
}
