package com.lzw.solutions.uva.p221;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    class Building {
        int num;
        int x;
        int y;
        int w;
        int d;
        int h;

        // a cover b
        boolean cover(double mx) {
            int ax1 = x;
            int ax2 = x + w;
            return ax1 <= mx + 1e-10 && mx - 1e-10 <= ax2;          
        }

        boolean visibleAt(ArrayList<Building> buildings, double mx) {
            if (!cover(mx)) {
                return false;
            }
            for (int i = 0; i < buildings.size(); i++) {
                Building b = buildings.get(i);
                if (num == b.num) {
                    continue;
                }
                if (b.cover(mx) && b.y < y && b.h >= h) {
                    return false;
                }
            }
            return true;
        }
    }  
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int num=1;
        for (;;) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            ArrayList<Building> buildings = new ArrayList<>();
            HashSet<Integer> xs = new HashSet<>();
            for (int i = 0; i < n; i++) {
                Building b = new Building();
                b.x = sc.nextInt();
                b.y = sc.nextInt();
                b.w = sc.nextInt();
                b.d = sc.nextInt();
                b.h = sc.nextInt();
                b.num = i + 1;
                buildings.add(b);

                xs.add(b.x);
                xs.add(b.x + b.w);
            }
            ArrayList<Integer> xsList = new ArrayList<>();
            xsList.addAll(xs);
            Collections.sort(xsList);
            ArrayList<Building> visibles = new ArrayList<>();            
            for (int i = 0; i < n; i++) {
                Building b = buildings.get(i);
                for (int j = 0; j < xsList.size() - 1; j++) {
                    double mx = (xsList.get(j) + xsList.get(j + 1)) / 2.0;
                    if (b.visibleAt(buildings, mx)) {
                        visibles.add(b);
                        break;
                    }
                }
            }

            visibles.sort(new Comparator<Building>() {
                @Override
                public int compare(Main.Building o1, Main.Building o2) {
                    if (o1.x != o2.x) {
                        return o1.x - o2.x;                 
                    } else {
                        return o1.y - o2.y;
                    }
                }
            });

            if (num > 1) {
                System.out.println();
            }
            System.out.println(String.format("For map #%d, the visible buildings are numbered as follows:", num));
            for (int i = 0; i < visibles.size(); i++) {
                Building v = visibles.get(i);
                if (i != 0) {
                    System.out.print(" ");
                }
                System.out.print(v.num);
            }
            num++;
            System.out.println();
        }
       
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("2.out");
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
