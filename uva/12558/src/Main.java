import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int a, b, k;
    int[] ks;
    double target;
    List<Integer> ans;

    void dfs(List<Integer> dms, int start, double sum) {
        if (ans != null) {
            if (dms.size() > ans.size()) {
                return;
            }
        }
        if (Math.abs(target - sum) < 1e-8) {
            if (ans == null || better(dms, ans)) {
                ans = dms;
            }
            return;
        }
        for (int i = start; i < 10000; i++) {
            if (forbid(i)) {
                continue;
            }
            dms.add(i);
            double nsum = sum + 1.0 / i;
            if (nsum < target) {
                dfs(dms, i + 1, nsum);
            }
            dms.remove(dms.size() - 1);
        }
    }

    boolean better(List<Integer> dms, List<Integer> ans) {
        if (dms.size() != ans.size()) {
            return dms.size() < ans.size();
        } else {
            int size = dms.size();
            for (int i = size - 1; i >= 0; i--) {
                int a = dms.get(i);
                int b = ans.get(i);
                if (a != b) {
                    return a < b;
                }
            }
        }
        return false;
    }

    boolean forbid(int x) {
        for (int i = 0; i < k; i++) {
            if (ks[i] == x) {
                return true;
            }
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int u = 0; u < t; u++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            ks = new int[k];
            for (int i = 0; i < k; i++) {
                ks[i] = Integer.parseInt(st.nextToken());
            }
            target = a * 1.0 / b;
            List<Integer> dms = new ArrayList<>();
            dfs(dms, 2, 0);
            out.append(String.format("Case %d: ", u + 1));
            out.append(String.format("%d/%d=", a, b));
            for (int i = 0; i < ans.size(); i++) {
                if (i != 0) {
                    out.append("+");
                }
                out.append(String.format("%d/%d", 1, ans.get(i)));
            }
        }
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
}