package p1;

import java.io.*;
import java.util.*;

public class Main {
    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine().trim());
            long[] a = new long[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                if (!st.hasMoreTokens()) {
                    throw new IOException("Insufficient input tokens");
                }
                a[i] = Long.parseLong(st.nextToken());
                if (a[i] > n) a[i] = n; // Cap a[i] at n
            }

            // Handle n = 1 separately
            if (n == 1) {
                out.println(1); // Final array is [0], MEX = 1
                continue;
            }

            // Difference array for range updates
            long[] diff = new long[n + 1];
            for (int i = n - 1; i >= 0; i--) {
                if (a[i] == 0) continue; // No increments if a[i] = 0
                int start = i + 1;
                int end = (int) Math.min((long) i + a[i], n - 1);
                if (start <= end) {
                    diff[start]++;
                    if (end + 1 <= n) diff[end + 1]--;
                }
            }

            // Compute final array b
            long[] b = new long[n];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += diff[i];
                b[i] = sum;
            }

            // Compute MEX efficiently
            long mex = 0;
            for (int i = 0; i < n; i++) {
                if (b[i] > mex) break; // Gap found
                if (b[i] == mex) mex++; // All values up to mex-1 exist
            }
            out.println(mex);
        }
    }

    void close() throws IOException {
        if (in != null) in.close();
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.solve();
        main.close();
    }
}