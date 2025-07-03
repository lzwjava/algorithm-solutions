package com.algorithm.solutions.uva.p11085;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    boolean check(int[] rows, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i + rows[i] == j + rows[j]) {
                    return false;
                }
                if (i - rows[i] == j - rows[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    int total;
    int[][] list;

    void permutation(int[] rows, boolean[] vis, int cur) {
        if (cur == 8) {
            list[total++] = rows.clone();
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (!vis[i]) {
                rows[cur] = i;
                if (check(rows, cur + 1)) {
                    vis[i] = true;
                    permutation(rows, vis, cur + 1);
                    vis[i] = false;
                }
            }
        }
    }

    void solve() throws IOException {
        total = 0;
        list = new int[92][8];
        int[] rows = new int[8];
        boolean[] vis = new boolean[8];
        permutation(rows, vis, 0);
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int[] nums = new int[8];
            for (int i = 0; i < 8; i++) {
                nums[i] = Integer.parseInt(st.nextToken()) - 1;
            }
            int min = 8;
            for (int i = 0; i < total; i++) {
                int cnt = 0;
                for (int j = 0; j < 8; j++) {
                    if (nums[j] != list[i][j]) {
                        cnt++;
                    }
                }
                if (cnt < min) {
                    min = cnt;
                }
            }
            out.append(String.format("Case %d: %d\n", caseNum, min));
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
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
