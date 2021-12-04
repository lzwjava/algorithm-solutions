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

    boolean equal(double a, double b) {
        return Math.abs(a - b) < 1e-15;
    }

    boolean isInt(double a) {
        return Math.abs(Math.round(a) - a) < 1e-8;
    }

    void dfs(List<Integer> dms, int start, int cur, int len, double sum) {
//        if (b == 233 && len == 3 && cur == 2 && dms.get(0) == 7 && dms.get(1) == 14) {
//            System.out.println();
//        }
        if (cur == len - 1) {
            double fn = 1.0 / (target - sum);
            if (isInt(fn)) {
                int ifn = (int) Math.round(fn);
                if (ifn > dms.get(dms.size() - 1)) {
                    dms.add(ifn);
                    if (ans == null || better(dms, ans)) {
                        ans = new ArrayList<>(dms);
                    }
                    dms.remove(dms.size() - 1);
                }
            }
            return;
        }
        for (int i = start; i < 12000; i++) {
            if (forbid(i)) {
                continue;
            }
            double nsum = sum + 1.0 / i;
            if (nsum < target || equal(nsum, target)) {
                int rest = len - (cur + 1);
                double max = nsum + rest * 1.0 / i;
                if (max > target || equal(max, target)) {
                    dms.add(i);
                    dfs(dms, i + 1, cur + 1, len, nsum);
                    dms.remove(dms.size() - 1);
                } else {
                    break;
                }
            }
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
            ans = null;
            for (int len = 2; ; len++) {
                List<Integer> dms = new ArrayList<>();
                dfs(dms, 2, 0, len, 0);
                if (ans != null) {
                    break;
                }
            }
            out.append(String.format("Case %d: ", u + 1));
            out.append(String.format("%d/%d=", a, b));
            for (int i = 0; i < ans.size(); i++) {
                if (i != 0) {
                    out.append("+");
                }
                out.append(String.format("%d/%d", 1, ans.get(i)));
            }
            out.append('\n');
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