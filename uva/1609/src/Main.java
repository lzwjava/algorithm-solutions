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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int n;
    String[] strs;
    String ans;

    boolean check(int[] nums, int cur, StringBuilder sb) {
        while (cur != 1) {
            int nc = cur / 2;
            int[] as = new int[nc];
            for (int i = 0; i < nc; i++) {
                int j = 2 * i;
                int k = 2 * i + 1;
                as[i] = winner(nums[j], nums[k]);
                sb.append(String.format("%d %d\n", nums[j] + 1, nums[k] + 1));
            }
            nums = as;
            cur /= 2;
        }
        return nums[0] == 0;
    }

    int winner(int a, int b) {
        if (strs[a].charAt(b) == '1') {
            return a;
        } else {
            return b;
        }
    }

    boolean found;

    void permutation(int[] nums, boolean[] vis, int cur) {
        if (found) {
            return;
        }
        if (cur == n) {
            StringBuilder sb = new StringBuilder();
            if (check(nums, n, sb)) {
                found = true;
                ans = sb.toString();
            }
            return;
        }
        double rd = Math.log(cur) / Math.log(2);
        if (Math.abs(rd - (int) rd) < 1e-10) {
            if (!check(nums, cur, new StringBuilder())) {
                return;
            }
        }
        int st;
        if (cur % 2 == 0) {
            st = nums[cur - 2] + 1;
        } else {
            st = 0;
        }
        for (int i = st; i < n; i++) {
            if (!vis[i]) {
                nums[cur] = i;
                vis[i] = true;
                permutation(nums, vis, cur + 1);
                vis[i] = false;
            }
        }
    }

    String cal(int n, String[] strs) {
        this.n = n;
        this.strs = strs;
        int[] nums = new int[n];
        boolean[] vis = new boolean[n];
        nums[0] = 0;
        vis[0] = true;
        found = false;
        permutation(nums, vis, 1);
        return ans;
    }

    String cal1(int n, String[] strs) {
        return "";
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            n = Integer.parseInt(line);
            strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = in.readLine();
            }
            String ans = cal1(n, strs);
            out.append(String.format("%s", ans));
        }
    }
}
