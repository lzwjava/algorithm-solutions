package com.lzw.solutions.lintcode.p2416;

import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            PrintStream ps = new PrintStream(outputPath);

            int n = Integer.parseInt(in.nextLine());
            String str = in.nextLine();
            String[] split = (str.substring(1, str.length() - 1)).split(",");
            int arr[] = new int[split.length];
            int i = 0;
            for (String s : split) {
                arr[i++] = Integer.parseInt(s.trim());
            }

            Solution solution = new Solution();
            int rs[] = solution.quickSortInThreadings(n, arr);

            if (!QuickSort.hasBeenCalledInSubThread) {
                throw new Exception("You should call sort_range in a sub thread.");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int index = 0; index < rs.length; index++) {
                if (index != rs.length - 1) sb.append(rs[index] + ", ");
                else sb.append(rs[index] + "]");
            }
            ps.write((sb.toString()).getBytes(StandardCharsets.UTF_8));
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
