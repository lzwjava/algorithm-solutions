import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean dp(int[] nums, int i, int n, int s1, int s2) {
        if (i == n) {
            return s1 == s2;
        }
        boolean ok = dp(nums, i + 1, n, s1 + nums[i], s2);
        if (ok) {
            return true;
        }
        ok = dp(nums, i + 1, n, s1, s2 + nums[i]);
        if (ok) {
            return true;
        }
        return false;
    }

    void solve() throws IOException {
        int m = Integer.parseInt(in.readLine());
        while (m > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = st.countTokens();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            boolean ok = dp(nums, 0, n, 0, 0);
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
            m--;
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
