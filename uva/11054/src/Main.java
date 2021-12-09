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

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            long ans = 0;
            for (int i = 1; i < n; i++) {
                if (a[i] == 0) {
                    continue;
                }
                for (int j = 0; j < i; j++) {
                    if (a[i] * a[j] < 0) {
                        int min = Math.min(Math.abs(a[i]), Math.abs(a[j]));
                        if (a[i] > 0) {
                            a[i] -= min;
                            a[j] += min;
                        } else {
                            a[i] += min;
                            a[j] -= min;
                        }
                        ans += (long) (i - j) * min;
                    }
                }
            }
            out.append(String.format("%d\n", ans));
        }
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
}