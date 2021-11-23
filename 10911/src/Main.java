import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double dist(Point b) {
            return Math.hypot(x - b.x, y - b.y);
        }
    }

    double calDist(int[] nums, int n) {
        int group = n / 2;
        double s = 0;
        for (int i = 0; i < group; i++) {
            int u = nums[i * 2];
            int v = nums[i * 2 + 1];
            s += points[u].dist(points[v]);
        }
        return s;
    }

    double minDist;

    void permutation(int[] nums, boolean[] vis, int cur, int n) {
        double dist = 0;
        if (cur % 2 == 0) {
            dist = calDist(nums, cur);
            if (dist > minDist) {
                return;
            }
        }
        if (cur == n) {
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        int si;
        if (cur > 0 && cur % 2 == 1) {
            si = nums[cur - 1] + 1;
        } else {
            si = 0;
        }
        for (int i = si; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                nums[cur] = i;
                permutation(nums, vis, cur + 1, n);
                vis[i] = false;
            }
        }
    }

    Point[] points;

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            points = new Point[n * 2];
            for (int i = 0; i < n * 2; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }
            minDist = Integer.MAX_VALUE;
            int m = n * 2;
            int[] nums = new int[m];
            boolean[] vis = new boolean[m];
            permutation(nums, vis, 0, m);
            out.append(String.format("Case %d: %.2f\n", caseNum, minDist));
            caseNum++;
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
