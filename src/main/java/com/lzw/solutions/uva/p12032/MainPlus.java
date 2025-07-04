package com.lzw.solutions.uva.p12032;

import java.io.*;
import java.util.StringTokenizer;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int u = 0; u < t; u++) {
            int n = Integer.parseInt(in.readLine());
            int[] nums = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            for (int v = 1; ; v++) {
                int p = 0;
                int k = v;
                boolean ok = true;
                for (int i = 0; i < n; i++) {
                    int d = nums[i] - p;
                    if (d < k) {
                        // remains
                    } else if (d == k) {
                        k--;
                    } else {
                        ok = false;
                        break;
                    }
                    p = nums[i];
                }
                if (ok) {
                    out.append(String.format("Case %d: %d\n", u + 1, v));
                    break;
                }
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        MainPlus main = new MainPlus();
        main.solve();
        main.close();
    }
}
