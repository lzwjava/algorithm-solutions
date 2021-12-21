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

    boolean found;

    void permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (found) {
            return;
        }
        if (cur == n) {
            boolean ok = true;
            for (int i = 0; i < n && ok; i++) {
                for (int j = i; j < n; j++) {
                    int v = -1;
                    for (int k = i; k <= j; k++) {
                        if (v == -1) {
                            v = nums[k] + 1;
                        } else {
                            v = v | (nums[k] + 1);
                        }
                    }
                    if (v < j - i + 1) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                found = true;
                for (int i = 0; i < n; i++) {
                    if (i != 0) {
                        out.append(' ');
                    }
                    out.append(String.format("%d", nums[i] + 1));
                }
                out.append('\n');
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                nums[cur] = i;
                permutation(nums, vis, cur + 1, n);
                vis[i] = false;
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] nums = new int[n];
            boolean[] vis = new boolean[n];
            found = false;
            permutation(nums, vis, 0, n);
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}