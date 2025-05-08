package p1;

import java.io.*;
import java.util.*;

public class Main {
    private BufferedReader in;
    private PrintWriter out;
    private int[] a;
    private int n;

    public Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public boolean check(int x) {
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            int need = Math.max(0, x - n + i);
            if (i > 0) {
                d[i] += d[i - 1];
            }
            if (d[i] < need) {
                return false;
            }
            int len = d[i] - need + a[i];
            if (i + 1 < n) {
                d[i + 1]++;
            }
            if (i + len + 1 < n) {
                d[i + len + 1]--;
            }
        }
        return true;
    }

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());

        a = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int lo = 0, hi = n;
        while (lo < hi) {
            int x = (lo + hi + 1) / 2;
            if (check(x)) {
                lo = x;
            } else {
                hi = x - 1;
            }
        }
        out.println(lo);
    }

    public void run() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            solve();
        }
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}