package com.algorithm.solutions.uva.p10034;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    float dist[][];
    float minDist;
    int minIds[];

    class Distance implements Comparable<Distance> {
        float dist;
        int a;
        int b;

        Distance(float dist, int a, int b) {
            this.dist = dist;
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Main.Distance o) {
            return Float.compare(dist, o.dist);
        }
    }

    class Point {
        float x;
        float y;

        Point() {
        }

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
    
    float distOfPoints(Point a, Point b) {
        float dx = Math.abs(a.x - b.x);
        float dy = Math.abs(a.y - b.y);
        float d;
        if (dx < 1e-8) {
            d = dy;
        } else if (dy < 1e-8) {
            d = dx;
        } else {
            d = (float) Math.sqrt(dx * dx + dy * dy);
        }
        return d;
    }
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        boolean first = true;
        while (t > 0) {
            int n = sc.nextInt();        
            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) {
                float x = sc.nextFloat();
                float y = sc.nextFloat();
                pts[i] = new Point(x, y);
            }
            if (first) {
                first = false;
            } else {
                System.out.println();
            }

            if (n == 1) {
                System.out.println(String.format("%.2f", 0.0));
                t--;
                continue;
            }

            dist = new float[n][n];
            ArrayList<Distance> ds = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    float len = distOfPoints(pts[i], pts[j]);
                    Distance d = new Distance(len, i, j);
                    ds.add(d);
                }
            }

            boolean connected[] = new boolean[n];
            Collections.sort(ds);

            Distance d0 = ds.get(0);
            float sum = 0;
            sum += d0.dist;
            connected[d0.a] = true;
            connected[d0.b] = true;
            for (int i = 0; i < n - 2; i++) {
                for (int j = 0; j < ds.size(); j++) {
                    Distance d = ds.get(j);
                    if (connected[d.a] == !connected[d.b]) {
                        connected[d.a] = true;
                        connected[d.b] = true;
                        sum += d.dist;
                        break;
                    }
                }
            }
            System.out.println(String.format("%.2f", sum));
            t--;
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

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
