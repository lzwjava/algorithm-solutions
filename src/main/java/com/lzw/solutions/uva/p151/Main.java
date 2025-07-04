package com.lzw.solutions.uva.p151;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            for (int m = 1; ; m++) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 1; i <= n; i++) {
                    list.add(i);
                }
                int i = 0;
                boolean ok = true;
                while (list.size() > 1) {
                    if (list.get(i) == 13) {
                        ok = false;
                        break;
                    }
                    // System.out.print(String.format("%d ", list.get(i)));
                    list.remove(i);
                    i--;
                    i += m;
                    if (i >= list.size()) {
                        i = i % list.size();
                    }
                }
                if (ok) {
                    System.out.println(m);
                    break;
                }
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
