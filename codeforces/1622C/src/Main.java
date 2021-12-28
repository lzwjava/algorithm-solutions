import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
//        m.test();
        m.close();
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

    int minStep;

    void dp(int n, long k, int[] a, long s, int decrease, int set) {
        if (s <= k) {
            int step = decrease + set;
            if (step < minStep) {
                minStep = step;
            }
            return;
        }
        if (n == 1) {
            dp(n, k, a, s - 1, decrease + 1, set);
        } else {
            long ns1 = s - 1 - set;
            long ns2 = s;

            if (set < n - 1) {
                int idx = n - 1 - set;
                int a0 = a[0] - decrease;
                int d = a[idx] - a0;
                ns2 = s - d;
            }

            if (ns1 <= ns2) {
                dp(n, k, a, ns1, decrease + 1, set);
            } else {
                dp(n, k, a, ns2, decrease, set + 1);
            }
        }
    }

    long cal1(int n, long k, int[] a) {
        Arrays.sort(a);
        long s = 0;
        for (int i = 0; i < n; i++) {
            s += a[i];
        }
        minStep = Integer.MAX_VALUE;
        dp(n, k, a, s, 0, 0);
        return minStep;
    }

    long cal(int n, long k, int[] a) {
        long[] sums = new long[n];
        Arrays.sort(a);
        long s = 0;
        for (int i = 0; i < n; i++) {
            s += a[i];
            sums[i] = s;
        }
        long ans;
        if (s <= k) {
            ans = 0;
        } else {
            ans = Integer.MAX_VALUE;
            for (int set = 0; set <= n - 1; set++) {
                long ns = s;
                if (set > 0) {
                    ns = s - (sum(sums, n - set, n - 1) - (long) a[0] * set);
                }
                long decrease = 0;
                if (ns > k) {
                    long d = ns - k;
                    int m = set + 1;
                    decrease = (int) Math.ceil(d * 1.0 / m);
                }
                long step = set + decrease;
                ans = Math.min(ans, step);
            }
        }
        return ans;
    }

    void test() {
        Random random = new Random();
        while (true) {
            int n = random.nextInt(1000);
            int k = random.nextInt(1000);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(1000) + 1;
            }
            long ans1 = cal1(n, k, a);
            long ans = cal(n, k, a);
            assert (ans == ans1);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            int[] a = parseArray(in.readLine());
            long ans = cal(n, k, a);
            out.append(String.format("%d\n", ans));
        }
    }

    long sum(long[] sums, int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }

}