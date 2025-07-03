package com.lzw.solutions.uva.p11809;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    class Part {
        int m;
        int e;
    }

    double cal(int m, int e) {
        double mv = 1 - Math.pow(2, -1 - m);
        double ev = Math.pow(2, e) - 1;
        double v = Math.log10(mv) + ev * Math.log10(2);
        return v;
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        HashMap<Double, Part> maps = new HashMap<>();
        for (int m = 0; m <= 9; m++) {
            for (int e = 1; e <= 30; e++) {
                double v = cal(m, e);
                Part p = new Part();
                p.m = m;
                p.e = e;
                maps.put(v, p);
            }
        }
        while (sc.hasNext()) {
            String floatNum = sc.next();
            int eIndex = floatNum.indexOf('e');
            String mantissa = floatNum.substring(0, eIndex);
            String exponent = floatNum.substring(eIndex + 1);
            if (mantissa.equals("0") && exponent.equals("0")) {
                break;
            }

            double v = Math.log10(Double.valueOf(mantissa)) + Double.valueOf(exponent);
            boolean ok = false;
            Part target = new Part();
            for (Double mv : maps.keySet()) {
                if (Math.abs(mv - v) < 1e-4) {
                    Part p = maps.get(mv);
                    ok = true;
                    target = p;
                    break;
                }
            }
            if (ok) {
                System.out.println(String.format("%d %d", target.m, target.e));
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
