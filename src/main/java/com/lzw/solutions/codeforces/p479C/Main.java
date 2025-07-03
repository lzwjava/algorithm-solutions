package com.lzw.solutions.codeforces.p479C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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

    class Exam implements Comparable<Exam> {
        int a, b;

        Exam(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Exam o) {
            if (a != o.a) {
                return Integer.compare(a, o.a);
            } else {
                return Integer.compare(b, o.b);
            }
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        Exam[] exams = new Exam[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            exams[i] = new Exam(a, b);
        }
        Arrays.sort(exams);
        int last = 0;
        for (int i = 0; i < n; i++) {
            Exam e = exams[i];
            int min = Math.min(e.a, e.b);
            int other = e.a + e.b - min;
            if (min >= last) {
                last = min;
            } else {
                last = other;
            }
        }
        out.append(String.format("%d\n", last));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}