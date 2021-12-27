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

    long k;
    int[] a;
    int n;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Long.parseLong(st.nextToken());
            a = parseArray(in.readLine());
            Arrays.sort(a);
            long s = 0;
            for (int i = 0; i < n; i++) {
                s += a[i];
            }
            int ans;
            if (s <= k) {
                ans = 0;
            } else {
                int set = 0;
                int decrease = 0;
                while (s > k) {
                    long ns1 = s - 1 - set;
                    long ns2 = s;

                    if (set < n - 1) {
                        int idx = n - 1 - set;
                        int a0 = a[0] - decrease;
                        int d = a[idx] - a0;
                        ns2 = s - d;
                    }

                    if (ns1 <= ns2) {
                        int d = 1 + set;
                        long times = (s - k) / d;
                        if (times > 0) {
                            s -= times * d;
                            decrease += times;
                        } else {
                            s = ns1;
                            decrease++;
                        }
                    } else {
                        s = ns2;
                        set++;
                    }
                }
                ans = set + decrease;
            }
            out.append(String.format("%d\n", ans));
        }
    }

}