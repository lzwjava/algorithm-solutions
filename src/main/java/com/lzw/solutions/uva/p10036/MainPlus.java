package com.lzw.solutions.uva.p10036;

import java.io.*;
import java.util.StringTokenizer;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n, k;
    int[] nums;
    boolean divisible;

    void permutation(int[] vs, int cur, int s) {
        if (divisible) {
            return;
        }
        if (cur == n) {
            if (s % k == 0) {
                divisible = true;
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            vs[cur] = i;
            int ns = s;
            if (i == 0) {
                ns += nums[cur];
            } else {
                ns -= nums[cur];
            }
            permutation(vs, cur + 1, ns);
        }
    }

    void solve() throws IOException {
        int m = Integer.parseInt(in.readLine());
        while (m > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            nums = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            divisible = false;
            int[] vs = new int[n];
            permutation(vs, 0, 0);
            if (divisible) {
                out.append("Divisible\n");
            } else {
                out.append("Not divisible\n");
            }
            m--;
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
