import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    long[][] nums;
    int n;
    int cnt;

    void permutation(int[] pos, int cur, int m, long sum) {
        if (cur == m - 1) {
            long rest = -sum;
            int index = Arrays.binarySearch(nums[m - 1], rest);
            if (index >= 0) {
                int i = index, j = index;
                while (i - 1 >= 0 && nums[m - 1][i - 1] == rest) {
                    i--;
                }
                while (j + 1 < n && nums[m - 1][j + 1] == rest) {
                    j++;
                }
                cnt += j - i + 1;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            pos[cur] = i;
            long nsum = sum + nums[cur][i];
            permutation(pos, cur + 1, m, nsum);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            in.readLine();
            n = Integer.parseInt(in.readLine());
            nums = new long[4][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 4; j++) {
                    nums[j][i] = Integer.parseInt(st.nextToken());
                }
            }
            Map<Long, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    long s = nums[i][0] + nums[j][1];
                    Integer count = map.get(s);
                    if (count == 0) {
                        count++;
                    }
                    map.put(s, count);
                }
            }
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    long s = -nums[i][2] - nums[j][3];
                    Integer count = map.get(s);
                    if (count != null) {
                        cnt += count;
                    }
                }
            }


            for (int i = 0; i < 4; i++) {
                Arrays.sort(nums[i]);
            }
            cnt = 0;
            int[] pos = new int[4];
            permutation(pos, 0, 4, 0);
            out.append(String.format("%d\n", cnt));
            t--;
            if (t != 0) {
                out.append('\n');
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