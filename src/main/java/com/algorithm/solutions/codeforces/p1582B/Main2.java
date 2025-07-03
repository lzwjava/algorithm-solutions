package com.algorithm.solutions.codeforces.p1582B;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {

    BufferedReader in;
    PrintWriter out;

    Main2() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void subSequenceSum(ArrayList<ArrayList<Long>> ans, long a[], ArrayList<Long> temp, long k, int start)
    {      
        if (start > a.length || k < 0) {
            return;
        }
        if (k == 0) {
            ans.add(new ArrayList<Long>(temp));
            return;
        }
        for (int i = start; i < a.length; i++) {
            temp.add(a[i]);
            subSequenceSum(ans, a, temp, k - a[i], i + 1);
            temp.remove(temp.size() - 1);
        }
    }


    long[] nums;
    long total;
    int count;
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            nums = new long[n];
            total = 0;
            for (int i = 0; i < n; i++) {
                nums[i] = Long.parseLong(st.nextToken());
                total += nums[i];
            }
            
            ArrayList<ArrayList<Long>> ans;
            ans= new ArrayList<ArrayList<Long>>();
            subSequenceSum(ans, nums, new ArrayList<Long>(), total - 1, 0);                
            
            count = ans.size();
            out.append(String.format("%d\n", count));                      
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
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main2 main = new Main2();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
