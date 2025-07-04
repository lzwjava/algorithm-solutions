package com.lzw.solutions.uva.p11462;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    String readLine(BufferedReader br) throws IOException {
        while (true) {
            String str = br.readLine();
            if (str == null) {
                throw new IOException("end");
            }
            if (str.isEmpty()) {
                continue;
            }
            return str;
        }
    }

    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String nstr = br.readLine();
            int n = Integer.parseInt(nstr);
            if (n == 0) {
                break;
            }
            String line = br.readLine();
            String numStrs[] = line.split(" ");
            assert (numStrs.length == n);
            int nums[] = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(numStrs[i]);
            }
            Arrays.sort(nums);
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    System.out.print(" ");
                }
                System.out.print(nums[i]);
            }
            System.out.println();
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {

        try {
            new Main().solve();
        } catch (IOException e) {
        }
    }
}
