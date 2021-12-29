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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
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

    boolean good(int[] a) {
        int n = a.length;
        for (int l = 0; l <= n; l++) {
            for (int r = l; r <= n; r++) {
                int sum = sum(l, r);
                int right = (a[l] + a[r]) * (r - l + 1);
                if (sum * 2 != right) {
                    return false;
                }
            }
        }
        return true;
    }

    int[] sums;

    int sum(int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            int s = 0;
            sums = new int[n];
            for (int i = 0; i < n; i++) {
                s += a[i];
                sums[i] = s;
            }

        }
    }
}