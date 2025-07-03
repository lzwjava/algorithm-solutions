package com.algorithm.solutions.codeforces.p208A;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String ns = s.replace("WUB", " ");
        ns = ns.replaceAll("\\s+", " ");
        System.out.println(ns.trim());
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
