package com.lzw.solutions.uva.p200;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Main2 {

    BufferedReader in;
    PrintWriter out;

    Main2() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean permutation(int[] nums, boolean vis[], int i, int n) {
        if (i == n) {
            ArrayList<String> sortStrs = new ArrayList<>();
            sortStrs.addAll(strs);
            Collections.sort(sortStrs, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int len1 = o1.length();
                    int len2 = o2.length();

                    int minLen = len1 < len2 ? len1 : len2;
                    for (int i = 0; i < minLen; i++) {
                        Character ch1 = o1.charAt(i);
                        Character ch2 = o2.charAt(i);

                        if (ch1 != ch2) {
                            int index1 = chList.indexOf(ch1);
                            int index2 = chList.indexOf(ch2);
                            return Integer.compare(nums[index1], nums[index2]);
                        }
                    }
                    if (len1 < len2) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
            boolean equal = true;
            for (int j = 0; j < n; j++) {
                if (!sortStrs.get(j).equals(strs.get(j))) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                int rnums[] = new int[n];
                for (int j = 0; j < n; j++) {
                    rnums[nums[j]] = j;
                }
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    Character ch = chList.get(rnums[j]);
                    sb.append(ch);
                }
                String ans = sb.toString();
                out.append(ans).append('\n');
                return true;
            }
            return false;
        }
        for (int j = 0; j < n; j++) {
            if (!vis[j]) {
                vis[j] = true;
                nums[i] = j;
                boolean res = permutation(nums, vis, i + 1, n);
                if (res) {
                    return true;
                }
                vis[j] = false;
            }
        }
        return false;
    }

    ArrayList<String> strs;
    ArrayList<Character> chList;

    void solve() throws IOException {
        strs = new ArrayList<>();
        while (true) {
            String s = in.readLine();
            if (s.equals("#")) {
                break;
            }
            strs.add(s);
        }
        HashSet<Character> chs = new HashSet<>();
        for (String s : strs) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                chs.add(ch);
            }
        }
        chList = new ArrayList<>();
        chList.addAll(chs);
        Collections.sort(chList);
        int n = chList.size();
        boolean[][] graph = new boolean[n][n];
        for (String str : strs) {
            for (int i = 0; i < str.length() - 1; i++) {
                Character ch1 = str.charAt(i);
                Character ch2 = str.charAt(i + 1);
                if (ch1 != ch2) {
                    int index1 = chList.indexOf(ch1);
                    int index2 = chList.indexOf(ch2);
                    graph[index1][index2] = true;
                }
            }
        }
        // int[] nums = new int[n];
        // boolean[] vis = new boolean[n];
        // permutation(nums,vis, 0, n);
        ArrayList<Integer> ans = new ArrayList<>();
        while (true) {
            int minCount = Integer.MAX_VALUE;
            int minI = 0;
            for (int i = 0; i < n; i++) {
                if (ans.contains(i)) {
                    continue;
                }
                int count = 0;
                for (int j = 0; j < n; j++) {
                    // i behinds j
                    if (graph[j][i]) {
                        count++;
                    }
                }
                if (count < minCount) {
                    minCount = count;
                    minI = i;
                }
            }
            ans.add(minI);
            if (ans.size() == n) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : ans) {
            sb.append(chList.get(i));
        }
        out.append(sb.toString()).append('\n');
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

    public static void Main2(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main2 Main2 = new Main2();
        Main2.solve();
        Main2.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
