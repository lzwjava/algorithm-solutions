package com.lzw.solutions.uva.p10192;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String a;
    String b;
    int map[][];
    
    int dp(int i, int j) {
        if (i == a.length() || j == b.length()) {
            return 0;
        }
        if (map[i][j] != -1) {
            return map[i][j];
        }
        char cha = a.charAt(i);
        char chb = b.charAt(j);
        int ans;
        if (cha == chb) {
            ans= 1 + dp(i+1, j+1);
        } else {
            int c1 = dp(i + 1, j);
            int c2 = dp(i, j + 1);
            ans = Math.max(c1, c2);
        }
        map[i][j] = ans;
        return ans;
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            a = in.readLine();
            if (a.equals("#")) {
                break;
            }            
            b = in.readLine();
            map = new int[a.length()][b.length()];
            for (int i = 0; i < a.length(); i++) {
                Arrays.fill(map[i], -1);
            }
            int ans = dp(0, 0);            
            out.append(String.format("Case #%d: you can visit at most %d cities.\n", caseNum, ans));
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
