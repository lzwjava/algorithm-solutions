package com.algorithm.solutions.uva.p10487;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int nums[] = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(in.readLine());
            }
            ArrayList<Integer> sums = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    int sum = nums[i] + nums[j];
                    sums.add(sum);
                }
            }
            Collections.sort(sums);
            
            out.append(String.format("Case %d:\n", caseNum));

            int m = Integer.parseInt(in.readLine());            
            for (int i = 0; i < m; i++) {
                int q = Integer.parseInt(in.readLine());
                int index = Collections.binarySearch(sums, q);
                int ans = 0;
                if (index >= 0) {
                    ans = sums.get(index);
                } else {
                    int gap = Integer.MAX_VALUE;
                    int insert = -(index + 1);
                    if (insert == 0) {
                        ans = sums.get(0);
                    } else if (insert == sums.size()) {
                        ans = sums.get(sums.size() - 1);
                    } else {
                        int a = sums.get(insert);
                        int b = sums.get(insert - 1);
                        if (Math.abs(a - q) < Math.abs(b - q)) {
                            ans = a;
                        } else {
                            ans = b;
                        }
                    }
                }
                out.append(String.format("Closest sum to %d is %d.\n", q, ans));
            }
            caseNum++;
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
