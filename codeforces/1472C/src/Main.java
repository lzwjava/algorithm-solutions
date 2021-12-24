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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            long sum = 0;
            long[] sums = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                sum += a[i];
                sums[i] = sum;
            }
            int ms = 0;
            for (int i = 0; i < n; i++) {
                int j = i;
                int s = 0;
                while (j < n) {
                    if (s + sum(sums, j, n - 1) < ms) {
                        break;
                    }
                    s += a[j];
                    j += a[j];
                }
                if (s > ms) {
                    ms = s;
                }
            }
            out.append(String.format("%d\n", ms));
        }
    }

    long sum(long[] sums, int i, int j) {
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