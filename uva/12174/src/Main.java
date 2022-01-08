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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[] x = parseArray(in.readLine());
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
                List<Integer>[] groups = new ArrayList[group];
                int p = 0;
                for (int j = 0; j < group; j++) {
                    groups[j] = new ArrayList<>();
                    for (int k = 0; k < groupSize[j]; k++) {
                        groups[j].add(x[p++]);
                    }
                }
                boolean ok = true;
                for (List<Integer> groupItem : groups) {
                    if (!checkGroup(groupItem)) {
                        ok = false;
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
            out.append(String.format("%d\n", total));
        }
    }
}