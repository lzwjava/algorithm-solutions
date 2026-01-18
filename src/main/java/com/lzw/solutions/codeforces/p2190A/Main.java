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
            String s = in.readLine();
            assert (s.length() == n);
            boolean allZeros = true;
            int i;
            for (i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    allZeros = false;
                }
            }
            boolean allOnes = true;
            for (i = 0; i < n; i++) {
                if (s.charAt(i) == '0') {
                    allOnes = false;
                }
            }
            if (allZeros || allOnes) {
                out.println("Bob");
            } else {
                out.println("Alice");
                char[] chars = s.toCharArray();
                char[] sortedChars = chars.clone();
                Arrays.sort(sortedChars);
                ArrayList<Integer> positions = new ArrayList<>();
                for (i = 0; i < n; i++) {
                    if (chars[i] != sortedChars[i]) {
                        positions.add(i);
                    }
                }
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
