package com.lzw.solutions.uva.p11479;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int caseNum = 1;
        while (t > 0) {
            long nums[] = new long[3];
            for (int i = 0; i < 3; i++) {
                nums[i] = sc.nextInt();
            }
            Arrays.sort(nums);
            System.out.print(String.format("Case %d: ", caseNum));
            if (nums[0] < 0 || nums[0] + nums[1] <= nums[2]) {
                System.out.println("Invalid");
            } else if (nums[0] == nums[1] && nums[1] == nums[2]) {
                System.out.println("Equilateral");                
            } else if (nums[0] == nums[1] || nums[1] == nums[2]) {
                System.out.println("Isosceles");
            } else {
                System.out.println("Scalene");
            }
            caseNum++;
            t--;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
