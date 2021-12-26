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

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int[] a;
    int n;

    boolean dp(int i, boolean first) {
        if (i == n) {
            return !first;
        }
        if (a[i] == 1) {
            return dp(i + 1, !first);
        } else {
            boolean result = dp(i + 1, !first);
            if (first && result) {
                return true;
            }
            if (!first && !result) {
                return false;
            }

            int d = a[i] - 1;
            a[i] -= d;
            result = dp(i, !first);
            a[i] += d;
            if (first && !result) {
                return true;
            }

            if (!first && !result) {
                return false;
            }
            return !first;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            n = Integer.parseInt(in.readLine());
            a = parseArray(in.readLine());
            boolean ok = dp(0, true);
            if (ok) {
                out.append("First\n");
            } else {
                out.append("Second\n");
            }
        }
    }

}