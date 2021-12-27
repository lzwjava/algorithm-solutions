import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
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
//        m.test();
        m.close();
    }

    int[] a;
    int n;

    int[][][] map;
    int maxn = 100001;

    int toInt(boolean b) {
        return b ? 1 : 0;
    }

    boolean toBool(int b) {
        return b == 1;
    }

    int arrInt(int v) {
        if (v > 1) {
            return 2;
        } else {
            return 1;
        }
    }

    boolean dp(int i, boolean first) {
        if (i == n) {
            return !first;
        }
        int cache = map[i][arrInt(a[i])][toInt(first)];
        if (cache != -1) {
            return toBool(cache);
        }
        boolean ans;
        if (a[i] == 1) {
            ans = dp(i + 1, !first);
        } else {
            boolean result = dp(i + 1, !first);
            if (first && result) {
                ans = true;
            } else if (!first && !result) {
                ans = false;
            } else {
                int d = a[i] - 1;
                a[i] -= d;
                result = dp(i, !first);
                a[i] += d;
                if (first && !result) {
                    ans = true;
                } else if (!first && !result) {
                    ans = false;
                } else {
                    ans = !first;
                }
            }
        }
        map[i][arrInt(a[i])][toInt(first)] = toInt(ans);
        return ans;
    }

    boolean cal(int[] a, int n) {
        this.a = a;
        this.n = n;
        int m = n + 1;
        map = new int[m][3][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(map[i][j], -1);
            }
        }
        return dp(0, true);
    }

    void test() {
        Random random = new Random();
        for (int j = 0; j < 100; j++) {
            int n = random.nextInt(1000) + 1;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt((int) 1e9) + 1;
            }
            boolean ok = cal(a, n);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            boolean ok = cal(a, n);
            if (ok) {
                out.append("First\n");
            } else {
                out.append("Second\n");
            }
        }
    }

}