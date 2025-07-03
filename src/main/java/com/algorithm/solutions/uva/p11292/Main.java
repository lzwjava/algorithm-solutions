package com.algorithm.solutions.uva.p11292;

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
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }
            int[] diameters = new int[n];
            for (int i = 0; i < n; i++) {
                diameters[i] = Integer.parseInt(in.readLine().trim());
            }
            int[] heights = new int[m];
            for (int i = 0; i < m; i++) {
                heights[i] = Integer.parseInt(in.readLine().trim());
            }
            Arrays.sort(diameters);
            Arrays.sort(heights);
            int j = 0;
            int ans = 0;
            int i;
            for (i = 0; i < n; i++) {
                boolean ok = false;
                do {
                    if (heights[j] >= diameters[i]) {
                        // kill
                        ans += heights[j];
                        ok = true;
                        break;
                    } else {
                        j++;
                    }
                } while (j < m);
                if (!ok) {
                    break;
                }
                j++;
                if (j == m && i != n - 1) {
                    break;
                }
            }
            if (i == n) {
                out.append(String.format("%d\n", ans));
            } else {
                out.append("Loowater is doomed!\n");
            }
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
