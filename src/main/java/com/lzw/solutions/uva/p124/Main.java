package com.lzw.solutions.uva.p124;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean check(int[] nums, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                char ci = chs[nums[i]];
                char cj = chs[nums[j]];
                if (!checkCons(ci, cj)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean checkCons(char a, char b) {
        int pair = cons.length / 2;
        for (int i = 0; i < pair; i++) {
            char pi = cons[i * 2];
            char pj = cons[i * 2 + 1];
            if (pi == b && pj == a) {
                return false;
            }
        }
        return true;
    }

    ArrayList<String> ans;

    void permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (!check(nums, cur)) {
            return;
        }
        if (cur == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(String.format("%c", chs[nums[i]]));
            }
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                nums[cur] = i;
                vis[i] = true;
                permutation(nums, vis, cur + 1, n);
                vis[i] = false;
            }
        }
    }

    char[] chs;
    int n;
    char[] cons;

    void solve() throws IOException {
        boolean first = true;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            if (first) {
                first = false;
            } else {
                out.append('\n');
            }
            StringTokenizer st = new StringTokenizer(line);
            n = st.countTokens();
            chs = new char[n];
            for (int i = 0; i < n; i++) {
                chs[i] = st.nextToken().charAt(0);
            }
            st = new StringTokenizer(in.readLine());
            int m = st.countTokens();
            cons = new char[m];
            for (int i = 0; i < m; i++) {
                cons[i] = st.nextToken().charAt(0);
            }
            int[] nums = new int[n];
            boolean[] vis = new boolean[n];
            ans = new ArrayList<String>();
            permutation(nums, vis, 0, n);
            Collections.sort(ans);
            for (String s : ans) {
                out.append(String.format("%s\n", s));
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
