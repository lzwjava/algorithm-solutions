package com.lzw.solutions.uva.p1587;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    class Pallet {
        int w;
        int h;
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            ArrayList<Pallet> pallets = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                Pallet p = new Pallet();
                int w = sc.nextInt();
                int h = sc.nextInt();
                p.w = w < h ? w : h;
                p.h = w < h ? h : w;
                pallets.add(p);
            }
            Collections.sort(pallets, new Comparator<Pallet>() {
                public int compare(Pallet a, Pallet b) {
                    if (a.w != b.w) {
                        return a.w - b.w;
                    } else {
                        return a.h - b.h;
                    }
                }
            });
            boolean ok = true;
            int w1 = pallets.get(0).w;
            for (int i = 1; i < 4; i++) {
                if (w1 != pallets.get(i).w) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                int h1 = pallets.get(2).h;
                for (int i = 3; i < 6; i++) {
                    if (h1 != pallets.get(i).h) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                int wh = pallets.get(0).h;
                if (pallets.get(1).h != wh || pallets.get(4).w != wh || pallets.get(5).w != wh) {
                    ok = false;
                }
            }

            if (ok) {
                System.out.println("POSSIBLE");
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
