package com.lzw.solutions.uva.p10789;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }
   
    void solve() throws IOException {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 2001; i++) {
            int si = (int) Math.sqrt(i);
            boolean ok = true;
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj <= si) {
                    if (i % pj == 0) {
                        ok = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }        
        int t = Integer.parseInt(in.readLine());
        int caseNum = 1;
        while (t > 0) {
            String s = in.readLine();
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                Integer count = map.get(ch);
                if (count == null) {
                    count = 0;
                }
                count++;
                map.put(ch, count);
            }
            ArrayList<Character> ans = new ArrayList<>();
            for (Character ch : map.keySet()) {
                Integer count = map.get(ch);
                int index = Collections.binarySearch(primes, count);
                if (index >= 0) {
                    ans.add(ch);
                }
            }
            Collections.sort(ans);
            out.append(String.format("Case %d: ", caseNum));
            if (ans.size() == 0) {
                out.append("empty");              
            } else {
                for (Character ch : ans) {
                    out.append(ch);
                }
            }
            out.append('\n');
            caseNum++;
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

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
