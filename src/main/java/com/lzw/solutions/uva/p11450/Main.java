package com.lzw.solutions.uva.p11450;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Garment {
        int k;
        int prices[];
    }

    Garment[] garments;
    static int maxM = 205;
    static int maxC = 25;

    int[][] map;    
    
    int dp(int m, int cur, int c) {
        if (cur == c) {
            return 0;
        }
        if (m == 0) {
            return Integer.MIN_VALUE;
        }
        int cache = map[cur][m];
        if (cache != -1) {
            return cache;
        }
        int ans = Integer.MIN_VALUE;
        Garment garment = garments[cur];
        for (int i = 0; i < garment.k; i++) {
            if (m >= garment.prices[i]) {
                // buy it
                int v = dp(m - garment.prices[i], cur + 1, c) + garment.prices[i];
                if (v > ans) {
                    ans = v;
                }
            }
        }
        map[cur][m] = ans;        
        return ans;
    }

   
    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            garments = new Garment[c];
            for (int i = 0; i < c; i++) {
                s = in.readLine();
                st = new StringTokenizer(s);
                int k = Integer.parseInt(st.nextToken());
                int[] prices = new int[k];
                for (int j = 0; j < k; j++) {
                    prices[j] = Integer.parseInt(st.nextToken());
                }
                Garment garment = new Garment();
                garment.k = k;
                garment.prices = prices;
                garments[i] = garment;
            }
            map = new int[maxC][maxM];
            for (int i = 0; i < maxC; i++) {
                Arrays.fill(map[i], -1);
            }
            int ans = dp(m, 0, c);
            if (ans >= 0) {
                out.append(String.format("%d\n", ans));                
            } else {
                out.append(String.format("no solution\n"));
            }
            n--;
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
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
