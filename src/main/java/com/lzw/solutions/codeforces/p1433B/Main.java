package com.lzw.solutions.codeforces.p1433B;

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
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            while (true) {
                int s1 = -1, e1 = -1;
                for (int i = 0; i < n; i++) {
                    if (a[i] == 1) {
                        if (s1 == -1 && e1 == -1) {
                            s1 = i;
                            e1 = i;
                        } else {
                            e1 = i;
                        }
                    } else {
                        if (s1 == -1 && e1 == -1) {
                            continue;
                        } else {
                            
                        }
                    }
                }
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