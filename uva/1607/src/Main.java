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

    int calAll(int y) {
        int[] inputs = new int[n];
        for (int i = 0; i < n; i++) {
            inputs[i] = y;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(-(i + 1), inputs[i]);
        }
        return cal(map);
    }

    int n, m;
    int[] a;
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
            int all0 = calAll(0);
            int all1 = calAll(1);
            if (all0 == all1) {
                
            }
            out.append(String.format("%s\n", mins));
        }
    }
}