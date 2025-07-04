package com.lzw.solutions.uva.p10066;

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
    int nums1[];
    int nums2[];
    int map[][];

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int dp(int n1, int n2) {
        if (n1 == 0 && n2 == 0) {
            return 0;
        }
        if (n1 == 0) {
            return 0;
        } else if (n2 == 0) {
            return 0;
        }
        if (map[n1][n2] != -1) {
            return map[n1][n2];
        }
        int ans;
        if (nums1[n1] == nums2[n2]) {
            int v = dp(n1 - 1, n2 - 1);
            ans = v + 1;
        } else {
            int v1 = dp(n1 - 1, n2);
            int v2 = dp(n1, n2 - 1);
            ans = Math.max(v1, v2);
        }
        map[n1][n2] = ans;
        return ans;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if (n1 == 0 && n2 == 0) {
                break;
            }
            s = in.readLine();
            st = new StringTokenizer(s);
            nums1 = new int[n1 + 1];
            for (int i = 0; i < n1; i++) {
                int num = Integer.parseInt(st.nextToken());
                nums1[n1 - i] = num;
            }
            s = in.readLine();
            st = new StringTokenizer(s);
            nums2 = new int[n2 + 1];
            for (int i = 0; i < n2; i++) {
                int num = Integer.parseInt(st.nextToken());
                nums2[n2 - i] = num;
            }
            map = new int[n1 + 1][n2 + 1];
            for (int i = 0; i < n1 + 1; i++) {
                Arrays.fill(map[i], -1);
            }
            int count = dp(n1, n2);
            out.append(String.format("Twin Towers #%d\n", caseNum));
            out.append(String.format("Number of Tiles : %d\n", count));
            out.append("\n");
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
    }
}
