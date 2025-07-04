package com.lzw.solutions.uva.p1588;

import java.util.Scanner;

public class Main {

    class Pallet {
        int w;
        int h;
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String master = sc.next();
            String driven = sc.next();

            int masterLen = master.length();
            int drivenLen = driven.length();
            int totalLen = masterLen + drivenLen;

            int minLen = masterLen + drivenLen;

            for (int i = 0; i < totalLen - masterLen; i++) {
                for (int j = 0; j < totalLen - drivenLen; j++) {
                    // i , i+ masterLen ;  j , j+ drivenLen
                    int start = i < j ? j : i;
                    int end = i + masterLen < j + drivenLen ? i + masterLen : j + drivenLen;

                    int len = masterLen + drivenLen - (end - start);
                    if (len > minLen) {
                        continue;
                    }

                    boolean ok = true;
                    for (int k = start; k < end; k++) {
                        int mch = master.charAt(k - i) - '0';
                        int dch = driven.charAt(k - j) - '0';
                        if (mch + dch == 4) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        if (minLen > len) {
                            minLen = len;
                        }
                    }
                }
            }
            System.out.println(minLen);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
