package com.algorithm.solutions.uva.pgen_sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        out.append(String.format("%d\n", 1000));
        out.flush();
        out.close();
    }
}
