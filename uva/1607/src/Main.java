import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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
//        m.test();
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
        return calInputs(inputs);
    }

    int calInputs(int[] inputs) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(-(i + 1), inputs[i]);
        }
        return cal(map);
    }

    int n, m;
    int[] a;

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

    String build(int[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (inputs[i] == -1) {
                sb.append('x');
            } else {
                sb.append((char) (inputs[i] + '0'));
            }
        }
        return sb.toString();
    }

    boolean check(int[] inputs) {
        boolean ok = true;
        int all0 = calAll(0);
        int all1 = calAll(0);
        if (all0 == all1) {
            for (int i = 0; i < n; i++) {
                if (inputs[i] == -1) {
                    return false;
                }
            }
        } else {
            for (int y = 0; y <= 1; y++) {
                int[] is = inputs.clone();
                for (int i = 0; i < n; i++) {
                    if (is[i] == -1) {
                        is[i] = y;
                    }
                }
                int fv = calInputs(is);
                if (fv != y) {
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }

    int[] solve(int n, int m, int[] a) {
        this.n = n;
        this.m = m;
        this.a = a;
        int all0 = calAll(0);
        int all1 = calAll(1);
        if (all0 == all1) {
            int[] inputs = new int[n];
            Arrays.fill(inputs, 0);
//            inputs[0] = -1;
            return inputs;
        } else {
            int left = 0, right = n;
            int[] inputs = new int[n];
            while (left < right) {
                int mid = (left + right) / 2;
                Arrays.fill(inputs, 0);
                for (int i = 0; i < mid; i++) {
                    inputs[i] = 1;
                }
                int v = calInputs(inputs);
                if (v == all0) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            Arrays.fill(inputs, 0);
            if (left > 0) {
                for (int i = 0; i < left; i++) {
                    if (i != left - 1) {
                        inputs[i] = 1;
                    } else {
                        inputs[i] = -1;
                    }
                }
            } else {
                inputs[0] = -1;
            }
//            assert check(inputs);
            return inputs;
        }
    }

    void test() {
        Random random = new Random();
        while (true) {
            int n = random.nextInt(3) + 1;
            int m = 2 * n;
            int k = 3 * m;
            int[] a = new int[k];
            for (int i = 0; i < k; i++) {
                int id = i / 2;
                int type = random.nextInt(2);
                if (type == 0 || id == 0) {
                    int v1 = -(random.nextInt(n) + 1);
                    a[i] = v1;
                } else {
                    do {
                        int v2 = random.nextInt(id) + 1;
                        a[i] = v2;
                        if (v2 != m) {
                            break;
                        }
                    } while (true);
                }
            }
            Set<Integer> input = new HashSet<>();
            Set<Integer> output = new HashSet<>();
            boolean connectSelf = false;
            for (int i = 0; i < k; i++) {
                if (a[i] < 0) {
                    input.add(a[i]);
                } else {
                    output.add(a[i]);
                }
                int id = i / 2;
                if (a[i] > 0 && a[i] == id + 1) {
                    connectSelf = true;
                    break;
                }
            }
            if (connectSelf || input.size() != n || output.size() != m - 1) {
                continue;
            }
            int[] inputs = solve(n, m, a);
//            String ans = build(inputs);
            assert check(inputs);
        }
    }

    void solve() throws IOException {
        int d = Integer.parseInt(in.readLine());
        while (d > 0) {
            d--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            a = parseArray(in.readLine());
            int[] is = solve(n, m, a);
            String ans = build(is);
            out.append(String.format("%s\n", ans));
        }
    }
}