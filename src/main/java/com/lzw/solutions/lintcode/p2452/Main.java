package com.lzw.solutions.lintcode.p2452;

import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.IntConsumer;

public class Main {

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            PrintStream ps = new PrintStream(outputPath);

            int n = Integer.parseInt(in.nextLine());

            IntConsumer intConsumer = (x -> {
                try {
                    int num = x % 30;
                    String name = Thread.currentThread().getName();
                    if ((num >= 11 && num <= 30 || num == 0) && "main".equals(name)) {
                        throw new Exception("You should call this method in a sub thread when you passed x=" + x);
                    }
                    if (num >= 1 && num <= 10 && !"main".equals(name)) {
                        throw new Exception("You should call this method in main thread when you passed x=" + x);
                    }
                    ps.write(String.valueOf(x).getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Solution solution = new Solution();
            solution.printNumberInMainSubThread(n, intConsumer);
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
