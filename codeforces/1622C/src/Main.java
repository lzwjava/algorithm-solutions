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
    
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            st = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            long s = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                s += a[i];
            }
            Arrays.sort(a);
            long ans;
            if (s <= k) {
                ans = 0;
            } else {
                ans = Integer.MAX_VALUE;
                for (int set = 0; set <= n - 1; set++) {
                    if (set >= ans) {
                        break;
                    }
                    long ns = s + (long) a[0] * set;
                    long decrease = 0;
                    if (ns > k) {
                        long d = ns - k;
                        decrease = (d + set) / (set + 1);
                    }
                    long step = set + decrease;
                    ans = Math.min(ans, step);
                    s -= a[n - 1 - set];
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }

}