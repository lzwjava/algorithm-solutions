import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    boolean checkGroup(List<Integer> items) {
        int size = items.size();
        Set<Integer> set = new HashSet<>(items);
        return set.size() == size;
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