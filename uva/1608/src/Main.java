import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
//        m.solve();
        m.test();
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

    boolean cal(int[] a) {
        int n = a.length;
        boolean boring = false;
        for (int i = 0; i < n && !boring; i++) {
            for (int j = i; j < n; j++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int k = i; k <= j; k++) {
                    Integer cnt = map.get(a[k]);
                    if (cnt == null) {
                        cnt = 0;
                    }
                    cnt++;
                    map.put(a[k], cnt);
                }
                boolean ok = false;
                for (int key : map.keySet()) {
                    int cnt = map.get(key);
                    if (cnt == 1) {
                        ok = true;
                        break;
                    }
                }
                if (!ok) {
                    boring = true;
                    break;
                }
            }
        }
        return boring;
    }

    boolean cal1(int[] a) {
        int n = a.length;
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // consecutive same
                if (j + len >= n) {
                    continue;
                }
                boolean ok = true;
                for (int k = i; k <= j; k++) {
                    int nk = k + len;
                    if (a[k] != a[nk]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    return true;
                }
            }
        }
        return false;
    }

    void test() {
        // boring: two consecutive same,
        // 4 1 5 1 5
        // 4 5 5 3 2
        while (true) {
            int n = 4;
            int[] a = new int[n];
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(5) + 1;
            }
            boolean boring = cal(a);
            boolean boring1 = cal1(a);
            assert boring == boring1;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            boolean boring = cal1(a);
            if (boring) {
                out.append("boring\n");
            } else {
                out.append("non-boring\n");
            }
        }
    }
}