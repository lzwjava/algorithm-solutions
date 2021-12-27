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


    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            int[] a = parseArray(in.readLine());
            Arrays.sort(a);
            sums = new long[n];
            long s = 0;
            for (int i = 0; i < n; i++) {
                s += a[i];
                sums[i] = s;
            }
            int ans;
            if (s <= k) {
                ans = 0;
            } else {
                ans = Integer.MAX_VALUE;
//                long kn = k / n;
                for (int i = 0; ; i++) {
                    long v = a[0] - (i + 1);
                    // a[0]+a[0]*x <=k
                    if (v * n <= k) {
                        int x;
                        for (x = 0; x <= n - 1; x++) {
                            if (v + v * x + sum(1, (n - 1) - x) <= k) {
                                break;
                            }
                        }
                        int step = (i + 1) + x;
                        if (step < ans) {
                            ans = step;
                        } else {
                            break;
                        }
                    }
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }

    long[] sums;

    long sum(int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }

}