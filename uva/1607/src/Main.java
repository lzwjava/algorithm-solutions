import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
        m.solve();
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

    int valueOf(Map<Integer, Integer> map, int[] v, int ai) {
        if (ai < 0) {
            return map.get(ai);
        } else {
            return v[ai - 1];
        }
    }

    int nand(int a, int b) {
        if (a == 1 && b == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    void permutation(int[] inputs, int x, int cur, int n) {
        if (cur == n) {
            if (x == 0 || x == n) {
                return;
            }
            boolean ok = true;
            for (int y = 0; y <= 1; y++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int i = 0; i < n; i++) {
                    int rv;
                    if (inputs[i] == -1) {
                        rv = y;
                    } else {
                        rv = inputs[i];
                    }
                    map.put(-(i + 1), rv);
                }
                int fv = cal(map);
                if (fv != y) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                if (x < minx) {
                    minx = x;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < n; i++) {
                        if (inputs[i] == -1) {
                            sb.append('x');
                        } else {
                            sb.append((char) (inputs[i] + '0'));
                        }
                    }
                    mins = sb.toString();
                }
            }
            return;
        }
        for (int i = -1; i <= 1; i++) {
            inputs[cur] = i;
            int nx = x;
            if (i == -1) {
                nx++;
            }
            permutation(inputs, nx, cur + 1, n);
        }
    }

    int n, m;
    int[] a;
    boolean found;
    int minx;
    String mins;

    int cal(Map<Integer, Integer> map) {
        int[] v = new int[m];
        for (int i = 0; i < m; i++) {
            int i1 = i * 2;
            int i2 = i * 2 + 1;
            int v1 = valueOf(map, v, a[i1]);
            int v2 = valueOf(map, v, a[i2]);
            int fv = nand(v1, v2);
            v[i] = fv;
        }
        return v[m - 1];
    }

    void solve() throws IOException {
        int d = Integer.parseInt(in.readLine());
        while (d > 0) {
            d--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            a = parseArray(in.readLine());
            int[] inputs = new int[n];
            minx = Integer.MAX_VALUE;
            permutation(inputs, 0, 0, n);
            out.append(String.format("%s\n", mins));
        }
    }
}