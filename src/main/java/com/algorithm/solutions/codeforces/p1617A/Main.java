package com.algorithm.solutions.codeforces.p1617A;

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
            String a = in.readLine();
            String b = in.readLine();
            int[] cnts = new int[26];
            for (int i = 0; i < a.length(); i++) {
                char ch = a.charAt(i);
                cnts[ch - 'a']++;
            }
            if (b.equals("abc") && cnts[0] > 0 && cnts[1] > 0 && cnts[2] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < cnts[0]; j++) {
                    sb.append('a');
                }
                for (int j = 0; j < cnts[2]; j++) {
                    sb.append("c");
                }
                for (int j = 0; j < cnts[1]; j++) {
                    sb.append("b");
                }
                for (int i = 3; i < 26; i++) {
                    if (cnts[i] > 0) {
                        for (int j = 0; j < cnts[i]; j++) {
                            sb.append((char) ('a' + i));
                        }
                    }
                }
                out.append(String.format("%s\n", sb));
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (cnts[i] > 0) {
                        for (int j = 0; j < cnts[i]; j++) {
                            sb.append((char) ('a' + i));
                        }
                    }
                }
                out.append(String.format("%s\n", sb));
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