import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    int n;
    int[] a;
    long[] sums;

    void solve() throws IOException {
        n = Integer.parseInt(in.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        sums = new long[n];
        long s = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            s += a[i];
            sums[i] = s;
        }

        long ans = 0;
        if (s % 3 == 0) {
            long part = s / 3;
            boolean[] ls = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (sum(0, i) == part) {
                    ls[i] = true;
                }
            }
            int c = 0;
            int[] rs = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                if (sum(i, n - 1) == part) {
                    c++;
                }
                rs[i] = c;
            }
            for (int i = 1; i < n - 1; i++) {
                if (ls[i]) {
                    ans += rs[i + 1];
                }
            }
        } else {
            ans = 0;
        }
        out.append(String.format("%d\n", ans));
    }

    long sum(int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}