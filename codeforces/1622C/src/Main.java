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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
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

    long cal(int n, long k, int[] a) {
        long[] sums;
        Arrays.sort(a);
        sums = new long[n];
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