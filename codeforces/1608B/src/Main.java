import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
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

    boolean found;

    class Result {
        int localMax;
        int localMin;

        Result(int localMax, int localMin) {
            this.localMax = localMax;
            this.localMin = localMin;
        }
    }

    Result calLocal(int[] nums, int n) {
        int localMax = 0, localMin = 0;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                localMax++;
            }
            if (nums[i] < nums[i - 1] && nums[i] < nums[i + 1]) {
                localMin++;
            }
        }
        return new Result(localMax, localMin);
    }

    void permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (found) {
            return;
        }
        Result tr = calLocal(nums, cur);
        int rest = n - cur;
        int d = (int) Math.ceil(rest * 1.0 / 2);
        if (tr.localMax + d < a || tr.localMin + d < b) {
            return;
        }
        if (cur == n) {
            Result r = calLocal(nums, n);
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", nums[i] + 1));
            }
            out.append('\n');

//            if (a == r.localMax && b == r.localMin) {
//                for (int i = 0; i < n; i++) {
//                    if (i != 0) {
//                        out.append(' ');
//                    }
//                    out.append(String.format("%d", nums[i] + 1));
//                }
//                out.append('\n');
//                found = true;
//            }
            out.append(String.format("%d %d\n", r.localMax, r.localMin));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                nums[cur] = i;
                vis[i] = true;
                permutation(nums, vis, cur + 1, n);
                vis[i] = false;
            }
        }
    }

    int a, b;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        Random random = new Random();
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            int sum = n - 2;
            int d;
            if (n % 2 == 1) {
                d = sum / 2 + 1;
            } else {
                d = sum / 2;
            }
            if (a + b <= sum && a <= d && b <= d) {
                int[] nums = new int[n];
                for (int i = 0; i < n; i++) {
                    nums[i] = i;
                }
                int min = Math.min(a, b);
                int mind = min + 1;
                int[] as = new int[mind];
                int[] bs = new int[mind];
                for (int i = 0; i < mind; i++) {
                    as[i] = i;
                }
                for (int i = 0; i < mind; i++) {
                    bs[i] = mind + i;
                }
                int p = 0;
                int q = 0;
                while (p < 2 * mind) {
                    nums[p++] = as[q];
                    nums[p++] = bs[q];
                    q++;
                }
                if (a > min) {
                    int u = a - min;
                    int v = 2 * mind;
                    for (int i = 0; i < u; i++) {
                        int f = nums[v];
                        nums[v] = nums[v + 1];
                        nums[v + 1] = f;
                        v += 2;
                    }
                }
                if (b > min) {
                    int u = b - min;
                    int v = 2 * mind + 1;
                    for (int i = 0; i < u; i++) {
                        int f = nums[v];
                        nums[v] = nums[v - 1];
                        nums[v - 1] = f;
                        v += 2;
                    }
                }
                for (int i = 0; i < n; i++) {
                    if (i != 0) {
                        out.append(' ');
                    }
                    out.append(String.format("%d", nums[i] + 1));
                }
                out.append('\n');
            } else {
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