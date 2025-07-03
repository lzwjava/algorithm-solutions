package com.lzw.solutions.uva.p10077;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {

    BufferedReader in;
    PrintWriter out;

    Main2() {
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
            ArrayList<Fraction> fractions = new ArrayList<>();
            fractions.add(new Fraction(0, 1));
            fractions.add(new Fraction(1, 1));
            fractions.add(new Fraction(1, 0));
            for (int i = 0; ; i++) {
                ArrayList<Fraction> newFractions = new ArrayList<>();
                newFractions.add(fractions.get(0));
                for (int j = 0; j < fractions.size() - 1; j++) {
                    Fraction f1 = fractions.get(j);
                    Fraction f2 = fractions.get(j + 1);
                    Fraction nf = new Fraction(f1.numerator + f2.numerator, f1.denominator + f2.denominator);
                    newFractions.add(nf);
                    newFractions.add(fractions.get(j + 1));
                }
                // newFractions.add(fractions.get(fractions.size() - 1));
                fractions = newFractions;
                boolean found = false;
                for (int j = 0; j < fractions.size(); j++) {
                    Fraction f = fractions.get(j);
                    if (f.numerator == m && f.denominator == n) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            fractions = new ArrayList<>(fractions.subList(1, fractions.size() - 1));
            Node root = buildTree(fractions);
            find(root, m, n, "");
        }
    }

    boolean find(Node node, int m, int n, String path) {
        if (node.left == null && node.right == null) {
            if (node.f.numerator == m && node.f.denominator == n) {
                out.append(String.format("%s\n", path));
                return true;
            }
        } else {
            double v = m * 1.0 / n;
            double left = node.left.f.numerator * 1.0 / node.left.f.denominator;
            double right = node.right.f.numerator * 1.0 / node.right.f.denominator;
            if (Math.abs(v - left) < Math.abs(v - right)) {
                find(node.left, m, n, path + "L");
            } else {
                find(node.right, m, n, path + "R");
            }
        }
        return false;
    }

    Node buildTree(List<Fraction> fractions) {
        assert (fractions.size() % 2 == 1);
        int mid = fractions.size() / 2;
        Fraction f = fractions.get(mid);
        Node node = new Node(f);
        List<Fraction> leftList = fractions.subList(0, mid);
        List<Fraction> righList = fractions.subList(mid + 1, fractions.size());
        if (leftList.size() > 0) {
            Node left = buildTree(leftList);
            Node right = buildTree(righList);
            node.left = left;
            node.right = right;
        }
        return node;
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

    public static void Main2(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main2 Main2 = new Main2();
        Main2.solve();
        Main2.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
