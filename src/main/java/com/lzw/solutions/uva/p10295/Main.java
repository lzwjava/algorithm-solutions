package com.lzw.solutions.uva.p10295;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }
   
    void solve() throws IOException {
        String line = in.readLine();
        StringTokenizer st = new StringTokenizer(line);
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            line = in.readLine();
            st = new StringTokenizer(line);
            String word = st.nextToken();
            int dollar = Integer.parseInt(st.nextToken());
            map.put(word, dollar);
        }
        for (int i = 0; i < n; i++) {
            ArrayList<String> lines = new ArrayList<>();
            while (true) {
                String s = in.readLine();
                if (s.equals(".")) {
                    break;
                }
                lines.add(s);
            }
            int total = 0;
            for (String s : lines) {
                String[] words = s.split("\\s+");
                for (String word : words) {
                    Integer dollar = map.get(word);
                    if (dollar != null) {
                        total += dollar;
                    }
                }
            }
            out.append(String.format("%d\n", total));
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
