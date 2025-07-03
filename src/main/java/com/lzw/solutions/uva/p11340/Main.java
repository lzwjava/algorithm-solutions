package com.lzw.solutions.uva.p11340;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
   
    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        int n = Integer.parseInt(br.readLine());
        while (n > 0) {
            int k = Integer.parseInt(br.readLine());
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < k; i++) {
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line);
                String s = st.nextToken();
                int cents = Integer.parseInt(st.nextToken());
                Character ch = s.charAt(0);
                map.put(ch, cents);
            }
            int m = Integer.parseInt(br.readLine());
            int total = 0;
            for (int i = 0; i < m; i++) {
                String s = br.readLine();
                for (int j = 0; j < s.length(); j++) {
                    Character ch = s.charAt(j);
                    Integer cents = map.get(ch);
                    if (cents != null) {
                        total += cents;
                    }
                }
            }
            System.out.println(String.format("%.2f$", total * 1.0 / 100));
            n--;
        }
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
