package com.lzw.solutions.uva.p11455;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine().trim());
        while (t > 0) {
            int[] nums = new int[4];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 4; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(nums);
            boolean allSame = true;
            for (int i = 1; i < 4; i++) {
                if (nums[i] != nums[0]) {
                    allSame = false;
                    break;
                }
            }
            if (allSame) {
                out.append("square\n");
            } else {
                boolean rectangle = nums[0] == nums[1] && nums[2] == nums[3];
                if (rectangle) {
                    out.append("rectangle\n");
                } else {
                    long sum = (long) nums[0] + nums[1] + nums[2];
                    if (nums[3] >= sum) {
                        out.append("banana\n");
                    } else {
                        out.append("quadrangle\n");
                    }
                }
            }
            t--;
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
