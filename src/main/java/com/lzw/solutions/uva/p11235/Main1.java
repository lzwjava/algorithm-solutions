package com.lzw.solutions.uva.p11235;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] nums;
    int n;

    Map<String, Integer> map;

    String getKey(int i, int j) {
        return String.format("%d,%d", i, j);
    }

    int cal(int i, int j) {
        String key = getKey(i, j);
        Integer cc = map.get(key);
        if (cc != null) {
            return cc;
        }
        if (i == j) {
            return 1;
        }
        int len = j - i + 1;
        int mid = i + len / 2;
        int r1 = cal(i, mid - 1);
        int r2 = cal(mid, j);
        int p = mid;
        while (p - 1 >= i && nums[p - 1] == nums[p]) {
            p--;
        }
        int q = mid;
        while (q + 1 <= j && nums[q + 1] == nums[q]) {
            q++;
        }
        int cnt = q - p + 1;

        int[] counts = new int[] {r1, r2, cnt};
        Arrays.sort(counts);
        cc = counts[2];
        map.put(key, cc);
        return cc;
    }

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            int q = Integer.parseInt(st.nextToken());
            nums = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            map = new HashMap<>();
            while (q > 0) {
                st = new StringTokenizer(in.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                i--;
                j--;
                int ans = cal(i, j);
                out.append(String.format("%d\n", ans));
                q--;
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

        Main1 main = new Main1();
        main.solve();
        main.close();
    }
}
