package com.algorithm.solutions.uva.p477;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Point {
        double x, y;
        
        Point() {
        }        
    }
    
    class Figure {
        char type;
        Point upperLeft;
        Point lowerRight;
        Point center;
        double radius;

        Figure() {
            upperLeft = new Point();
            lowerRight = new Point();
            center = new Point();
        }
    }
   
    void solve() throws IOException {
        ArrayList<Figure> figures = new ArrayList<>();
        while (true) {
            String line = in.readLine();
            if (line.equals("*")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            Figure f = new Figure();
            f.type = st.nextToken().charAt(0);
            if (f.type == 'r') {
                double cords[] = new double[4];
                for (int i = 0; i < 4; i++) {
                    cords[i] = Double.parseDouble(st.nextToken());
                }
                f.upperLeft.x = cords[0];
                f.upperLeft.y = cords[1];
                f.lowerRight.x = cords[2];
                f.lowerRight.y = cords[3];                
            } else {
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                double r = Double.parseDouble(st.nextToken());
                f.center.x = x;
                f.center.y = y;
                f.radius = r;
            }
            figures.add(f);
        }
        ArrayList<Point> points = new ArrayList<>();
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            String a = st.nextToken();
            String b = st.nextToken();
            if (a.equals("9999.9") && b.equals("9999.9")) {
                break;
            }
            Point p = new Point();
            p.x = Double.parseDouble(a);
            p.y = Double.parseDouble(b);
            points.add(p);
        }
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            boolean found = false;
            for (int j = 0; j < figures.size(); j++) {
                Figure f = figures.get(j);
                boolean contained = contain(f, p);
                if (contained) {
                    found = true;
                    out.append(String.format("Point %d is contained in figure %d\n", i + 1, j + 1));
                }
            }
            if (!found) {
                out.append(String.format("Point %d is not contained in any figure\n", i + 1));
            }
        }
    }

    boolean contain(Figure f, Point p) {
        if (f.type == 'r') {
            if (p.x >= f.upperLeft.x && p.x <= f.lowerRight.x &&
                    p.y >= f.lowerRight.y && p.y <= f.upperLeft.y) {
                return true;
            } else {
                return false;
            }
        } else {
            double dist = Math.sqrt(Math.pow(f.center.x - p.x, 2) + Math.pow(f.center.y - p.y, 2));
            if (dist < f.radius) {
                return true;
            } else {
                return false;
            }
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
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
