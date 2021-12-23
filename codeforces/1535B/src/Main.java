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

    int n;
    List<Integer> a;

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

    void permutation(int[] nums, boolean[] vis, int cur, int pair, int st) {
        cnt++;
        if (cnt > 1000) {
            return;
        }
        if (cur == n) {
            if (pair > maxc) {
                maxc = pair;
                maxnums = nums.clone();
            } else {
                out.append('\n');
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            int pi = (st + i) % n;
            int v = a.get(pi);
            if ((cur < even && v % 2 == 0) || (cur >= even && v % 2 == 1)) {
                if (!vis[pi]) {
                    vis[pi] = true;
                    nums[cur] = v;
                    int c = 0;
                    for (int k = 0; k < cur; k++) {
                        if (gcd(nums[k], 2 * nums[cur]) > 1) {
                            c++;
                        }
                    }
                    int d = n - cur - 1;
                    // n-1, n-d;
//                    int s = 0;
//                    for (int k = 0; k < d; k++) {
//                        s += (n - 1 - k);
//                    }
//                    if (pair + c + s > maxc) {
//
//                    }
                    permutation(nums, vis, cur + 1, pair + c, i);
                    vis[pi] = false;
                }
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            a = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                a.add(v);
            }
            Collections.sort(a, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1 % 2, o2 % 2);
                }
            });
            int c = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (gcd(a.get(i), 2 * a.get(j)) > 1) {
                        c++;
                    }
                }
            }
//            even = 0;
//            for (int i = 0; i < n; i++) {
//                if (a.get(i) % 2 == 0) {
//                    even++;
//                } else {
//                    break;
//                }
//            }
//            maxc = 0;
//            int[] nums = new int[n];
//            boolean[] vis = new boolean[n];
//            cnt = 0;
//            permutation(nums, vis, 0, 0, 0);
            out.append(String.format("%d\n", c));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}