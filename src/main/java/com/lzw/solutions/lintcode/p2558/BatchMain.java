package com.lzw.solutions.lintcode.p2558;

import java.io.*;
import java.io.File;
import java.util.*;

class BatchMain {
    public static List<Integer> getSortedDataIds(String inputDir) {
        File[] inputFiles = new File(inputDir).listFiles();
        List<Integer> dataIds = new ArrayList<>();

        for (File file : inputFiles) {
            if (!file.getPath().endsWith(".in")) {
                continue;
            }
            String name = file.getName();
            String idStr = name.substring(0, name.indexOf("."));
            dataIds.add(Integer.parseInt(idStr));
        }
        Collections.sort(dataIds);

        return dataIds;
    }

    public static void main(String[] args) {
        try {
            String inputDir = args[0];
            String outputDir = args[1];

            for (Integer dataId : getSortedDataIds(inputDir)) {
                String inputPath = inputDir + "/" + dataId + ".in";
                String outputPath = outputDir + "/" + dataId + ".out";

                PrintStream stdout = new PrintStream(outputPath.replace(".out", ".stdout"));
                System.setOut(stdout);
                PrintStream stderr = new PrintStream(outputPath.replace(".out", ".stderr"));
                System.setErr(stderr);

                Driver driver = new Driver(inputPath, outputPath);
                driver.run();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
