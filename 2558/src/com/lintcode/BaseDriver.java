package com.lintcode;

import java.io.File;
import java.io.*;
import java.util.*;

abstract class BaseDriver {
    public String inputPath, outputPath;
    public Scanner scanner;
    public PrintWriter writer;

    public BaseDriver(String inputPath, String outputPath) throws IOException {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.scanner = new Scanner(new FileReader(inputPath));
        this.writer = new PrintWriter(outputPath, "UTF-8");
    }

    private String readFile(String path) throws IOException {
        Scanner scanner = new Scanner(new FileReader(path));
        scanner.useDelimiter("\\Z");
        return scanner.next();
    }

    public void specialJudge(String inputData, String outputData) throws IOException {
        if (inputData == null) {
            inputData = readFile(inputPath);
        }
        if (outputData == null) {
            outputData = readFile(outputPath);
        }
        String spjPath = outputPath.replace(".out", ".spj");
        PrintWriter spjWriter = new PrintWriter(spjPath, "UTF-8");
        String failedReason = doSpecialJudge(inputData, outputData);
        spjWriter.write(failedReason);
        spjWriter.close();
    }

    public void specialJudge(String inputData) throws IOException {
        specialJudge(inputData, null);
    }

    public void specialJudge() throws IOException {
        specialJudge(null, null);
    }

    public abstract String doSpecialJudge(String inputData, String outputData);
}
