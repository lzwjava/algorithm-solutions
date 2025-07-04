package com.lzw.solutions.uva.p12657;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main1 {

    int indexInNums(int nums[], int v) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == v) {
                return i;
            }
        }
        return -1;
    }

    void print(int nums[]) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    void work() {
        int caseNum = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (; ; ) {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str == null || str.isEmpty()) {
                break;
            }
            String[] strs = str.trim().split("\\s+");
            if (strs.length != 2) {
                break;
            }
            int n = Integer.parseInt(strs[0]);
            int m = Integer.parseInt(strs[1]);
            int nums[] = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
            }
            for (int i = 0; i < m; i++) {
                try {
                    str = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                strs = str.trim().split("\\s+");
                assert (strs.length == 3 || strs.length == 1);

                int command = Integer.parseInt(strs[0]);
                if (command == 4) {
                    for (int j = 0; j < n / 2; j++) {
                        int tmp = nums[j];
                        nums[j] = nums[n - 1 - j];
                        nums[n - 1 - j] = tmp;
                    }
                } else {
                    int x = Integer.parseInt(strs[1]);
                    int y = Integer.parseInt(strs[2]);
                    int xIndex = indexInNums(nums, x);
                    int yIndex = indexInNums(nums, y);
                    if (command == 3) {
                        int tmp = nums[xIndex];
                        nums[xIndex] = nums[yIndex];
                        nums[yIndex] = tmp;
                    } else {
                        if (command == 1) {
                            // move x to the left of y
                            if (xIndex + 1 == yIndex) {
                                // do nothing
                            } else {
                                if (xIndex < yIndex) {
                                    for (int j = xIndex; j <= yIndex - 2; j++) {
                                        nums[j] = nums[j + 1];
                                    }
                                    nums[yIndex - 1] = x;
                                } else {
                                    // xIndex > yIndex
                                    for (int j = xIndex - 1; j >= yIndex; j--) {
                                        nums[j + 1] = nums[j];
                                    }
                                    nums[yIndex] = x;
                                }
                            }
                        } else {
                            // move x to the right of y
                            if (xIndex == yIndex + 1) {
                                // do nothing
                            } else {
                                if (xIndex < yIndex) {
                                    for (int j = xIndex; j <= yIndex - 1; j++) {
                                        nums[j] = nums[j + 1];
                                    }
                                    nums[yIndex] = x;
                                } else {
                                    // xIndex > yIndex
                                    for (int j = xIndex - 1; j >= yIndex + 1; j--) {
                                        nums[j + 1] = nums[j];
                                    }
                                    nums[yIndex + 1] = x;
                                }
                            }
                        }
                    }
                }
                print(nums);
            }
            System.out.print(String.format("Case %d: ", caseNum));
            long sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i % 2 == 0) {
                    sum += nums[i];
                }
            }
            System.out.println(sum);
            caseNum++;
        }
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

        new Main1().work();
    }
}
