package com.algorithm.solutions.uva.p195;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Main {

    BufferedReader in;
    PrintWriter out;
    String s;
    HashSet<String> words;
    char schs[];
    boolean vis[];
    char chs[];

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    void permutation(int i) {
        if (i == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < schs.length; j++) {
                sb.append(schs[j]);
            }
            String fs = sb.toString();
            words.add(fs);
            return;
        }
        for (int j = 0; j < chs.length; j++) {
            int c1 = 0;
            int c2 = 0;
            for (int k = 0; k < i; k++) {
                if (schs[k] == chs[j]) {
                    c1++;
                }
            }
            for (int k = 0; k < s.length(); k++) {
                if (s.charAt(k) == chs[j]) {
                    c2++;
                }
            }
            if (c1 < c2) {
                schs[i] = chs[j];
                permutation(i + 1);
            }
        }
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            s = in.readLine();
            words = new HashSet<>();
            schs = new char[s.length()];
            vis = new boolean[s.length()];
            char tchs[] = s.toCharArray();
            HashSet<Character> charSet = new HashSet<>();
            for (char ch : tchs) {
                charSet.add(ch);
            }
            chs = new char[charSet.size()];
            int i = 0;
            for (Character ch : charSet) {
                chs[i] = ch;
                i++;
            }
            Arrays.sort(chs);

            permutation(0);
            ArrayList<String> list = new ArrayList<>();
            list.addAll(words);
            Collections.sort(list,new Comparator<String>(){
                @Override
                public int compare(String o1, String o2) {
                    for (int i = 0; i < o1.length(); i++) {
                        char c1 = o1.charAt(i);
                        char c2 = o2.charAt(i);                
                        if (c1 == c2) {
                            continue;
                        } else {
                            char lc1 = Character.toLowerCase(c1);
                            char lc2 = Character.toLowerCase(c2);
                            if (lc1 != lc2) {
                                return Character.compare(lc1, lc2);
                            } else {
                                return Character.compare(c1, c2);
                            }
                        }
                    }
                    return 0;
                }                
            } );
            for (String w : list) {
                out.append(w).append('\n');
            }
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
