package com.algorithm.solutions.uva.p524;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    boolean isp[];

    PrintWriter out;

    void permutation(int nums[], boolean vis[], int i, int n) {
        if (i == n) {
            if (isp[nums[n - 1] + nums[0]]) {
                for (int k = 0; k < n; k++) {
                    if (k != 0) {
                        out.append(" ");
                    }
                    out.append(nums[k]+"");
                }
                out.append("\n");
            }
            return;
        }
        for (int k = 1; k <= n; k++) {
            if (!vis[k]) {
                if (isp[k + nums[i - 1]]) {
                    nums[i] = k;
                    vis[k] = true;
                    permutation(nums, vis, i + 1, n);
                    vis[k] = false;
                }
            }
        }
    }
   
    void solve() throws IOException {
        int size = 35;
        isp = new boolean[size];
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i < size; i++) {
            boolean ok = true;
            int si = (int) Math.sqrt(i);
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }
        for (Integer p : primes) {
            isp[p] = true;
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = 1;
        out = new PrintWriter(System.out);
        boolean first = true;
        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }
            int n = Integer.parseInt(s);
            if (first) {
                first = false;
            } else {
                out.append("\n");
            }
            out.append(String.format("Case %d:\n", caseNum));
            boolean vis[] = new boolean[n + 1];
            int nums[] = new int[n];
            nums[0] = 1;
            vis[1] = true;
            permutation(nums, vis, 1, n);
            caseNum++;
        }
        out.flush();
        br.close();
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

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
