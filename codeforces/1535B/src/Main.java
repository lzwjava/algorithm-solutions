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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    int n;
    int[] a;

    List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    int maxc;
    int cnt;
    int[] maxnums;
    int even;

    void permutation(int[] nums, boolean[] vis, int cur, int pair) {
        cnt++;
        if (cnt > 10000) {
            return;
        }
        if (cur == n) {
            if (pair > maxc) {
                maxc = pair;
                maxnums = nums.clone();
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if ((cur < even && a[i] % 2 == 0) || (cur >= even && a[i] % 2 == 1)) {
                if (!vis[i]) {
                    vis[i] = true;
                    nums[cur] = a[i];
                    int c = 0;
                    for (int k = 0; k < cur; k++) {
                        if (gcd(nums[k], 2 * nums[cur]) > 1) {
                            c++;
                        }
                    }
                    int d = n - cur - 1;
                    // n-1, n-d;
                    int s = 0;
                    for (int k = 0; k < d; k++) {
                        s += (n - 1 - k);
                    }
                    if (pair + c + s > maxc) {
                        permutation(nums, vis, cur + 1, pair + c);
                    }
                    vis[i] = false;
                }
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            even = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] % 2 == 0) {
                    even++;
                }
            }
            maxc = 0;
            int[] nums = new int[n];
            boolean[] vis = new boolean[n];
            cnt = 0;
            permutation(nums, vis, 0, 0);
            out.append(String.format("%d\n", maxc));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}