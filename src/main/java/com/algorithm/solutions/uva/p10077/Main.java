package com.algorithm.solutions.uva.p10077;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Node {
        Fraction f;
        Node left;
        Node right;

        Node(Fraction f) {
            this.f = f;
        }
    }
    
    class Fraction {
        int numerator;
        int denominator;

        Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        @Override
        public String toString() {
            return String.format("%d/%d", numerator, denominator);
        }
    }
    
    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 1 && n == 1) {
                break;
            }
            Fraction left = new Fraction(0, 1);
            Fraction mid = new Fraction(1, 1);
            Fraction right = new Fraction(1, 0);
            StringBuilder sb = new StringBuilder();
            while (mid.numerator != m || mid.denominator != n) {
                // m/n, < mid.numerator/mid.denominator
                if (m * mid.denominator < n * mid.numerator) {
                    right = mid;
                    mid = new Fraction(left.numerator + mid.numerator, left.denominator + mid.denominator);
                    sb.append('L');
                } else {
                    left = mid;
                    mid = new Fraction(right.numerator + mid.numerator, right.denominator + mid.denominator);
                    sb.append('R');
                }
            }
            out.append(String.format("%s\n", sb.toString()));
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("2.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
