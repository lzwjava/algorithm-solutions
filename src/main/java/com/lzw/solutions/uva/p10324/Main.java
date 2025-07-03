package com.lzw.solutions.uva.p10324;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class Main {
   
    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = 1;
        while (true) {
            String str = br.readLine();
            if (str == null || str.isEmpty()) {
                break;
            }
            int n = Integer.parseInt(br.readLine());
            System.out.println(String.format("Case %d:", caseNum));
            int len = str.length();
            int data[] = new int[len];
            data[0] = 0;
            for (int i = 1; i < len; i++) {
                if (str.charAt(i) == str.charAt(i - 1)) {
                    data[i] = data[i - 1];
                } else {
                    data[i] = data[i - 1] + 1;
                }
            }
            for (int m = 0; m < n; m++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());           
                if (data[i] == data[j]) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
            caseNum++;
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("2.out");
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
