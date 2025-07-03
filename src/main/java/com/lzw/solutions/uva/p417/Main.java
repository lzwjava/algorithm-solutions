package com.lzw.solutions.uva.p417;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    ArrayList<String> words;
    
    void permutation(int nums[], int i, int n) {
        if (i == n) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nums.length; j++) {
                char ch = (char) (nums[j] + 'a');
                sb.append(ch);
            }
            words.add(sb.toString());
            return;
        }
        int start;
        if (i == 0) {
            start = 0;
        } else {
            start = nums[i - 1] + 1;
        }
        int end = 25;
        end -= (n - i-1);        
        for (int j = start; j <= end; j++) {
            nums[i] = j;
            permutation(nums, i+1, n);
        }
    }
   
    void solve() throws IOException {
        words = new ArrayList<>();
        for (int len = 1; len <= 5; len++) {
            int nums[] = new int[len];
            permutation(nums, 0, len);
        }
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            // int index = Collections.binarySearch(words, s);
            int index = words.indexOf(s);
            if (index < 0) {
                out.append("0\n");
            } else {
                out.append(String.format("%d\n", index+1));
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
