import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int dp(int[] a, int i, int start) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (a[i] > start) {
            int len1 = dp(a, i + 1, a[i]) + 1;
            int len2 = dp(a, i + 1, start);
            return Integer.max(len1, len2);
        } else {
            return dp(a, i + 1, start);
        }
    }

    class Item implements Comparable<Item> {
        int v, d;

        Item(int v, int d) {
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(Item o) {
            if (d != o.d) {
                return Integer.compare(o.d, d);
            } else {
                return Integer.compare(v, o.v);
            }
        }
    }

    int cal(int[] a) {
        int n = a.length;
        int[] d = new int[n];
        int[] v = new int[n + 1];
        Arrays.fill(v, -1);
        d[0] = 1;
        // d[i]: length ending at i
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1]) {
                d[i] = d[i - 1] + 1;
            } else {
                d[i] = 1;
            }
        }
        // length starting at i
        int[] f = new int[n];
        f[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                f[i] = f[i + 1] + 1;
            } else {
                f[i] = 1;
            }
        }

        int ans = 0;

        int vn = 1;
        v[0] = 0;
        for (int i = 0; i < n; i++) {
            int idx = Arrays.binarySearch(v, 0, vn, a[i]);
            int leftLen;
            if (idx >= 0) {
                while (v[idx] == a[i]) {
                    idx--;
                }
                leftLen = idx;
            } else {
                idx = -(idx + 1);
                leftLen = idx - 1;
            }
            int totalLen = leftLen + f[i];
            if (totalLen > ans) {
                ans = totalLen;
            }
            int k = d[i];
            if (v[k] == -1 || v[k] > a[i]) {
                v[k] = a[i];
            }
            if (k >= vn) {
                vn = k + 1;
            }
        }
        return ans;
    }

    void solve() throws IOException {
        int z = Integer.parseInt(in.readLine());
        while (z > 0) {
            z--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            int ans = cal(a);
            out.append(String.format("%d\n", ans));
        }
    }
}