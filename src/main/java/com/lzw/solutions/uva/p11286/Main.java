package com.lzw.solutions.uva.p11286;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String getKey(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                sb.append(',');
            }
            sb.append(String.valueOf(arr[i]));
        }
        return sb.toString();
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int[][] nums = new int[n][5];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 5; j++) {
                    nums[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < n; i++) {
                Arrays.sort(nums[i]);
            }
            int max = 0;
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String key = getKey(nums[i]);
                Integer count = map.get(key);
                if (count == null) {
                    count = 0;
                }
                count++;
                map.put(key, count);
                if (count > max) {
                    max = count;
                }
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                String key = getKey(nums[i]);
                Integer count = map.get(key);
                if (count == max) {
                    ans++;
                }
            }
            out.append(String.format("%d\n", ans));
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
