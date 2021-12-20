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

    int mergeCount(int[] a, int l, int r) {
        if (l == r) {
            return 0;
        }
        if (l + 1 == r) {
            if (a[r] - a[l] == r - l) {
                return 1;
            } else {
                return 0;
            }
        }
        int mid = (l + r) / 2;
        int c1 = mergeCount(a, l, mid);
        int c2 = mergeCount(a, mid + 1, r);
        int mc = 0;
        for (int i = l; i <= mid; i++) {
            for (int j = mid + 1; j <= r; j++) {
                if (a[j] - a[i] == j - i) {
                    mc++;
                }
            }
        }
        return c1 + c2 + mc;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            int ans = mergeCount(a, 0, n - 1);
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}