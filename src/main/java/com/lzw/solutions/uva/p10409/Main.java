package com.lzw.solutions.uva.p10409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Dice {
        int[] nums;
        // 0     1      2     3      4       5
        // top, north, west, bottom, south, east

        Dice() {
            nums = new int[6];
            nums[0] = 1;
            nums[1] = 2;
            nums[2] = 3;
            nums[3] = 6;
            nums[4] = 5;
            nums[5] = 4;
        }

        private void north() {
            int[] newNums = new int[6];
            newNums[1] = nums[0];
            newNums[3] = nums[1];
            newNums[2] = nums[2];
            newNums[4] = nums[3];
            newNums[0] = nums[4];
            newNums[5] = nums[5];

            nums = newNums;
        }

        private void south() {
            int[] newNums = new int[6];
            newNums[4] = nums[0];
            newNums[0] = nums[1];
            newNums[2] = nums[2];
            newNums[1] = nums[3];
            newNums[3] = nums[4];
            newNums[5] = nums[5];

            nums = newNums;
        }

        private void west() {
            int[] newNums = new int[6];
            newNums[2] = nums[0];
            newNums[1] = nums[1];
            newNums[3] = nums[2];
            newNums[5] = nums[3];
            newNums[4] = nums[4];
            newNums[0] = nums[5];

            nums = newNums;
        }

        private void east() {
            int[] newNums = new int[6];
            newNums[5] = nums[0];
            newNums[1] = nums[1];
            newNums[0] = nums[2];
            newNums[2] = nums[3];
            newNums[4] = nums[4];
            newNums[3] = nums[5];

            nums = newNums;
        }

        private void move(String command) {
            switch (command) {
                case "north":
                    north();
                    break;
                case "east":
                    east();
                    break;
                case "south":
                    south();
                    break;
                case "west":
                    west();
                    break;
            }
        }
    }

    void solve() throws IOException {
        while (true) {
            Integer n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            String[] commands = new String[n];
            for (int i = 0; i < n; i++) {
                commands[i] = in.readLine();
            }
            Dice dice = new Dice();
            for (int i = 0; i < n; i++) {
                dice.move(commands[i]);
            }
            out.append(String.format("%d\n", dice.nums[0]));
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
