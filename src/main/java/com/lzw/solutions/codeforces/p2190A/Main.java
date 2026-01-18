package com.lzw.solutions.codeforces.p2190A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    // 100100          100101         10  01  100   101   101010
    // 000011(1456)    000111(145)    01  01  001   011   000111(1346)

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine().trim();
            int i;
            int zero = 0, one = 0;
            for (i = 0; i < n; i++) {
                if (s.charAt(i) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            if (zero == n || one == n) {
                out.println("Bob");
            } else {
                ArrayList<Integer> positions = new ArrayList<>();
                for (i = 0; i < n; i++) {
                    char target;
                    if (i < zero) {
                        target = '0';
                    } else {
                        target = '1';
                    }
                    if (s.charAt(i) != target) {
                        positions.add(i);
                    }
                }
                if (positions.size() == 0) {
                    out.print("Bob");
                } else {
                    out.println("Alice");
                    int size = positions.size();
                    out.println(size);
                    for (i = 0; i < size; i++) {
                        if (i != 0) {
                            out.print(' ');
                        }
                        out.print(positions.get(i) + 1);
                    }
                    out.println();
                }
            }
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
