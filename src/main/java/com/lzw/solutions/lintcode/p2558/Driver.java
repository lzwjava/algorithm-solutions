package com.lzw.solutions.lintcode.p2558;

import java.io.*;
import java.util.function.Consumer;

class ReleaseThread extends Thread{
    private int n;
    private boolean isOxygen;
    private H2O h2o;
    private PrintWriter writer;
    
    public ReleaseThread(int n, boolean isOxygen, H2O h2o, PrintWriter writer) {
        super();
        this.n = n;
        this.isOxygen = isOxygen;
        this.h2o = h2o;
        this.writer = writer;
    }
    
    @Override
    public void run() {
        Consumer<Integer> releaseHydrogen = (x) -> {
            try {
                writer.write("H");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        };

        Consumer<Integer> releaseOxygen = (x) -> {
            try {
                writer.write("O");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        };
        
        if (isOxygen) {
            for(int i = 0; i < n; i++) {
                h2o.oxygen(releaseOxygen);
            }
        } else {
            for(int i = 0; i < n; i++) {
                h2o.hydrogen(releaseHydrogen);
            }
        }
    
    }
}

class Driver extends BaseDriver {
    public Driver(String inputPath, String outputPath) throws IOException {
        super(inputPath, outputPath);
    }

    public String doSpecialJudge(String inputData, String outputData) {
        String[] params = inputData.split(" ");
        int n = Integer.parseInt(params[1]);
        int counts = 0;
        for (int i = 0; i < n; i++) {
            String substr = outputData.substring(i * 3, i * 3 + 3);
            if (!substr.equals("HHO") && !substr.equals("HOH") && !substr.equals("OHH")) {
                return substr + " is not a h2o";
            }
            counts++;
        }
        if(counts != n)
            return "You only produced " + String.valueOf(counts) + " h2o.\nWe need " + String.valueOf(n) + " h2o";
        return "";
    }

    public void run() throws IOException {
        String inputData = scanner.nextLine();
        String[] params = inputData.split(" ");
        int h = Integer.parseInt(params[0]);
        int o = Integer.parseInt(params[1]);

        H2O solution = new H2O();
        
        ReleaseThread hThread = new ReleaseThread(h, false, solution, writer);
        ReleaseThread oThread = new ReleaseThread(o, true, solution, writer);
        
        hThread.start();
        oThread.start();
        try {
            hThread.join();
            oThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        writer.close();

        specialJudge(inputData);
    }
}