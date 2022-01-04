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

    void permutation(int[] nums, boolean[] vis, int cur) {
        found = true;
        if (found) {
            return;
        }
        if (cur == n) {
            double s = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    s += a[j] * 1.0 / j;
                }
            }
            if (Math.abs(s - Math.round(s)) < 1e-10) {
                int v = (int) Math.round(s);
                if (v == m) {

                }
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                nums[cur] = a[i];
                permutation(nums, vis, cur + 1);
                vis[i] = false;
            }
        }
    }

    int n, m;
    int[] a;
    boolean found;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            a = parseArray(in.readLine());
            int[] nums = new int[n];
            boolean[] vis = new boolean[n];
            found = false;
            permutation(nums, vis, 0);
            if (found) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }
}