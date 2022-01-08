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

    int cal1(int s, int n, int[] x) {
        int m = Integer.min(s, n);
        int total = 0;
        for (int i = 1; i <= m; i++) {
            int group = (n - i + s - 1) / s + 1;
            int[] groupSize = new int[group];
            groupSize[0] = i;
            int tn = n - i;
            for (int j = 1; j < group; j++) {
                if (tn >= s) {
                    groupSize[j] = s;
                    tn -= s;
                } else {
                    groupSize[j] = tn;
                }
            }
            int p = 0;
            boolean ok = true;
            for (int j = 0; j < group; j++) {
                Set<Integer> set = new HashSet<>();
                for (int k = 0; k < groupSize[j]; k++) {
                    if (set.contains(x[p])) {
                        ok = false;
                        break;
                    } else {
                        set.add(x[p++]);
                    }
                }
                if (!ok) {
                    break;
                }
            }
            if (ok) {
                total++;
            }
        }
        if (n <= s && total == n) {
            total = s;
        }
        return total;
    }

    int cal2(int s, int n, int[] x) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] leftPos = new int[n];
        for (int i = 0; i < n; i++) {
            Integer p = map.get(x[i]);
            if (p == null) {
                leftPos[i] = -1;
            } else {
                leftPos[i] = p;
            }
            map.put(x[i], i);
        }
        int min = n;
        int mini = -1;
        for (int i = 0; i < n; i++) {
            if (leftPos[i] != -1) {
                int dist = i - leftPos[i];
                if (dist < min) {
                    min = dist;
                    mini = i;
                }
            }
        }
        if (min == n) {
            return s;
        }
        int j = leftPos[mini];
        boolean ok = true;
        while (j >= 0) {
            int md = Integer.min(j, s);
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < md; i++) {
                if (set.contains(x[j])) {
                    ok = false;
                    break;
                }
                set.add(x[j]);
                j--;
            }
            if (!ok) {
                break;
            }
        }
        j = leftPos[mini] + 1;
        while (j < n) {
            int md = Integer.min(n - j, s);
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < md; i++) {
                if (set.contains(x[j])) {
                    ok = false;
                    break;
                }
                set.add(x[j]);
                j++;
            }
            if (!ok) {
                break;
            }
        }
        if (!ok) {
            return 0;
        }
        return min;
    }

    void test() {
        Random random = new Random();
        while (true) {
            int s = random.nextInt(10);
            int n = random.nextInt(100);
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = random.nextInt(s) + 1;
            }
            int ans1 = cal1(s, n, x);
            int ans2 = cal2(s, n, x);
            assert ans1 == ans2;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[] x = parseArray(in.readLine());
            int total = cal1(s, n, x);
            out.append(String.format("%d\n", total));
        }
    }
}