import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    double calLen(double[] nums, int len) {
        if (len == 0) {
            return 0;
        }
        double sum = nums[0];
        for (int i = 0; i < len - 1; i++) {
            double a = nums[i] + nums[i + 1];
            double b = Math.abs(nums[i] - nums[i + 1]);
            double c = Math.sqrt(a * a - b * b);
            sum += c;
        }
        sum += nums[len - 1];
        return sum;
    }

    double minLen;

    void permutation(double[] nums, double[] radiis, boolean[] vis, int cur, int n) {
        double len = calLen(nums, cur);
        if (len > minLen) {
            return;
        }
        if (cur == n) {
            if (len < minLen) {
                minLen = len;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                nums[cur] = radiis[i];
                permutation(nums, radiis, vis, cur + 1, n);
                vis[i] = false;
            }
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            double[] radiis = new double[m];
            for (int i = 0; i < m; i++) {
                radiis[i] = Double.parseDouble(st.nextToken());
            }
            double[] nums = new double[m];
            boolean[] vis = new boolean[m];
            minLen = Double.MAX_VALUE;
            permutation(nums, radiis, vis, 0, m);
            out.append(String.format("%.3f\n", minLen));
            n--;
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
