package com.lzw.solutions.uva.p476;

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
        float x;
        float y;

        Point() {

        }

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
    
    class Rect {
        Point upperLeft;
        Point lowerRight;

        Rect(){

        }

        Rect(float x1, float y1, float x2, float y2) {
            Point u = new Point(x1, y1);
            Point v = new Point(x2, y2);
            this.upperLeft = u;
            this.lowerRight = v;
        }

        boolean contains(Point p) {
            if (p.x > upperLeft.x && p.x < lowerRight.x && 
                    p.y > lowerRight.y && p.y < upperLeft.y) {                
                return true;
            }
            return false;
        }
    }
   
    void solve() throws IOException {
        ArrayList<Rect> rects = new ArrayList<>();
        while (true) {
            String s = in.readLine();
            if (s.equals("*")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            st.nextToken();

            float x1 = Float.parseFloat(st.nextToken());
            float y1 = Float.parseFloat(st.nextToken());
            float x2 = Float.parseFloat(st.nextToken());
            float y2 = Float.parseFloat(st.nextToken());
            Rect r = new Rect(x1, y1, x2, y2);
            rects.add(r);
        }
        int pointNum =1;
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            if (s1.equals("9999.9") && s2.equals("9999.9")) {
                break;
            }
            float x1 = Float.parseFloat(s1);
            float y1 = Float.parseFloat(s2);
            Point p = new Point(x1, y1);
            boolean contained = false;
            for (int i = 0; i < rects.size(); i++) {
                Rect r = rects.get(i);
                if (r.contains(p)) {
                    contained = true;
                    out.append(String.format("Point %d is contained in figure %d\n", pointNum, i + 1));
                }
            }
            if (!contained) {
                out.append(String.format("Point %d is not contained in any figure\n", pointNum));                
            }
            pointNum++;
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
