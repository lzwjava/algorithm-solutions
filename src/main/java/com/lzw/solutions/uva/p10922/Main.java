package com.lzw.solutions.uva.p10922;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] strToNums(String s) {
        int len = s.length();
        int nnums[] = new int[len];
        for (int i = 0; i < len; i++) {
            nnums[i] = s.charAt(i) - '0';
        }
        return nnums;
    }

    class Result {
        boolean isMultiple;
        int depth;

        Result() {
        }
        
        Result(boolean isMultiple, int depth) {
            this.isMultiple = isMultiple;
            this.depth = depth;
        }
    }
    
    Result cal(int nums[], int depth) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum < 10 && sum >= 0) {            
            if (sum % 9 == 0) {
                return new Result(true, depth);
            } else {
                return new Result(false, depth);
            }
        } else {
            String s = String.format("%d", sum);            
            int nnums[] = strToNums(s);
            return cal(nnums, depth + 1);
        }
    }
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line.equals("0")) {
                break;
            }
            int nums[] = strToNums(line);
            Result res = cal(nums, 0);
            if (res.isMultiple) {
                out.append(String.format("%s is a multiple of 9 and has 9-degree %d.\n", line, res.depth+1));
            } else {
                out.append(String.format("%s is not a multiple of 9.\n", line));
            }
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
