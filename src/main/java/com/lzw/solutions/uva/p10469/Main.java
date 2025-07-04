package com.lzw.solutions.uva.p10469;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            String sa = Integer.toBinaryString(a);
            String sb = Integer.toBinaryString(b);
            StringBuilder s = new StringBuilder();
            int maxLen = sa.length() > sb.length() ? sa.length() : sb.length();
            for (int i = 0; i < maxLen; i++) {
                int pa = sa.length() - 1 - i;
                int va = 0;
                if (pa >= 0) {
                    va = sa.charAt(pa) - '0';
                }
                int pb = sb.length() - 1 - i;
                int vb = 0;
                if (pb >= 0) {
                    vb = sb.charAt(pb) - '0';
                }
                int v = va ^ vb;
                s.append(v);
            }
            String rs = s.reverse().toString();
            int ans = Integer.parseInt(rs, 2);
            System.out.println(ans);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
