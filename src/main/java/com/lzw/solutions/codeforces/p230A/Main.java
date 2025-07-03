package com.lzw.solutions.codeforces.p230A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    class Dragon implements Comparable<Dragon> {
        int x, y;

        Dragon(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Dragon o) {
            return Integer.compare(x, o.x);
        }
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int n = in.nextInt();
        Dragon[] dragons = new Dragon[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            dragons[i] = new Dragon(x, y);
        }
        Arrays.sort(dragons);
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            Dragon d = dragons[i];
            if (s > d.x) {
                s += d.y;
            } else {
                ok = false;
                break;
            }
        }
        if (ok) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
