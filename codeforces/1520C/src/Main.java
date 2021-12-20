import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    boolean adjacent(int a, int b) {
        return Math.abs(a - b) == 1;
    }

    boolean check(int[] nums, int p) {
        if (p == 0) {
            return true;
        }
        int i = p - 1;
        int x = i / n;
        int y = i % n;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                int ni = nx * n + ny;
                if (ni < p) {
                    if (adjacent(nums[i], nums[ni])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    boolean found;

    void print(int[] nums) {
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (v != 0) {
                    out.append(' ');
                }
                int x = u * n + v;
                out.append(String.format("%d", nums[x] + 1));
            }
            out.append('\n');
        }
    }

    void permutation(int[] nums, boolean[] vis, int i, int m) {
        if (found) {
            return;
        }
        if (!check(nums, i)) {
            return;
        }
        if (i == m) {
            print(nums);
            found = true;
            return;
        }
        for (int j = 0; j < m; j++) {
            if (!vis[j]) {
                vis[j] = true;
                nums[i] = j;
                permutation(nums, vis, i + 1, m);
                vis[j] = false;
            }
        }
    }

    int n;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            int m = n * n;
            int[] nums = new int[m];
            boolean[] vis = new boolean[m];
            found = false;
            permutation(nums, vis, 0, m);
            if (!found) {
                out.append("-1\n");
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}