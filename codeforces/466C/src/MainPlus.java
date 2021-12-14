import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
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

        int ans = 0;
        if (s % 3 == 0) {
            long part = s / 3;
            for (int i = 1; i <= n - 2; i++) {
                if (sum(0, i - 1) == part) {
                    for (int j = i; j <= n - 2; j++) {
                        if (sum(i, j) == part && sum(j + 1, n - 1) == part) {
                            ans++;
                        }
                    }
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
        MainPlus m = new MainPlus();
        m.solve();
        m.close();
    }

}